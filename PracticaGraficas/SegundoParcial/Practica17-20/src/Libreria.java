import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        //super.paint(g);
    }



   //Ejercicio 17

   public void drawRectangule(int x0, int y0, int x1, int y1, Color borderColor, Color fillColor) {
       // Dibuja las líneas horizontales del borde superior e inferior
       dibujarLineaBresenham(x0, y0, x1, y0, borderColor);
       dibujarLineaBresenham(x0, y1, x1, y1, borderColor);

       // Dibuja las líneas verticales del borde izquierdo y derecho
       dibujarLineaBresenham(x0, y0, x0, y1, borderColor);
       dibujarLineaBresenham(x1, y0, x1, y1, borderColor);

       // Relleno por Scan-Line
       rellenarRectanguloScanLine(x0, y0, x1, y1, fillColor);
   }



    public void rellenarRectanguloScanLine(int x0, int y0, int x1, int y1, Color color) {
        int xMin = Math.min(x0, x1);
        int xMax = Math.max(x0, x1);
        int yMin = Math.min(y0, y1);
        int yMax = Math.max(y0, y1);

        for (int y = yMin + 1; y < yMax; y++) {
            for (int x = xMin + 1; x < xMax; x++) {
                putPixel(x, y, color); // Dibuja el píxel en el interior del rectángulo

                try {
                    Thread.sleep(1); // Pausa de 10 milisegundos entre píxeles
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void dibujarLineaBresenham(int x0, int y0, int x1, int y1, Color color) {

        putPixel(x0, y0, color ); //Dibujar el primer pixel en el punto de incio

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




}
