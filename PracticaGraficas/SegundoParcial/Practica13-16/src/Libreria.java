import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

public class Libreria extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public Libreria() {
        setTitle("");
        setSize(500, 500);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
//Ejercicio 13
    public void lineaDDAPunteada(int x0, int x1, int y0, int y1, Color color) {
        float dx = x1 - x0; // Diferencia en la coordenada x
        float dy = y1 - y0; // Diferencia en la coordenada y
        float steps; // Número de pasos necesarios para mejorar la línea
        float x, y; // Coordenadas actuales de dibujo
        float xinc, yinc;

        // Declarando el arreglo de máscara
        int[] mascara = {1, 0, 1, 1, 0, 1};

        // Determinar el número de pasos según la dirección dominante (x o y)
        if (dx > dy) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }

        // Calcular los incrementos para x e y
        xinc = dx / steps;
        yinc = dy / steps;

        x = x0; // Inicializar x con la coordenada x de inicio
        y = y0; // Inicializar y con la coordenada y de inicio

        // Índice para controlar la máscara
        int mascaraIndex = 0;

        // Dibujar el primer pixel en la coordenada de inicio (redondeado)
        putPixel(round(x), round(y), color);

        // Realizar el trazado de la línea utilizando los incrementos
        for (float k = 1; k <= steps; k++) {
            x = x + xinc;
            y = y + yinc;

            mascaraIndex = (mascaraIndex + 1) % mascara.length;

            if (mascara[mascaraIndex] == 1) {
                putPixel(round(x), round(y), color); // Dibujar el pixel actual (redondeado)
            }
        }
    }

    //Ejercicio 14

    public void lineaGrosor(int x0, int x1, int y0, int y1, int grosor) {
        // Calcular la pendiente
        float dx = x1 - x0;
        float dy = y1 - y0;
        float pendiente = Math.abs(dy / dx);

        if (pendiente < 1) {
            // La pendiente es menor que 1, línea horizontal o casi horizontal
            for (int i = -(grosor / 2); i <= grosor / 2; i++) {
                lineaDDA(x0, x1, y0 + i, y1 + i);
            }
        } else {
            // La pendiente es mayor que 1, línea vertical o casi vertical
            for (int i = -(grosor / 2); i <= grosor / 2; i++) {
                if (dy > 0) {
                    lineaDDA(x0 + i, x1 + i, y0, y1);
                } else {
                    lineaDDA(x0 - i, x1 - i, y0, y1);
                }
            }
        }
    }


    public void lineaDDA(int x0,int x1, int y0, int y1){
        float dx = x1 - x0; //Diferencia en la coordenada x
        float dy = y1 - y0; //Diferencia en la coordenada y
        float steps; //Numero de pasos necesarios para mejorar la linea
        float x, y; //coordenadas actuales de dibujo
        float xinc, yinc; //Incrementos para x e y
        //Determinar el numero de pasos segun la direccion dominante (x o y)
        if (dx > dy){
            steps = dx;
        }else{
            steps = dy;
        }
        //Calcular los incrementos para x e y
        xinc = dx /steps;
        yinc = dy /steps;

        x = x0; //Inicializar x con la coordenada x de inicio
        y = y0; //Inicializar y con la coordenada y de inicio
        //Dibujando el primer pixel en la cooerdenada de inicio (redondeado)
        putPixel(round(x), round(y), Color.red);
        //Realizando el trazado de la line utilizando los incrementos
        for (float k = 1; k <=steps; k++){
            x = x + xinc;
            y = y + yinc;
            putPixel(round(x), round(y), Color.red); //Dibujar el pixel actual(redondeado)
        }
    }

    public void AlgoritmoBresenhamCircle(int centerX, int centerY, int radius, Color color) {
        int x = radius;
        int y = 0;
        int p = 1 - radius;

        while (x >= y) {
            putPixel( centerX + x, centerY + y, color); // Octante 1
            putPixel( centerX - x, centerY + y, color); // Octante 2
            putPixel( centerX + x, centerY - y, color); // Octante 3
            putPixel( centerX - x, centerY - y, color); // Octante 4
            putPixel( centerX - y, centerY + x, color); // Octante 5
            putPixel( centerX + y, centerY + x, color); // Octante 6
            putPixel( centerX - y, centerY - x, color); // Octante 7
            putPixel( centerX + y, centerY - x, color); // Octante 8

            y++;

            if (p <= 0) {
                p += 2 * y + 1;
            } else {
                x--;
                p += 2 * (y - x) + 1;
            }
        }
    }


    //Ejercicio 15



    public void dibujarCirculoPunteadoBresenhamConMascara(int centerX, int centerY, int radius, int[] mascara, Color color) {
        int x = radius;
        int y = 0;
        int p = 1 - radius;
        int mascaraIndex = 0; // Índice en la máscara

        while (x >= y) {
            if (mascara[mascaraIndex] == 1) {
                AlgoritmoBresenhamCircle(centerX, centerY, x, color);
            }

            y++;

            if (p <= 0) {
                p += 2 * y + 1;
            } else {
                x--;
                p += 2 * (y - x) + 1;
            }

            mascaraIndex = (mascaraIndex + 1) % mascara.length;
        }
    }

    //Ejercicio 16

    public void dibujarCirculoConGrosor(int centerX, int centerY, int radius, int grosor, Color color) {
        for (int i = 0; i < grosor; i++) {
            int radioInterior = radius + i;
            int radioExterior = radius + i + 1;

            // Dibuja las curvas interna y externa
            AlgoritmoBresenhamCircle(centerX, centerY, radioInterior, color);
            AlgoritmoBresenhamCircle(centerX, centerY, radioExterior, color);

            // Rellena el espacio entre las curvas (líneas horizontales o verticales)
            for (int y = centerY - radioExterior; y <= centerY + radioExterior; y++) {
                for (int x = centerX - radioExterior; x <= centerX + radioExterior; x++) {
                    double distancia = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                    if (distancia >= radioInterior && distancia <= radioExterior) {
                        putPixel(x, y, color);
                    }
                }
            }

            // Utiliza una brocha virtual en la curva
            for (double angulo = 0; angulo <= 360; angulo += 1) {
                double radianes = Math.toRadians(angulo);
                int xBrocha = (int) (centerX + radioInterior * Math.cos(radianes));
                int yBrocha = (int) (centerY + radioInterior * Math.sin(radianes));
                // Dibuja la brocha virtual en (xBrocha, yBrocha)
                putPixel(xBrocha, yBrocha, color);
            }
        }
    }

    public void dibujarCirculoConMascara(int centerX, int centerY, int radius, Color color) {
        int x = radius;
        int y = 0;
        int p = 1 - radius;

        // Máscara que define cuándo pintar (1) y cuándo omitir (0)
        int[] mascara = {1, 0, 1, 1, 0, 1, 1, 0,1, 0, 1, 1, 0, 1, 1, 0};

        int mascaraIndex = 0;

        while (x >= y) {
            if (mascara[mascaraIndex] == 1) {
                putPixel(centerX + x, centerY + y, color);
                putPixel(centerX - x, centerY + y, color);
                putPixel(centerX + x, centerY - y, color);
                putPixel(centerX - x, centerY - y, color);
                putPixel(centerX - y, centerY + x, color);
                putPixel(centerX + y, centerY + x, color);
                putPixel(centerX - y, centerY - x, color);
                putPixel(centerX + y, centerY - x, color);
            }

            y++;

            if (p <= 0) {
                p += 2 * y + 1;
            } else {
                x--;
                p += 2 * (y - x) + 1;
            }

            // Avanza al siguiente valor de la máscara
            mascaraIndex = (mascaraIndex + 1) % mascara.length;
        }
    }



}