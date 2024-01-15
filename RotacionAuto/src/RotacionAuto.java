import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RotacionAuto extends JPanel {

    private BufferedImage buffer;
    private Graphics graPixel;
    private double xyRDirection = 0.005; // Dirección de rotación en el plano xy
    private double xzRDirection = 0.005; // Dirección de rotación en el plano xz
    private double yzRDirection = 0.005; // Dirección de rotación en el plano yz


    // Vértices para el primer cubo
    private double vertex1[][][][] = {{{{-1, -1, -1}, {1, -1, -1}}, {{-1, 1, -1},{1, 1, -1}}}, {{{-1, -1, 1}, {1, -1, 1}}, {{-1, 1, 1}, {1, 1, 1}}}};

    // Vértices para el segundo cubo
    private double vertex2[][][][] = {{{{0, 0, -1}, {2, 0, -1}}, {{0, 2, -1},{2, 2, -1}}}, {{{0, 0, 1}, {2, 0, 1}}, {{0, 2, 1}, {2, 2, 1}}}};

    private double xyR = 0.005, xzR = 0.005, yzR = 0.005;

    private final static int IMAGE_SIZE = 500, SCALE = 30, OFFSET = 100, DIAMETER = 8;

    public static void main(String[] args) throws InterruptedException {
        RotacionAuto jPanel = new RotacionAuto();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);

        // HILO
        while (true) {
            Thread.sleep(10);
            jPanel.repaint();
        }
    }

    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        graPixel = buffer.createGraphics();
        Color c1 = Color.RED; // Color para el primer cubo
        Color c2 = Color.BLUE; // Color para el segundo cubo

        // Rotaciones para el primer cubo
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    xyRotate(vertex1[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex1[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex1[x][y][z], Math.sin(yzR), Math.cos(yzR));
                }
            }
        }

        // Dibujar el primer cubo
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                drawEdge(vertex1[x][y][0][0], vertex1[x][y][0][1], vertex1[x][y][1][0], vertex1[x][y][1][1], buffer, c1);
                drawEdge(vertex1[x][0][y][0], vertex1[x][0][y][1], vertex1[x][1][y][0], vertex1[x][1][y][1], buffer, c1);
                drawEdge(vertex1[0][x][y][0], vertex1[0][x][y][1], vertex1[1][x][y][0], vertex1[1][x][y][1], buffer, c1);
            }
        }

        // Rotaciones para el segundo cubo
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    xyRotate(vertex2[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex2[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex2[x][y][z], Math.sin(yzR), Math.cos(yzR));
                }
            }
        }

        // Dibujar el segundo cubo
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                drawEdge(vertex2[x][y][0][0], vertex2[x][y][0][1], vertex2[x][y][1][0], vertex2[x][y][1][1], buffer, c2);
                drawEdge(vertex2[x][0][y][0], vertex2[x][0][y][1], vertex2[x][1][y][0], vertex2[x][1][y][1], buffer, c2);
                drawEdge(vertex2[0][x][y][0], vertex2[0][x][y][1], vertex2[1][x][y][0], vertex2[1][x][y][1], buffer, c2);
            }
        }

        graphics.drawImage(buffer, 0, 0, this);

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    xyRotate(vertex1[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex1[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex1[x][y][z], Math.sin(yzR), Math.cos(yzR));
                }
            }
        }

        // Actualizar la rotación para el segundo cubo
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    xyRotate(vertex2[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex2[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex2[x][y][z], Math.sin(yzR), Math.cos(yzR));
                }
            }
        }

        // Verificar límites y cambiar la dirección de rotación si es necesario
        if (excedeLimites(vertex1) || excedeLimites(vertex2)) {
            xyRDirection *= -1; // Cambiar la dirección de rotación en el plano xy
            xzRDirection *= -1; // Cambiar la dirección de rotación en el plano xz
            yzRDirection *= -1; // Cambiar la dirección de rotación en el plano yz
        }

        // Aplicar la rotación ajustada a la dirección
        xyR += xyRDirection;
        xzR += xzRDirection;
        yzR += yzRDirection;

        // ...



    }

    private boolean excedeLimites(double[][][][] vertices) {
        // Verificar si alguno de los vértices excede los límites de la pantalla
        // Puedes ajustar estos límites según el tamaño de la pantalla y la escala utilizada
        // En este ejemplo, se asume que los límites son -5 y 5 en todas las direcciones
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    if (vertices[x][y][z][0] < -5 || vertices[x][y][z][0] > 5 ||
                            vertices[x][y][z][1] < -5 || vertices[x][y][z][1] > 5 ||
                            vertices[x][y][z][2] < -5 || vertices[x][y][z][2] > 5) {
                        return true; // Excede los límites
                    }
                }
            }
        }
        return false; // No excede los límites
    }
    final void xyRotate(double p[], double sin, double cos) {
        double temp;
        temp = cos * p[0] + sin * p[1];
        p[1] = -sin * p[0] + cos * p[1];
        p[0] = temp;
    }

    final void xzRotate(double p[], double sin, double cos) {
        double temp;
        temp = cos * p[0] + sin * p[2];
        p[2] = -sin * p[0] + cos * p[2];
        p[0] = temp;
    }

    final void yzRotate(double p[], double sin, double cos) {
        double temp;
        temp = cos * p[1] + sin * p[2];
        p[2] = -sin * p[1] + cos * p[2];
        p[1] = temp;
    }

    final void drawEdge(double x1, double y1, double x2, double y2, BufferedImage bu, Color col) {
        dibujaLinea((int) (x1 * SCALE) + OFFSET, (int) (-y1 * SCALE) + OFFSET, (int) (x2 * SCALE) + OFFSET, (int) (-y2 * SCALE) + OFFSET, col, bu);
    }

    public static void dibujaLinea(int x0, int y0, int x1, int y1, Color col, BufferedImage bu) {
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

        if (Math.toDegrees(Math.atan(pendiente)) > 0) {
            pendiente = (float) Math.abs(adyacente / opuesto);
            for (int i = 0; i <= Math.abs(opuesto); i++) {
                bu.setRGB(x0 + (int) (i * pendiente * sigX), y0 + (i * sigY), c.getRGB());
            }
        } else {
            for (int h = 0; h <= Math.abs(adyacente); h++) {
                bu.setRGB(x0 + h * sigX, y0 + (int) (h * pendiente * sigY), c.getRGB());
            }
        }
    }
}

