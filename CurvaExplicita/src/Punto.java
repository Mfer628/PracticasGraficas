import java.util.ArrayList;

public class Punto {
    public int x, y, z;
    public static Punto instance;

    public Punto(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Rotar en X
    public static ArrayList<Punto> rotacionX(ArrayList<Punto> points, double degree) {
        int x, y, z;
        ArrayList<Punto> temp = new ArrayList<>();
        for (Punto point : points) {
            x = point.x;
            y = (int) (((double) point.y * Math.cos(Math.toRadians(degree))) + ((double) point.z * Math.sin(Math.toRadians(degree))));
            z = (int) (((double) point.y * -Math.sin(Math.toRadians(degree))) + ((double) point.z * Math.cos(Math.toRadians(degree))));
            temp.add(new Punto(x, y, z));
        }
        return temp;
    }

 //Rotar en Y
    public static ArrayList<Punto> rotacionY(ArrayList<Punto> points, double degree) {
        int x, y, z;
        ArrayList<Punto> temp = new ArrayList<>();
        for (Punto point : points) {
            x = (int) (((double) point.x * Math.cos(Math.toRadians(degree))) + (-(double) point.z * Math.sin(Math.toRadians(degree))));
            y = point.y;
            z = (int) (((double) point.x * Math.sin(Math.toRadians(degree))) + ((double) point.z * Math.cos(Math.toRadians(degree))));
            temp.add(new Punto(x, y, z));
        }
        return temp;
    }

     //Rotar en Z
    public static ArrayList<Punto> rotacionZ(ArrayList<Punto> points, double degree) {
        int x, y, z;
        ArrayList<Punto> temp = new ArrayList<>();
        for (Punto point : points) {
            x = (int) (((double) point.x * Math.cos(Math.toRadians(degree))) + ((double) point.y * Math.sin(Math.toRadians(degree))));
            y = (int) (((double) point.x * -Math.sin(Math.toRadians(degree))) + ((double) point.y * Math.cos(Math.toRadians(degree))));
            z = point.z;
            temp.add(new Punto(x, y, z));
        }
        return temp;
    }

    public static void resetPoint(Punto point) {
        
    }

}
