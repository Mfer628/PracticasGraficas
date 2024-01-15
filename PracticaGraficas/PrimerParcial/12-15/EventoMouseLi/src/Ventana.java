import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Ventana extends JFrame implements MouseListener, MouseMotionListener {
    JButton btnPrueba;
    public Ventana(){
        setTitle("Eventos del Mouse");
        setSize(400, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);
        show();
    }
//este metodo se llama cuando se hace click en el JFrame
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clickeado ");
    }
//se llama cuando se presiona el boton
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse presionado ");
    }
//cuando se suelta el boton del mouse
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse liberado en");
    }
//puntero del mouse entra en el JFrame
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entró en la ventana");
    }
//cuando el puntero del mouse sale del area del componente
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse salió de la ventana");
    }
//sea arrastea el mouse mientraas se mantiene presiona el boton del mouse
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse arrastrado ");
    }
//mouse se mueve sin presionar ningun boton
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse movido en");
    }
}
