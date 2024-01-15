package hilos;

public class ImprimirFrases implements Runnable{
   private String frase1, frase2;
   public ImprimirFrases(String _frase1, String _frase2){
       this.frase1 = _frase1;
       this.frase2 = _frase2;

   }

    @Override
    public void run() {
    EjemploSincronizacionThread.ImprimirFrases(this.frase1, this.frase2);
    }
}
