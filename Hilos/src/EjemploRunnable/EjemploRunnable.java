/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Ejemplo con Runnable
*/
package EjemploRunnable;

/*Implementamos la interfaz Runnable*/
public class EjemploRunnable implements Runnable{
    /*Funcion que se va a ejecutar una vez que el hilo se este ejecutando*/
    public void run(){
        /*For para mostrar números del 0 al 9 y el nombre del hilo*/
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " "  + Thread.currentThread().getName());
        }
        System.out.println("Termina thread " + Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        /*Creamos los hilos y le pasamos como paramatro una instancia a la interfaz 
        y el nombre que llevara el hilo*/
        new Thread(new EjemploRunnable(), "Luis").start();
        new Thread(new EjemploRunnable(), "Enrique").start();
        System.out.println("Termina thread main");
    }
}
