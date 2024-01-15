public class Panaderia {
    private String pan;
    private boolean disponibilidad;
    public synchronized void hornear(String masa){
        while (disponibilidad){
            try {
                wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
        this.pan = masa;
        System.out.println("Panadero Hornea el pan " + this.pan);
        this.disponibilidad = true;
        notify(); //Avisamos al consumidor que ya esta listo el pan
    }

    public synchronized String consumir (){
        //Mientras que el la disponibilidad sea diferente de true .. El consumidor espera
        while (!disponibilidad){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Cliente Consume el pan" + this.pan);
        this.disponibilidad = false;
        notify(); //Al recurso que ya se consumio el pan
        return pan;
    }

}
