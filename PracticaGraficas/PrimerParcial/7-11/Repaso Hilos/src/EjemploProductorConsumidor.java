import javax.swing.*;

public class EjemploProductorConsumidor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 new  Ventana();
            }
        });


    }
}
