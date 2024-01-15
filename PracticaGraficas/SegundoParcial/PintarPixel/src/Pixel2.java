import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

public class Pixel2 extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    public Pixel2(){
        setTitle("Pintando el pixel");
        setSize(500, 500);
        buffer = new BufferedImage(1,1 , BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void putPixel(int x , int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
    public void linea(float x0 ,float y0 , float x1 , float y1){
        // y = mx + b
        double m ;
        double b;
        m = (y1 - y0) / (x1 - x0);
        b = y0 - (m * x0);
        for (double x = x0; x <= x1 ;x++){
            double y = (m*x) + b;
            putPixel((int)x, (int) round(y), Color.red);
        }
    }
    public void lineaMejorada (int x0, int y0, int x1 , int y1 ){
        //En este codigo loq que yo propongo es que se trabajen
        //Calculando diferencias absolutas entre las coordenadas
        //dx almacenara la diferencia en el eje horizontal
        //dy almacenara la diferencia en el eje vertical
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = 1;
        int sy = 1;
        //variable err la utilizo para realizar un seguimiento de un  error
        int err = 0;
        int err2;
        //Ingreso un ciclo que es un bucle infinitp hasta que se complete el dibujo de la linea
        while (true) {
            putPixel(x0, y0, Color.red);
            //Esta condicion verifica si las coordenasas actuales han alcanzado las coordenadas finales
            //si es asi el bucle se rompe y se completa el dibujo de la linea
            if (x0 == x1 && y0 == y1) {
                break;
            }
            //Estas ultimas condiciones actualizan las coordenadas en funcion del error acumulado y las direcciones
            err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (err2 < dx) {
                err += dx;
                y0 += sy;
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

    public void dibujarLineaBresenham(int x0, int y0, int x1, int y1) {

        putPixel(x0, y0, Color.red); //Dibujar el primer pixel en el punto de incio

        int dx = Math.abs(x1 - x0); //Diferencia absoluta en la coordenada x
        int dy = Math.abs(y1 - y0); //Diferencia abosoluta en la coordenada y

        int sx = (x0 < x1) ? 1 : -1; //Direccion en el eje x (1 si x1 > x0, de lo contrario, -1)
        int sy = (y0 < y1) ? 1 : -1; //Direccion en el eje y (1 si y1 > y0, de lo contrario, -1)

        int err = dx - dy; //Inicializar el error
        int err2; //variable para el doble error

        //Bucle principal para trazar la linea
        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, Color.red);//Dibujar el pixel actual
            err2 = 2 * err; //calculando el doble error
            //Actulizandp el error y la coordenada x si el error es mayor que -dy
            if (err2 > -dy) {
                err -= dy;
                x0 += sx; //moviendo la direccion de x
            }

            //Actulizando el error y la coordenada y si el error es menor que dx
            if (err2 < dx) {
                err += dx;
                y0 += sy; //Mover en la direccion de y
            }
        }
    }

    public void dibujarLineaPuntoMedio(int x0, int y0, int x1, int y1) {

        int dx = Math.abs(x1 - x0); // Diferencia absoluta en la coordenada x
        int dy = Math.abs(y1 - y0); // Diferencia absoluta en la coordenada y

        int sx = (x0 < x1) ? 1 : -1; // Dirección en el eje x (1 si x1 > x0, de lo contrario, -1)
        int sy = (y0 < y1) ? 1 : -1; // Dirección en el eje y (1 si y1 > y0, de lo contrario, -1)

        int err = dx - dy; // Cálculo del error inicial

        // Bucle principal para trazar la línea
        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, Color.red); // Dibujar el píxel actual en rojo

            int err2 = 2 * err; // Cálculo del doble del error

            // Si el error es negativo o igual a cero, ajustar el error y mover en la dirección de x
            if (err2 >= -dy) {
                err -= dy; // Ajuste del error en función de dy
                x0 += sx; // Mover en la dirección de x
            }
            // Si el error es positivo o igual a cero, ajustar el error y mover en la dirección de y
            if (err2 <= dx) {
                err += dx; // Ajuste del error en función de dx
                y0 += sy; // Mover en la dirección de y
            }
        }
    }
    public void drawRectangule(int x0 , int y0, int x1, int y1){
        dibujarLineaBresenham(x0, y0, x1, y0);
        dibujarLineaBresenham(x0, y0, x0, y1);
        dibujarLineaBresenham(x0, y1, x1, y1);
        dibujarLineaBresenham(x1,y0, x1, y1);
    }
    public void clear(){
        repaint();
    }
}
