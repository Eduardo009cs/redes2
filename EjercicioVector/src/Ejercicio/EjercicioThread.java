/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Ejercicio 2 de hilos
*/
package Ejercicio;

import java.util.*;
/*Extendemos de la clase Thread*/
public class EjercicioThread extends Thread{
    /*Constructor de la clase*/
    public EjercicioThread(String str){
        super(str);
    }
    public void run(){
        /*Generamos números randoms*/
        Random rnd = new Random();
        /*Tamaño del vector en un rango de 5 a 35*/
        int tam_vector = rnd.nextInt(30) + 5;
        /*Declaramos las variables para las operaciones*/
        int suma = 0;
        double suma_cuadrados = 0;
        float promedio = 0;
        /*Creamos el vector con el tamaño del vector */
        Vector v = new Vector(tam_vector);
        
        /*Llenamos el vector con números aleatorios*/
        for (int i = 0; i < tam_vector; i++) {
            v.add(i, rnd.nextInt(30) + 5);
        }
        /*Imprimimos el vector generado*/
        System.out.println("Vector generado\n" + v);
        /*Realizamos las operaciones de:
            -Suma de todos los números del vector
            -Suma de los elmentos cuadrados del vector*/
        for (int i = 0; i < tam_vector; i++) {
            suma += (int)v.get(i);
            suma_cuadrados += Math.pow((int)v.get(i), 2);
        }
        /*Obtenemos el promedio*/
        promedio = suma/tam_vector;
        /*Imprimimos los resultados*/
        System.out.println("Suma de elementos: " + suma);
        System.out.println("Suma de elementos cuadrados: " + suma_cuadrados);
        System.out.println("Promedio: " + promedio);
        System.out.println("Termina thread " + getName());
    }
    public static void main(String[] args) {
        /*Creamos las dos instancias*/
        new EjercicioThread("Vector 1").start();
        new EjercicioThread("Vector 2").start();
        System.out.println("Termina thread main");
    }
}
