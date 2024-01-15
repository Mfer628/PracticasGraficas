import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Libreria objLibreria = new Libreria();
        //objLibreria.drawRectangule1(100,100,300, 200);
        //objLibreria.drawRectangule(100,100,300,200, Color.red,Color.CYAN);
        objLibreria.drawRectangule(100, 100, 300, 200,Color.black, Color.CYAN);

        /*
        Color borderColor = Color.black;
        Rellenoinunda objRelleno = new Rellenoinunda();
        objRelleno.centro(100, 100, 300,200, borderColor );

        int centro_x = (100 + 300) / 2;
        int centro_y = (100 + 200) / 2;
        Color fillColor = Color.blue; // Color con el que deseas rellenar

        objRelleno.floodFill(centro_x, centro_y, borderColor, fillColor);


         */
    }
}