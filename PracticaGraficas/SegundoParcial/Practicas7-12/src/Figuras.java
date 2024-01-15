import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Figuras extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    public Figuras(){
        setTitle("Figuras");
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buffer = new BufferedImage(1,1 , BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();

    }

    public void putPixel(int x , int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
    @Override
    public void paint(Graphics g) {
        //super.paint(g);
    }

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

    public void dibujarCirculos(){
        int centerx=90, centery = 300, radio=70;
        for (int i = 1; i<=3; i++) {
            AlgoritmoBresenhamCircle(centerx, centery, radio,Color.blue);
            //Redusco solo el radio y esto hace que quede dentro del anterior circulo
            radio -= 20; //
        }
    }

    public void dibujarOvalos(){
        int centerx=550, centery = 300, radio1=100,radio2=50;
        for (int i = 1; i <= 4 ; i++ ){
            dibujarElipse(centerx, centery,radio1, radio2);
            radio1 -= 15;
            radio2 -= 10;

        }

            //Redusco solo el radio y esto hace que quede dentro del anterior circulo

    }

    public void dibujarRectangulos(){
        int x0=200 , y0=200, x1=400, y1=300;
        for(int i = 1; i <= 2; i++){
            drawRectangule(x0,y0, x1, y1);


            // Calculando el centro del rectángulo actual
            int centerX = (x0 + x1) / 2;
            int centerY = (y0 + y1) / 2;

            // Calculando el nuevo ancho y altura para el siguiente rectángulo
            int newWidth = (x1 - x0) - 50;  // Reduce el ancho en 50 unidades
            int newHeight = (y1 - y0) - 50; // Reduce la altura en 50 unidades

            // Calculando las nuevas coordenadas para el siguiente rectángulo
            x0 = centerX - (newWidth / 2);
            y0 = centerY - (newHeight / 2);
            x1 = centerX + (newWidth / 2);
            y1 = centerY + (newHeight / 2);
        }

    }
    public void drawRectangule(int x0 , int y0, int x1, int y1){
        dibujarLineaBresenham(x0, y0, x1, y0);
        dibujarLineaBresenham(x0, y0, x0, y1);
        dibujarLineaBresenham(x0, y1, x1, y1);
        dibujarLineaBresenham(x1,y0, x1, y1);
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
    public void dibujarElipse(int xc,  int yc, int radiusX, int radiusY) {
        for (double angle = 0; angle <= 2 * Math.PI; angle += 0.01) {
            int x = (int) (xc + radiusX * Math.cos(angle));
            int y = (int) (yc + radiusY * Math.sin(angle));
            putPixel(x, y, Color.GREEN);
        }
    }

}
