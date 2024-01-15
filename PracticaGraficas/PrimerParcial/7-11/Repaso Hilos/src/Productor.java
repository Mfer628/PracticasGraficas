import javax.swing.*;
import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;

public class Productor implements Runnable{
    private Panaderia panaderia; //

    private JTextArea textArea;
    private long tiempoInicio;
    private JLabel objetoMovil;
    private int n;
    public Productor(Panaderia panaderia,int n,JTextArea txtArea, JLabel objetoMovil){
        this.panaderia = panaderia;
        this.textArea = txtArea;
        this.n =n;
        this.objetoMovil = objetoMovil;
    }
    //Panadero fabrica el pan
    @Override
    public void run() {
        int posX = 0;
        for (int i = 1; i <= n; i++) {
            String masa = "Pan n°: " + i; // Crear la cadena de masa
            panaderia.hornear(masa); //

            textArea.append("Panadero Hornea el pan " + masa + "\n"); // Agregamos al JTextArea
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(400, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long tiempoFinal = System.currentTimeMillis();
            long tiempoTranscurrido = tiempoFinal - tiempoInicio;
            posX = (int) (tiempoTranscurrido / 10);
            int finalPosX = posX;
            SwingUtilities.invokeLater(() -> {
                objetoMovil.setBounds(finalPosX, 100, 100, 30); // Modifica la posición en X y otros atributos según tus necesidades
            });
            // Agregamos al JTextArea
        }
    }
}
