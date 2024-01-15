import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

public class Ventana2 extends JFrame {

    private BufferedImage background;
    private BufferedImage buffer;
    private Timer timer;


    public Ventana2() {
        super("Reloj Analogico");
        setSize(500, 500);
        reproducirMusica();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        // Configura un temporizador para actualizar la ventana cada segundo
        Thread clockThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                repaint(); // Vuelve a dibujar el reloj cada segundo
                            }
                        });
                        Thread.sleep(1000); // Esperar 1 segundo
                    } catch (InterruptedException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        clockThread.setDaemon(true);
        clockThread.start();


        // Cargar la imagen de fondo desde un archivo
        try {
            background = ImageIO.read(new File("C:\\Users\\mfer-\\Pictures\\imagenGrafica\\luis.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        updateBuffer(buffer.getGraphics());
        g.drawImage(buffer, 0, 0, this);
    }

    private void updateBuffer(Graphics gBuffer) {
        Calendar cal = Calendar.getInstance();
        int min = 0;

        if (cal.get(Calendar.MINUTE) != min) {
            int hora = cal.get(Calendar.HOUR);
            min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);

            System.out.println("Hora: " + hora + ":"+ min +":" + sec);
            gBuffer.drawImage(background, 0, 0, null); // Dibujar la imagen de fondo primero
            Graphics2D g2d = (Graphics2D) gBuffer;
            // Dibujar las manecillas del reloj
            // Asegúrate de definir las funciones angulo12 y angulo60 correctamente
            GradientPaint gradient1 = new GradientPaint(0, 0, Color.decode("#ffe642"), 0, getHeight(), Color.BLACK);
            GradientPaint gradient2 = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), Color.GRAY);
            GradientPaint gradient3 = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), Color.green);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
// Definir el radio del primer círculo con el degradado
            int radius1 = 170; // Ajusta este valor según tus necesidades
// Calcular las coordenadas del centro del primer círculo
            int centerX1 = centerX;
            int centerY1 = centerY;
// Definir el tamaño y la ubicación del primer círculo con el degradado
            int ovalX1 = centerX1 - radius1;
            int ovalY1 = centerY1 - radius1;
            int ovalWidth1 = radius1 * 2;
            int ovalHeight1 = radius1 * 2;
// Establecer el degradado y el grosor de la línea para el primer círculo
            g2d.setPaint(gradient1);
            g2d.setStroke(new BasicStroke(4));
// Dibujar el primer círculo con el degradado centrado
            g2d.drawOval(ovalX1, ovalY1, ovalWidth1, ovalHeight1);
// Definir el radio del segundo círculo (esfera del reloj)
            int radius2 = 150; // Ajusta este valor según tus necesidades
// Calcular las coordenadas del centro del segundo círculo (esfera del reloj)
            int centerX2 = centerX1;
            int centerY2 = centerY1;
// Definir el tamaño y la ubicación del segundo círculo (esfera del reloj)
            int ovalX2 = centerX2 - radius2;
            int ovalY2 = centerY2 - radius2;
            int ovalWidth2 = radius2 * 2;
            int ovalHeight2 = radius2 * 2;
// Establecer el degradado y el grosor de la línea para el segundo círculo
            g2d.setPaint(gradient2); // Puedes definir gradient2 para el segundo círculo
            g2d.setStroke(new BasicStroke(2)); // Puedes ajustar el grosor según tus necesidades

// Dibujar el segundo círculo (esfera del reloj) centrado con respecto al primer círculo
            g2d.drawOval(ovalX2, ovalY2, ovalWidth2, ovalHeight2);

            gBuffer.fillArc((getWidth() - 180) / 2 + 5, (getHeight() - 180) / 2 + 5, 180, 180, angulo12(hora, min), 3);
            g2d.setPaint(gradient3);

            gBuffer.fillArc((getWidth() - 180) / 2 + 5, (getHeight() - 180) / 2 + 5, 190, 190, angulo60(min), 3);


            gBuffer.setColor(Color.RED);
            gBuffer.fillArc((getWidth() - 180) / 2 + 5 , (getHeight() - 180) / 2 + 5, 200, 200, anguloSegundos(sec), 3);

            gBuffer.setColor(Color.BLACK);

            Font font = new Font("Times New Roman", Font.BOLD, 30);
            gBuffer.setFont(font);
            g2d.setColor(Color.blue);
            g2d.drawString("12", 240, 130);
            g2d.drawString("1", 320, 155);
            g2d.drawString("2", 368, 210);
            g2d.drawString("3", 377, 270);
            g2d.drawString("4", 360, 330);
            g2d.drawString("5", 320, 372);
            g2d.drawString("6", 250, 390);
            g2d.drawString("7", 180, 380);
            g2d.drawString("8", 128, 330);
            g2d.drawString("9", 110, 270);
            g2d.drawString("10", 110, 210);
            g2d.drawString("11", 155, 155);

        }
    }

    // Debes definir las funciones angulo12 y angulo60 según tus necesidades

    private int angulo12(int hora, int minutos) {
        // Calcula el ángulo de la manecilla de las horas (en grados) según la hora y los minutos actuales.
        // Cada hora avanza 360/12 = 30 grados, y cada minuto adicional suma 0.5 grados.
        return (hora % 12) * 30 + (minutos / 2) - 90;
    }
    private int angulo60(int minutos) {
        if (minutos == 0) {
            return 0; // Cuando los minutos son 0, el ángulo es 0 grados.
        } else {
            return 360 - (minutos * 6);
        }
    }
    private int anguloSegundos(int segundos) {
        // Calcula el ángulo de la manecilla de los segundos (en grados) según los segundos actuales.
        // En un reloj, hay 60 segundos en un minuto, y el círculo completo tiene 360 grados.
        // Por lo tanto, cada segundo avanza 360/60 = 6 grados.
        return 360 - (segundos * 6);
    }

    private void reproducirMusica() {
        try {
            // Cargar el archivo de música (debes proporcionar la ruta correcta al archivo)
            InputStream inputStream = getClass().getResourceAsStream("/Musica/musica.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            // Configurar el Clip para reproducir la música
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // Reproducir la música en bucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

}