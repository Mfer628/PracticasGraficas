public class Main {
    public static void main(String[] args) {

        String[] primerCasoHex = {"-hex", args[1]} ;
        String[] segundoCasoIP = {"-ip", args[1]};
        Main obMain = new Main();



        if (args[0].equals(primerCasoHex[0])){
            //System.out.println("Primer caso");
            System.out.println("Conversion de hexadecimal a ip");
            String ip = obMain.HexadecimalIp(primerCasoHex);
            System.out.println("La ip es: "+ip);
        } else if (args[0].equals(segundoCasoIP[0])) {
            System.out.println("Segundo caso");
            System.out.println("Convercion de Ip a Hexadecimal");
            String hexDecimal = obMain.IpHexadecimal(segundoCasoIP);
            System.out.println(hexDecimal);
        }

    }

    public String HexadecimalIp(String[] datos){
        StringBuilder ip = new StringBuilder();
        //System.out.println("La cantidad de datos son: "+datos.length);
/*
        for ( int i =0; i < datos.length; i++){
            System.out.println("Los datos son "+ i);
        }

 */
        String cadena = datos[1];
        //System.out.println("La cantidad es: "+cadena.length());
        // Asegurarse de que la longitud de la cadena sea múltiplo de 8 caracteres
        int padding = 8 - (cadena.length() % 8);
        if (padding != 8) {
            StringBuilder pad = new StringBuilder();
            for (int i = 0; i < padding; i++) {
                pad.append("0");
            }
            cadena = pad.toString() + cadena;
        }

        // Divide la cadena hexadecimal en grupos de 8 caracteres
        for (int i = 0; i < cadena.length(); i += 8) {
            String group = cadena.substring(i, i + 8);

            // Convierte el grupo de 8 caracteres de hexadecimal a binario
            long binaryValue = Long.parseLong(group, 16);

            // Convierte el valor binario en decimal y agrega al resultado
            ip.append((binaryValue >> 24) & 0xFF).append(".")
                    .append((binaryValue >> 16) & 0xFF).append(".")
                    .append((binaryValue >> 8) & 0xFF).append(".")
                    .append(binaryValue & 0xFF);

            if (i + 8 < cadena.length()) {
                ip.append(".");
            }
        }

        return ip.toString();
    }
    public String IpHexadecimal (String[] datos) {

        String cadena = datos[1];
        String[] octets = cadena.split("\\.");
        //System.out.println(cadena);

        if (octets.length != 4) {
            return "Error: La dirección IP no tiene el formato correcto.";
        }

        StringBuilder hexIP = new StringBuilder();

        for (String octet : octets) {
            try {
                int decimalValue = Integer.parseInt(octet);
                if (decimalValue < 0 || decimalValue > 255) {
                    return "Error: La dirección IP contiene valores fuera de rango.";
                }

                String hexValue = Integer.toHexString(decimalValue); // Convierte a hexadecimal
                if (hexValue.length() == 1) {
                    hexValue = "0" + hexValue; // Asegura que tenga dos dígitos
                }

                hexIP.append(hexValue).append(".");
            } catch (NumberFormatException e) {
                return "Error: La dirección IP contiene caracteres no válidos.";
            }
        }

        return hexIP.substring(0, hexIP.length() - 1); // Elimina el último punto
    }



}