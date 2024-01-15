import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class Grafica extends JFrame {
    public String [] datos;
    public boolean bandera = false;
    public Grafica (String[] dato){
        this.datos = dato;
        setSize(300, 200);
        setVisible(true);
        bandera = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i <datos.length; i++) {
            System.out.println(datos[i]);
        }
        //Pasando los datos del arreglo a int
        if (bandera == true){
            int rojo = Integer.parseInt(datos[0]);
            int verde = Integer.parseInt(datos[1]);
            int azul = Integer.parseInt(datos[2]);
            System.out.println("Los elemtos son: " + rojo + verde + azul);
            int total_Votos = rojo + verde + azul;
            System.out.println("El total de votos es: "+total_Votos);
            int grado_rojo = rojo * 360 /total_Votos;
            int grado_verde = verde * 360 / total_Votos;
            int grado_azul = azul * 360 / total_Votos;

            g.setColor(new Color( 255 , 0 ,0 ));
            g.fillArc(25, 80, 200 , 200 , 0 , grado_rojo);
            g.fillRect(250, 120 , 20, 20);
            g.drawString("Color Rojo" , 275, 135);

            g.setColor(new Color( 0 , 130 ,0 ));
            g.fillArc(25, 80, 200 , 200 , grado_rojo , grado_verde);
            g.fillRect(250, 150 , 20, 20);
            g.drawString("Color Verde" , 275, 165);

            g.setColor(new Color( 0 , 0 ,255 ));
            g.fillArc(25, 80, 200 , 200 , grado_rojo + grado_verde , grado_azul);
            g.fillRect(250, 180 , 20, 20);
            g.drawString("Color Azuk " , 275, 195);
        }



    }
}
