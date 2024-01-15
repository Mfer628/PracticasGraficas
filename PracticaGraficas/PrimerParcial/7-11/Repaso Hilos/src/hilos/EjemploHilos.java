package hilos;

public class EjemploHilos extends Thread {
    public EjemploHilos (String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Se inicio el hilo" + getName());
        for (int i = 0; i<10; i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.getName());
        }
        System.out.println("Finaliza Hilo");

    }
}
