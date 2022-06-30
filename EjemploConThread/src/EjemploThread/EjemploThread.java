/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Ejemplo con Thread
*/
package EjemploThread;
/*Extendemos de la clase Thread*/
public class EjemploThread extends Thread{
    /*Constructor de la clase que recibe un String como parametro, para ponerselo
    como nombre al hilo que se va a crear*/
    public EjemploThread(String str){
        super(str);
    }
    /*Función que va a iniciar el hilo una vez que se este ejecutando*/
    public void run(){
        /*Creamos un for que va a imprimir números desde 0 a 9 junto con el 
        nombre del hilo que se esta ejecutando*/
        for (int i = 0; i<10; i++) {
            System.out.println(i + " " + getName());
        }
        System.out.println("Termina thread " + getName());
    }
    public static void main(String[] args) {
        /*Creamos dos instancia a la clase para iniciar la ejecución de los 
        hilos*/
        new EjemploThread("Luis").start();
        new EjemploThread("Enrique").start();
        System.out.println("Termina thread main");
    }
}
