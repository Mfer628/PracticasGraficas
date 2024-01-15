import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//Transformaciones - Rotación

public class Ejecutar extends JFrame implements KeyListener {
    
    //TEST
    public static void main(String[] args) {
        new Ejecutar();
    }

    private BufferedImage buffer;
    private ArrayList<Punto> cubo, cubo2;
    private Punto coord, dist;
    private int grados,tam;



    private Ejecutar (){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        this.setVisible(true);
        this.addKeyListener(this);
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Pixel admin = new Pixel(buffer);
        coord = new Punto(0, 0, 0);
        tam = 60;
        dist = new Punto(tam, tam, tam);
        cubo = Modelo.getPoints(coord, dist);
        cubo2 = Modelo.getPoints(coord, dist);
        grados = 0;
        repaint();
    }



    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode()==37)//Flecha izquierda
        {grados+=10;
            cubo2 = Punto.rotacionY(cubo, grados);
        }
        else if(e.getKeyCode()==39)//Flecha derecha
        {grados-=10;
            cubo2 = Punto.rotacionY(cubo, grados);
        }
        else if(e.getKeyCode()==38)//Flecha arriba
        {grados +=10;
            cubo2 = Punto.rotacionX(cubo, grados);
        }
        else if(e.getKeyCode()==40)//Flecha abajo
        {grados-=10;
            cubo2 = Punto.rotacionX(cubo, grados);
        }
        else if(e.getKeyCode()==65)//1 Angulo Z
        {grados-=10;
            cubo2 = Punto.rotacionZ(cubo, grados);
        }
        else if(e.getKeyCode()==83)//2 Angulo Z Tecla S
        {grados+=10;
            cubo2 = Punto.rotacionZ(cubo, grados);
        }
        repaint();
    }

    public void keyReleased(KeyEvent e){}

     public void paint(Graphics g){
        Pixel.fondo(Color.WHITE);
        Modelo.CuboRotado(cubo2, Color.BLACK);
        g.drawImage(buffer, 0, 0, this);
    }
}

