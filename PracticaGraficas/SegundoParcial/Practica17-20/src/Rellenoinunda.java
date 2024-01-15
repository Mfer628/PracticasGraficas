import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Queue;
import java.util.LinkedList;
public class Rellenoinunda extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public Rellenoinunda(){
        setTitle("Relleno Inundacion");
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
        repaint();
    }
    public void drawRectangule(int x0, int y0, int x1, int y1, Color borderColor) {

        dibujarLineaBresenham(x0, y0, x1, y0, borderColor);
        dibujarLineaBresenham(x0, y1, x1, y1, borderColor);
        dibujarLineaBresenham(x0, y0, x0, y1, borderColor);
        dibujarLineaBresenham(x1, y0, x1, y1, borderColor);


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

    public void centro(int x0, int y0, int x1, int y1, Color color){
         drawRectangule(x0,y0,x1,y1, color);
         //Calculando el centro de la figura
        int centro_x = (x0 + x1) / 2;
        int centro_y = (y0 + y1) / 2;
        putPixel(centro_x, centro_y, Color.blue);

        //Relleno

        floodFill(centro_x, centro_y, color, Color.blue);

    }
    public void floodFill(int x, int y, Color targetColor, Color fillColor) {
        if (!targetColor.equals(fillColor)) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));

            while (!queue.isEmpty()) {
                Point current = queue.poll();
                x = current.x;
                y = current.y;

                if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
                    int rgb = buffer.getRGB(x, y);
                    Color pixelColor = new Color(rgb);

                    if (pixelColor.equals(targetColor)) {
                        putPixel(x, y, fillColor);

                        queue.add(new Point(x + 1, y));
                        queue.add(new Point(x - 1, y));
                        queue.add(new Point(x, y + 1));
                        queue.add(new Point(x, y - 1));
                    }
                }
            }
        }
    }


}
