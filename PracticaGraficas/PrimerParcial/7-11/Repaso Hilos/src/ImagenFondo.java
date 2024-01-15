import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImagenFondo extends JPanel {

    private Image imagen;


    public void paint(Graphics g){
        imagen=new ImageIcon(getClass().getResource("/Imagenes/imgPanaderia.png")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }

}
