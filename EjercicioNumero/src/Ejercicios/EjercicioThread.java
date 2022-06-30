/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Ejercicio 1 de hilos
*/
package Ejercicios;

/*Extendemos de la clase Thread*/
public class EjercicioThread extends Thread{
    
    /*Constructor de la clase*/
    public EjercicioThread(String str){
        super(str);
    }
    /*Código que ejecutará el hilo cuando sea iniciado*/
    public void run(){
        /*For que nos ayudara a contar de 1 a 10*/
        for (int i = 1; i<=10; i++) {
            /*Condición con la que se verificará que el hilo que se esta
            ejecutando es par o impar*/
            if(getName().equals("Par")){
                /*Condicion con la que se verificará que el número es par*/
                if(i%2 == 0){
                    System.out.println("Numero: " + i);
                }
            }else{
                /*Condicion con la que se verificará que el número es impar*/
                if(i%2 != 0){
                    System.out.println("Numero: " + i);
                }
            }
        }
        System.out.println("Termina thread " + getName());
    }
    public static void main(String[] args) {
        /*Creando hilos*/
        new EjercicioThread("Impar").start();
        new EjercicioThread("Par").start();
        new EjercicioThread("Impar").start();
        new EjercicioThread("Par").start();
        System.out.println("Termina thread main");
    }
}
