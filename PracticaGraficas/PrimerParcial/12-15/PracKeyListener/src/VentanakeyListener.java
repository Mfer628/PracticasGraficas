import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;

public class VentanakeyListener extends JFrame implements KeyListener {
    public VentanakeyListener(){
        super("Eventos Key Listener");
        setSize(400, 300);
        addKeyListener(this);
        show();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Tecla Tipeada"+ e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Tecla Presionada" + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Tecla Liberada"+ KeyEvent.getKeyText(e.getKeyCode()));
    }
}
