import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No se proporcionaron números para ordenar.");
            return;
        }

        ArrayList <Integer> numeros = new ArrayList<>();

        //Llenando el array
        for (int i = 0; i < args.length; i++ ){
            numeros.add(Integer.parseInt(args[i]));
        }

        //Ordenando la Lista de numeros
        Collections.sort(numeros);


        MostrarArray(numeros);



    }
    public static void MostrarArray (ArrayList <Integer> numeros){

        System.out.println("Ordenado los numeros");

        for (int numero : numeros) {
            System.out.println(numero);
        }

    }
}
