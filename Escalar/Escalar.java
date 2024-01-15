package TercerParcial.Escalar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

//Transformaciones - Escalar (G,P)

public class Escalar extends JFrame implements KeyListener {

    //Test
    public static void main(String[] args) {
        new Escalar();
    }

    public Cuadro c1;
    private Graphics graPixel;
    private BufferedImage pixel;
    private BufferedImage pixelTem;
    private int unidad, y2, x2, dx, dy, dz;

    private Escalar() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        this.addKeyListener(this);
        pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        x2 = 200;
        y2 = 250;
        dx = 0;
        dy = 0;
        dz = 0;
        unidad = 50;
    }

    private void dibujaPixel(int x, int y, Color c) {
        pixel.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(pixel, x, y, this);
    }

    public void dibujaLinea(int x0, int y0, int x1, int y1, Color col) {
        Color c = col;
        float adyacente = (float) Float.max(x0, x1) - Float.min(x0, x1);
        float opuesto = (float) Float.max(y0, y1) - Float.min(y0, y1);
        float pendiente = (float) opuesto / adyacente;

        int sigX = 0;
        int sigY = 0;
        pendiente = Math.abs(pendiente);

        if (x0 < x1) {
            sigX = 1;
        } else {
            sigX = -1;
        }
        if (y0 < y1) {
            sigY = 1;
        } else {
            sigY = -1;
        }

        if (Math.toDegrees(Math.atan(pendiente)) > 45) {
            pendiente = (float) Math.abs(adyacente / opuesto);
            for (int i = 0; i <= Math.abs(opuesto); i++) {
                dibujaPixel(x0 + (int) (i * pendiente * sigX), y0 + (i * sigY), c);
            }
        } else {
            for (int h = 0; h <= Math.abs(adyacente); h++) {
                dibujaPixel(x0 + h * sigX, y0 + (int) (h * pendiente * sigY), c);
            }
        }
    }

    public void cuboPerspectiva(int x1, int y1, int z1, int large, Color c) {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0, 0};
        int[] arrY = {0, 0, 1, 1, 0};
        double x, y, z = 100.0;
        double xc = 50;
        double yc = 50;
        double zc = -100.0;
        for (int cont = 0; cont < arrX.length; cont++) {
            double tempX, tempY;
            tempX = x1 + (arrX[cont] * (large + dx));
            tempY = y1 + (arrY[cont] * (large + dy));
            x = tempX + (xc * ((z - z1) / zc));
            y = y2 - (tempY + (yc * ((z - z1) / zc)));
            valuesX1.add((int) x);
            valuesY1.add((int) y);
        }
        for (int cont = 0; cont < arrX.length; cont++) {
            double tempX, tempY;
            double tempZ = z1 + large + dz;
            tempX = x1 + (arrX[cont] * (large + dx));
            tempY = y1 + (arrY[cont] * (large + dy));
            x = tempX + (xc * ((z - tempZ) / zc));
            y = y2 - (tempY + (yc * ((z - tempZ) / zc)));
            valuesX2.add((int) x);
            valuesY2.add((int) y);
        }
        for (int cont = 1; cont < valuesX1.size(); cont++) {
            dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c);
            dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c);
            dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics gFondo = null;
        pixelTem = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        graPixel = pixelTem.createGraphics();
        gFondo = pixelTem.getGraphics();
        gFondo.setColor(Color.WHITE);
        gFondo.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        cuboPerspectiva(x2, 0, 50, unidad, Color.BLACK);
        g.drawImage(pixel, 0, 0, this);
    }

    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 71)//Tecla G
        {
            unidad += 5;
            repaint();
        } else if (e.getKeyCode() == 80)//Tecla P
        {
            unidad -= 5;
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}

