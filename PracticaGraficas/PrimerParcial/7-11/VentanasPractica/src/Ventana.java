import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana extends Frame {
    public Ventana( ){
        super("Ejemplit herencia con  Frame ");
        setSize(500, 500);
        //Creando el constructor
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Cierra la ventana
                System.exit(0); // Termina la ejecución del programa
            }
        });

    }
}
