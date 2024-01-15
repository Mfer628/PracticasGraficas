import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Libreria objLibreriaPu = new Libreria();
        Libreria objLibreriaGro = new Libreria();
        Libreria objLibreriaMas = new Libreria();
        Libreria objLibreriaGrosorCi = new Libreria();

        objLibreriaPu.lineaDDAPunteada(100, 300,100 ,300, Color.red);
        objLibreriaGro.lineaGrosor(100, 200,150,150,5);

        objLibreriaMas.dibujarCirculoConMascara(250, 250, 50 ,Color.red);
        objLibreriaGrosorCi.dibujarCirculoConGrosor(250,250,50,5, Color.red);

    }
}