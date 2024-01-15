import javax.swing.*;
import java.awt.*;

public class UsoGraficos extends JFrame {
    private Image fondo;
    private Image buffer;
    public UsoGraficos(){
        super("UsoGraficos");
        setResizable(false);
        setSize(200, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void paint(Graphics g){
        //Estableciendo un fondo es un rectangulo blanco
        //Solo agregue un rectangulo como fondo
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Dibujar el texto en la ventana
        g.setColor(Color.BLACK); // Cambia el color del texto según tus necesidades
        g.drawString("Demo de Gráficos", 10, 50);
        //Dibujar su carita
        g.drawArc(50, 60, 50 ,50,0, 360);
        g.drawArc(60, 70, 30 ,30,180, 180);
        //DibujanDo sus ojitos
        g.fillOval(65, 75, 5, 5);
        g.fillOval(80, 75, 5 , 5);

        //Dibujando su cuerpito
        g.drawLine(75, 110, 75, 200);
        //Dibujando sus Brasitos
        g.drawLine(75, 120,45, 160);
        g.drawLine(75, 120, 105, 160);
        //Dibujando sus piernitas
        g.drawLine(75, 200, 45, 240);
        g.drawLine(75, 200, 105, 240);

    }
}
