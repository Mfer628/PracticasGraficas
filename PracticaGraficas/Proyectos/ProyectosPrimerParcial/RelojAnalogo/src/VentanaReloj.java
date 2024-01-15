import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class VentanaReloj extends JFrame implements Runnable {
    private Image fondo;
    private BufferedImage background;
    private Image buffer;
    private Thread thr;
    public VentanaReloj(){
        super("Reloj Analogico");
        setSize(300, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        thr = new Thread(this);
        thr.start();
        try {
            background = ImageIO.read(new File("C:\\Users\\mfer-\\Pictures\\imagenGrafica\\luis.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        show();

    }

    @Override
    public void paint(Graphics g) {
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        if (fondo == null){
            fondo = createImage(getWidth(), getHeight());
            //Pintar circulo del reloj
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0, 0 , getWidth(), getHeight());
            gfondo.drawOval((getWidth()-100)/2, (getHeight()-100)/2 , 100, 100);

        }
        update(g);
        super.paint(g);
    }

    public void update (Graphics g ){
        g.setClip(0, 0, getWidth(), getHeight());
        Calendar cal = Calendar.getInstance();

        int min=0 ;
        if (cal.get(Calendar.MINUTE) != min){

            int hora = cal.get((Calendar.HOUR));
            min = cal.get(Calendar.MINUTE);
            //Crear la imagen estatica

            buffer = createImage(getWidth(), getHeight());

            Graphics gBuffer = buffer.getGraphics();

            gBuffer.drawImage(background, 0, 0, null);
            gBuffer.drawImage(fondo, 0, 0, this);

            gBuffer.setColor(Color.WHITE);
            gBuffer.fillArc((getWidth()-90)/2+5, (getHeight()-90)/2+5, 80,80, angulo12(hora, min), 3);
            gBuffer.fillArc((getWidth()-100)/2+5, (getHeight()-100)/2+5, 90,90, angulo60(min), 3);

            g.drawImage(buffer, 0, 0, this);
            int sec = cal.get(Calendar.SECOND);
            //Pintar ente movil
            g.fillArc((getWidth() - 100)/2+5, (getHeight()-100)/2+5, 90, 90, anguloSegundos(sec), 3);
        }
    }

    @Override
    public void run() {
        //Hilo
        while (true){

                try {
                    thr.sleep(1000);
                } catch (InterruptedException ex) {

                }
            repaint();
                }


    }
    private int angulo12(int hora, int minutos) {
        // Calcula el ángulo de la manecilla de las horas (en grados) según la hora y los minutos actuales.
        // Cada hora avanza 360/12 = 30 grados, y cada minuto adicional suma 0.5 grados.
        return (hora % 12) * 30 + (minutos / 2);
    }
    private int angulo60(int minutos) {
        // Calcula el ángulo de la manecilla de las horas (en grados) según la hora actual.
        // Por ejemplo, si quieres que la manecilla de las horas se mueva 30 grados por hora,
        // puedes usar la siguiente fórmula:
        // Ángulo = (hora % 12) * 30
        // Calcula el ángulo de la manecilla de los minutos (en grados) según los minutos actuales.
        // Por ejemplo, si quieres que la manecilla de los minutos se mueva 6 grados por minuto,
        // puedes usar la siguiente fórmula:
        // Ángulo = minutos * 6
        return minutos * 6;
    }
    private int anguloSegundos(int segundos) {
        // Calcula el ángulo de la manecilla de los segundos (en grados) según los segundos actuales.
        // En un reloj, hay 60 segundos en un minuto, y el círculo completo tiene 360 grados.
        // Por lo tanto, cada segundo avanza 360/60 = 6 grados.
        return segundos * 6;
    }

}
