import javax.swing.*;

public class Consumidor implements Runnable{
    private Panaderia panaderia; //
    private JTextArea textArea;
    private int n ;
    public Consumidor(Panaderia panaderia,int n , JTextArea txtArea){
        this.panaderia = panaderia;
        this.textArea=txtArea;
        this.n = n;
    }
    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            String pan = panaderia.consumir();
            textArea.append("Cliente Consume el pan " + pan + "\n"); // Agregamos al JTextArea
        }
    }
}
