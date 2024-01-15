import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.server.RMIClassLoader;

public class Libreria extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    public Libreria(){
        setTitle("7-12");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buffer = new BufferedImage(1,1 , BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();

        setVisible(true);
    }
    public void putPixel(int x , int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics g) {
        // super.paint(g);
    }
    //Ejercicio 7
    public void drawCircleFormulaC(int xc, int yc, int radio) {

//El bucle for itera a travez de las coordenadas x desde xc - radio hasta xc + radio: Esto significa que
        //recorrera todas las pociciones horizontales dentro del circulo en el eje x
        for (int x = xc - radio; x <= xc + radio; x++) {
//Para cada valor de x se calcula el valor correspondiente de y utilizando la ecuacion del circulo
            //Es como si se dividiera en dos partes y aqui se dibuja la parte superior del circulo
            int y = yc + (int) Math.sqrt(Math.pow(radio, 2) - Math.pow(x - xc, 2));
            putPixel(x, y, Color.red);
            //Aqui calculamos la parte inferior del circulo cambiando el signo negativo
            y = yc - (int) Math.sqrt(Math.pow(radio, 2) - Math.pow(x - xc, 2));
            putPixel(x, y, Color.red);
        }
        repaint();
    }

    //Ejercicio 8
public void drawCirlePolarCordi(int xc, int yc, int radio){
        for (double angulo = 0; angulo <=2*Math.PI; angulo += 0.01){
            int x = (int)(xc + radio*Math.cos(angulo));
            int y = (int)(yc + radio*Math.sin(angulo));
            putPixel(x,y, Color.red);
        }
repaint();
}

    public void drawEllipsePolarCoord(int xc, int yc, int radiusX, int radiusY) {
        for (double angle = 0; angle <= 2 * Math.PI; angle += 0.01) {
            int x = (int) (xc + radiusX * Math.cos(angle));
            int y = (int) (yc + radiusY * Math.sin(angle));
            putPixel(x, y, Color.red);
        }
        repaint();
    }


//Ejercicio 9

    public void dibujarCirculoSimetriaOcho(int xc, int yc, int radio, Color color) {
        for (int i = 0; i <= 360; i++) {
            double theta = i * Math.PI / 180; // Convierte grados a radianes

            // Calcula las coordenadas x e y en función del ángulo polar y el radio
            int x = xc + (int) (radio * Math.cos(theta));
            int y = yc + (int) (radio * Math.sin(theta));

            putPixel(x, y, color);

            // También debes reflejar los puntos en los otros siete octantes
            int[] octantes = {
                    1, 1, -1, 1, 1, -1, -1, -1
            };

            for (int j = 0; j < 8; j += 2) {
                int newX = xc + octantes[j] * (x - xc);
                int newY = yc + octantes[j + 1] * (y - yc);

                putPixel(newX, newY, color);
            }
        }
    }
//Ejercicio 10

    public void AlgoritmoPuntoMedio(int xc, int yc , int R){
        int x, y,p;
        x = 0;
        y = R;
        p = 1-R;
        dibujarOctantes(xc, yc,x,y,Color.red);

        while (x < y){
            x = x+1;
            if (p < 0){
                p = p +2*x +3;
            }else{
                y = y-1;
                p = p+2*(x-y) + 5;
            }
            dibujarOctantes(xc,yc,x,y, Color.red);
        }
    }

    public void dibujarOctantes(int xc, int yc, int x, int y, Color color){

        putPixel(xc + x, yc + y, color); // Octante superior derecho
        putPixel(xc - x, yc + y, color); // Octante superior izquierdo
        putPixel(xc + x, yc - y, color); // Octante inferior derecho
        putPixel(xc - x, yc - y, color); // Octante inferior izquierdo
        putPixel(xc + y, yc + x, color); // Octante derecho superior
        putPixel(xc - y, yc + x, color); // Octante izquierdo superior
        putPixel(xc + y, yc - x, color); // Octante derecho inferior
        putPixel(xc - y, yc - x, color); // Octante izquierdo inferior
    }

    //Ejercicio 11
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

    //Ejercicio 12
}
