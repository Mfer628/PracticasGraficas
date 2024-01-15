import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        int opc;
        float x0 , y0 , x1, y1;
        int x0M , y0M , x1M, y1M;
        int x0B , y0B , x1B, y1B;
        Pixel2 objPixel = new Pixel2();
        do{
            System.out.println("Practicas 1-6");
            System.out.println("1. Modelo Matematico para dibujar una linea recta");
            System.out.println("2. Modelo Matematico mejorado para dibujar una linea recta");
            System.out.println("3. Algoritmo DDA");
            System.out.println("4. Algoritmo de Bresenham");
            System.out.println("5. Algoritmo de punto medio");
            System.out.println("6. Rectangulo");
            System.out.println("7. Salir");
            System.out.println();
            opc = in.nextInt();
            switch (opc){

                case 1:
                    System.out.println("Modelo Matematico para dibujar una linea recta");
                    System.out.println("Dame los datos de (x0 , y0 , x1 , y1)");
                    x0=in.nextFloat();
                    y0=in.nextFloat();
                    x1=in.nextFloat();
                    y1=in.nextFloat();

                    objPixel.linea(x0, y0, x1, y1);

                    break;
                case 2:
                    objPixel.clear();
                    System.out.println("Modelo Matematico mejorado para dibujar una linea recta");
                    System.out.println("Dame los datos de (x0 , y0 , x1 , y1)");
                    x0M=in.nextInt();
                    y0M=in.nextInt();
                    x1M=in.nextInt();
                    y1M=in.nextInt();
                    objPixel.lineaMejorada(x0M, y0M, x1M, y1M);
                    break;
                case 3:
                    objPixel.clear();
                    System.out.println("Algoritmo DDA");
                    System.out.println("Modelo Matematico mejorado para dibujar una linea recta");
                    System.out.println("Dame los datos de (x0 , y0 , x1 , y1)");
                    x0M=in.nextInt();
                    y0M=in.nextInt();
                    x1M=in.nextInt();
                    y1M=in.nextInt();
                    objPixel.lineaDDA(x0M, x1M, y0M, y1M);
                    break;

                case 4:
                    objPixel.clear();
                    System.out.println("Algoritmo de Bresenham");
                    System.out.println("Dame los datos de (x0 , y0 , x1 , y1)");
                    x0B=in.nextInt();
                    y0B=in.nextInt();
                    x1B=in.nextInt();
                    y1B=in.nextInt();
                    objPixel.dibujarLineaBresenham(x0B, y0B, x1B, y1B);
                    break;
                case 5:
                    objPixel.clear();
                    System.out.println("Algoritmo de punto medio");
                    System.out.println("Dame los datos de (x0 , y0 , x1 , y1)");
                    x0M=in.nextInt();
                    y0M=in.nextInt();
                    x1M=in.nextInt();
                    y1M=in.nextInt();
                    objPixel.dibujarLineaPuntoMedio(x0M, y0M, x1M, y1M);
                    break;
                case 6:
                    objPixel.clear();
                    System.out.println("Rectangulo");
                    System.out.println("Ingrese las coordenadas de la esquina superior izquierda (x0, y0):");
                    int x0R = in.nextInt();
                    int y0R = in.nextInt();
                    System.out.println("Ingrese las coordenadas de la esquina inferior derecha (x1, y1):");
                    int x1R = in.nextInt();
                    int y1R = in.nextInt();
                    objPixel.drawRectangule(x0R, y0R, x1R, y1R);
                    break;
            }
        }while (opc != 7);
    }

}


