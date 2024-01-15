import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ventana extends JFrame {


    JButton btn2;
    private JTextArea textArea;
    private JLabel objetoMovil;


    int nConsumidor;
    int nProductor;
    public Ventana() {
        setTitle("Productor Consumidor");
        setResizable(false);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reproducirMusica();

        btn2 = new JButton("Comenzar");
        btn2.setBounds(670, 0, 100, 30);
        btn2.setBackground(Color.ORANGE);
        btn2.setForeground(Color.BLACK);
        add(btn2);



        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ejecutar cuando se hace clic en el botón
                nProductor = Integer.parseInt(JOptionPane.showInputDialog("Cuantos Panes deseas Producir"));
                nConsumidor=Integer.parseInt(JOptionPane.showInputDialog("Cuanto pan deseas consumir"));
                textArea = new JTextArea(); // Inicializamos el JTextArea
                JScrollPane scrollPane = new JScrollPane(textArea); // Agregamos un JScrollPane
                scrollPane.setEnabled(false);
                scrollPane.setBounds(400, 40, 300  , 300); // Establecemos su posición y tamaño
                add(scrollPane); // Agregamos el JScrollPane en lugar del JTextArea directamente

                objetoMovil = new JLabel("Objeto");
                Panaderia p = new Panaderia();
                new Thread(new Productor(p, nProductor, textArea, objetoMovil)).start();
                new Thread(new Consumidor(p,nConsumidor ,textArea)).start();
            }
        });
        ImagenFondo panel = new ImagenFondo();
        add(panel);



    setVisible(true);
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
