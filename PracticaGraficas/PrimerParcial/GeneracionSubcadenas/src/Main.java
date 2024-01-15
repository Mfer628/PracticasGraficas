public class Main {
    public static void main(String[] args) {
        Main obj1 = new  Main();

        if (args.length > 0) {
            // El primer argumento (args[0]) es la cadena de texto proporcionada
            String texto = args[0];

            for (char i : texto.toCharArray()) {
                System.out.print(i);
            }

            obj1.Descendente(texto);
            obj1.Ascendente(texto);

        }



    }

    public void Descendente (String texto){
        for (int i = texto.length(); i >= 1; i--) {
            String subcadena = texto.substring(0 , i);
            System.out.println("\"" + subcadena + "\"");
        }
    }
    public void Ascendente(String texto){
        for (int i = texto.length(); i >= 0; i--) {
            String subcadena = texto.substring(i);
            System.out.println("\"" + subcadena + "\"");
        }
    }
}