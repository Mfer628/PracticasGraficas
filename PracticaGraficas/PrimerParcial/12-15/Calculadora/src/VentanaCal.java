import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCal extends JFrame  {


        private JTextField txtCalcular;
        private JButton C, div, multi, rest, sum, igual, punto, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
        private double num1Value, num2Value;
        private String operador;

    public VentanaCal() {
            setTitle("Calculadora");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Configurar el administrador de diseño del JFrame
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(6, 6 ,6 ,6 );
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;

            txtCalcular = new JTextField();
            txtCalcular.setEditable(false);
            txtCalcular.setPreferredSize(new Dimension(100, 100));
            gbc.gridx = 0; // Primera columna
            gbc.gridy = 0; // Primera fila
            gbc.gridwidth = 4; // Ocupar 5 columnas
            add(txtCalcular, gbc);

            // Configurar los botones
            C = new JButton("C");
            div = new JButton("/");
            multi = new JButton("*");
            rest = new JButton("-");
            sum = new JButton("+");
            igual = new JButton("=");
            punto = new JButton(".");
            num0 = new JButton("0");
            num1 = new JButton("1");
            num2 = new JButton("2");
            num3 = new JButton("3");
            num4 = new JButton("4");
            num5 = new JButton("5");
            num6 = new JButton("6");
            num7 = new JButton("7");
            num8 = new JButton("8");
            num9 = new JButton("9");


            // Configurar las restricciones para cada botón
            gbc.gridwidth = 1; // Restablecer a 1 columna
            gbc.gridy = 1; // Segunda fila
            gbc.gridx = 0; // Primera columna
            add(C, gbc);

            gbc.gridx = 1; // Segunda columna
            gbc.gridheight=1;
            add(div, gbc);

            gbc.gridx = 2; // Tercera columna
            gbc.gridheight=1;
            add(multi, gbc);

            gbc.gridx = 3; // Cuarta columna
            gbc.gridheight=1;
            add(rest, gbc);



             // Ocupar 2 columnas
            gbc.gridx=2;
            gbc.gridy=6;
            add(punto, gbc);

            gbc.gridx = 0;// Tercera columna
            gbc.gridy = 6;
            gbc.gridwidth=2;
            add(num0, gbc);

            gbc.gridx = 0; // Primera columna
            gbc.gridy = 5; // Cuarta fila
            gbc.gridwidth=1;
            add(num1, gbc);

            gbc.gridx = 1; // Segunda columna
            gbc.gridwidth=1;
            add(num2, gbc);

            gbc.gridx = 2; // Tercera columna
            gbc.gridwidth=1;
            add(num3, gbc);

            gbc.gridx = 0; // Cuarta columna
            gbc.gridy = 4;
            add(num4, gbc);

            gbc.gridx = 1; // Quinta columna
            gbc.gridy = 4;
            add(num5, gbc);

            gbc.gridx = 2; // Primera columna
            gbc.gridy = 4; // Quinta fila
            add(num6, gbc);

            gbc.gridx = 0;// Segunda columna
            gbc.gridy = 2;
            add(num7, gbc);

            gbc.gridx = 1; // Tercera columna
            gbc.gridy = 2;
            add(num8, gbc);

            gbc.gridx = 2;// Cuarta columna
            gbc.gridy = 2;
            add(num9, gbc);

            gbc.gridheight=3;
            gbc.gridx = 3; //
            gbc.gridy = 2; //segunda coumna
            add(sum, gbc);

            gbc.gridheight=6;
            gbc.gridx=3;
            gbc.gridy = 5 ;
            add(igual,gbc);
            // Configurar el JFrame
            pack();
            setLocationRelativeTo(null); // Centrar en la pantalla
            setVisible(true);

            //Accionando botones

            num0.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "0";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "1";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "2";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "3";
                            txtCalcular.setText(nuevovalor);
                    }
            });
            num4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "4";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "5";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "6";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "7";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "8";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            num9.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "9";
                            txtCalcular.setText(nuevovalor);

                    }
            });

            sum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "+";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            rest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "-";
                            txtCalcular.setText(nuevovalor);
                    }
            });
            multi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "*";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            div.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + "/";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            punto.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String valorActual = txtCalcular.getText();
                            String nuevovalor = valorActual + ".";
                            txtCalcular.setText(nuevovalor);

                    }
            });
            C.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                                txtCalcular.setText("");

                    }
            });

            igual.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                                Calcular(txtCalcular);

                    }
            });

        }



private void Calcular(JTextField txtCalcular){
          String contenido = txtCalcular.getText();
        System.out.println(contenido);
        char[] a = contenido.toCharArray();
        String[] arreglo = new String[a.length];
        boolean banderaSumar = false;
        boolean banderaRestar = false;
        boolean banderaDivision = false;
        boolean banderaMultiplicacion = false;

        for (int i = 0; i < arreglo.length; i++){
                arreglo[i] = String.valueOf(a[i]);
                if (arreglo[i].equals("+")){
                        banderaSumar = true;
                }else if (arreglo[i].equals("-")){
                        banderaRestar = true;
                } else if (arreglo[i].equals("/")) {
                        banderaDivision = true;
                }else if (arreglo[i].equals("*")){
                        banderaMultiplicacion=true;
                }
        }

        if(banderaSumar){
                sumar(arreglo);
        }else if (banderaRestar){
                restar(arreglo);
        } else if (banderaDivision) {
                division(arreglo);
        }else if(banderaMultiplicacion){
                multi(arreglo);
        }

        //System.out.println(sumar(arreglo));
        //sumar(arreglo);




}

        public double sumar (String[] numeros){
            double resultado ;
            double num1 , num2;

                        num1 = Double.parseDouble(numeros[0]);
                        num2 = Double.parseDouble(numeros[2]);
                        resultado =  num1 + num2;
                        System.out.println(resultado);

                txtCalcular.setText(String.valueOf(resultado));
        return resultado;
        }
        public double restar (String[] numeros){
                double resultado ;
                double num1 , num2;

                num1 = Double.parseDouble(numeros[0]);
                num2 = Double.parseDouble(numeros[2]);
                resultado =  num1 - num2;
                System.out.println(resultado);

                txtCalcular.setText(String.valueOf(resultado));
                return resultado;
        }
        public double division (String[] numeros){
                double resultado ;
                double num1 , num2;

                num1 = Double.parseDouble(numeros[0]);
                num2 = Double.parseDouble(numeros[2]);
                resultado =  num1 / num2;
                System.out.println(resultado);

                txtCalcular.setText(String.valueOf(resultado));
                return resultado;
        }
        public double multi (String[] numeros){
                double resultado ;
                double num1 , num2;

                num1 = Double.parseDouble(numeros[0]);
                num2 = Double.parseDouble(numeros[2]);
                resultado =  num1 * num2;
                System.out.println(resultado);

                txtCalcular.setText(String.valueOf(resultado));
                return resultado;
        }
}

