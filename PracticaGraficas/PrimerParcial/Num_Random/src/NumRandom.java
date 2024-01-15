public class NumRandom {

    public void GenerarNumero (){
        double random1 = Math.random() * 100;
        double random2 = Math.random() * 100;
        System.out.println(random1);
        System.out.println(random2);
        if (random1 > random2){
            System.out.println("El  primer numero es mayor " + random1);
        }else {
            System.out.println("El  segundo numero es mayor " + random2);
        }
    }

}
