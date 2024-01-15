package TercerParcial.Rotacion;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;



public class Modelo {

    public static ArrayList<Punto> getPoints(Punto origen, Punto abertura) {
        ArrayList<Punto> values = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0};
        int[] arrY = {0, 0, 1, 1};
        int x, y;
        for (int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            values.add(new Punto(x, y, -abertura.z / 2));
        }
        for (int cont = 0; cont < arrX.length; cont++) {
            x = origen.x + (arrX[cont] * abertura.x);
            y = origen.y + (arrY[cont] * abertura.y);
            values.add(new Punto(x, y, abertura.z / 2));
        }
        return values;
    }

    public static void CuboRotado(ArrayList<Punto> cubePoints, Color c) {
        double x, y, z = 250.0;
        double xp = -60.0, yp = -60.0, zp = -86.0;
        ArrayList<Punto> puntitos = new ArrayList<>();

        for (Punto cubePoint : cubePoints) {
            x = cubePoint.x + (xp * ((z - cubePoint.z) / zp));
            y = Pixel.height - (cubePoint.y + (yp * ((z - cubePoint.z) / zp)));
            puntitos.add(new Punto((int) x, (int) y, 0));
        }
        for (int cont = 0; cont < (puntitos.size() / 2) - 1; cont++) {
            LineaBresenham(puntitos.get(cont).x, puntitos.get(cont).y, puntitos.get(cont + 1).x, puntitos.get(cont + 1).y, c); //1er cuadrante
            LineaBresenham(puntitos.get(cont + 4).x, puntitos.get(cont + 4).y, puntitos.get(cont + 5).x, puntitos.get(cont + 5).y, c); //1er cuadrante
            LineaBresenham(puntitos.get(cont).x, puntitos.get(cont).y, puntitos.get(cont + 4).x, puntitos.get(cont + 4).y, c); //1er cuadrante
        }
        LineaBresenham(puntitos.get(3).x, puntitos.get(3).y, puntitos.get(0).x, puntitos.get(0).y, c); //1er cuadrante
        LineaBresenham(puntitos.get(7).x, puntitos.get(7).y, puntitos.get(4).x, puntitos.get(4).y, c); //1er cuadrante
        LineaBresenham(puntitos.get(3).x, puntitos.get(3).y, puntitos.get(7).x, puntitos.get(7).y, c); //1er cuadrante
    }

    private static void LineaBresenham(int x0, int y0, int x1, int y1, Color c) {
        int x, y;
        int diferenciaX, diferenciaY, A, B, pk, stepx, stepy;
        diferenciaX = (x1 - x0);
        diferenciaY = (y1 - y0);
        if (diferenciaY < 0) {
            diferenciaY = -1 * diferenciaY;
            stepy = -1;
        } else {
            stepy = 1;
        }
        if (diferenciaX < 0) {
            diferenciaX = -1 * diferenciaX;
            stepx = -1;
        } else {
            stepx = 1;
        }
        x = x0;
        y = y0;
        Pixel.instance.drawPixel(x, y, c);
        if (diferenciaX > diferenciaY) {
            pk = (2 * diferenciaY) - diferenciaX;
            A = 2 * diferenciaY;
            B = (2 * diferenciaY) - (2 * diferenciaX);
            while (x != x1) {
                x = x + stepx;
                if (pk < 0) {
                    pk = pk + A;
                } else {
                    y = y + stepy;
                    pk = pk + B;
                }
                Pixel.instance.drawPixel(x, y, c);
            }
        } else {
            pk = (2 * diferenciaX) - diferenciaY;
            A = 2 * diferenciaX;
            B = (2 * diferenciaX) - (2 * diferenciaY);
            while (y != y1) {
                y = y + stepy;
                if (pk < 0) {
                    pk = pk + A;
                } else {
                    x = x + stepx;
                    pk = pk + B;
                }
                Pixel.instance.drawPixel(x, y, c);
            }
        }
    }
     public void fillAreaBetweenLines(Punto p1, Punto p2, Punto p3, Punto p4, Color c) {
        // Asegúrate de que los puntos estén ordenados de izquierda a derecha
        ArrayList<Punto> orderedPoints = orderPoints(p1, p2, p3, p4);

        int minY = orderedPoints.get(0).y;
        int maxY = orderedPoints.get(3).y;

        for (int y = minY; y <= maxY; y++) {
            // Encuentra los puntos de intersección en cada línea
            Punto intersection1 = getIntersection(orderedPoints.get(0), orderedPoints.get(3), y);
            Punto intersection2 = getIntersection(orderedPoints.get(1), orderedPoints.get(2), y);

            // Dibuja la línea horizontal entre los puntos de intersección
            LineaBresenham(intersection1.x, y, intersection2.x, y, c);
        }
    }

    // Ordena los puntos de izquierda a derecha
    private static ArrayList<Punto> orderPoints(Punto p1, Punto p2, Punto p3, Punto p4) {
        ArrayList<Punto> orderedPoints = new ArrayList<>();
        orderedPoints.add(p1);
        orderedPoints.add(p2);
        orderedPoints.add(p3);
        orderedPoints.add(p4);

        orderedPoints.sort(Comparator.comparingInt(point -> point.x));
        return orderedPoints;
    }

    // Encuentra la intersección de una línea horizontal con un segmento de línea
    private static Punto getIntersection(Punto p1, Punto p2, int y) {
        int x = (int) (p1.x + (double) (y - p1.y) / (p2.y - p1.y) * (p2.x - p1.x));
        return new Punto(x, y, 0);
    }

}

class Pixel {
    private final BufferedImage buffer;
    public static Pixel instance;
    public static int width, height;

    public Pixel(BufferedImage b) { 
        buffer = b;
        width = this.buffer.getWidth();
        height = this.buffer.getHeight();
        instance = this;
    }

    public void drawPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public static void fondo(Color c) {
        for (int i = 0; i < instance.buffer.getWidth(); i++)
            for (int j = 0; j < instance.buffer.getHeight(); j++)
                instance.drawPixel(i, j, c);
    }

}
