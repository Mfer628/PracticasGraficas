package Espiral2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class Espiral extends JFrame{

    public Espiral (){
        setVisible(true);
        setSize(700 , 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Thread hilo1 = new Thread(() -> Espiral(g , 60));
        Thread hilo2 = new Thread(() -> Espiral(g , -60));
        hilo1.start();
        hilo2.start();
        try{
           hilo1.join();
           hilo2.join();

        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }


    }

    public void Espiral (Graphics g , double factor){
        Graphics2D g2D = (Graphics2D)  g;
        int cx = getWidth() /2;
        int cy = getHeight() / 2;
        for (double ang = 0; ang < 30 * Math.PI; ang += 0.01) {
            double r = factor * Math.sqrt(ang);
            double x = cx + r *Math.cos(ang);
            double y = cy + r *Math.sin(ang);
            g2D.setColor(Color.blue);
            g2D.fillRect((int) x, (int) y , 3,  3 );
            try{
                Thread.sleep(1);
                repaint();

            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
