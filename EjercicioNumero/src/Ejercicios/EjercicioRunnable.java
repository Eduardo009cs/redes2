/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Ejercicio 1 de hilos
*/
package Ejercicios;
/*Implementamos la interfaz runnable*/
public class EjercicioRunnable implements Runnable{
    /*Función que se ejecutará cuando el hilo sea creado*/
    public void run(){
        for (int i = 1; i <= 10; i++) {
            /*Condición para verificar que el hilo va a mostrar números pares o
            impares*/
            if(Thread.currentThread().getName().equals("Par")){
                /*Condición para verificar que el número sea par*/
                if( i%2==0){
                    System.out.println("Numero: " + i);
                }
            }else{
                /*Condición para verificar que el número sea impar*/
                if( i%2!=0){
                    System.out.println("Numero: " + i);
                }
            }
        }
        System.out.println("Termina thread " + Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        /*Creación de los hilos e iniciandolos*/
        new Thread(new EjercicioRunnable(), "Impar").start();
        new Thread(new EjercicioRunnable(), "Par").start();
        new Thread(new EjercicioRunnable(), "Impar").start();
        new Thread(new EjercicioRunnable(), "Par").start();
        System.out.println("Termina thread main");
    }
}
