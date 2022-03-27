/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Envío de archivos multiples
  SERVIDOR
*/

package servidor;

import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args) {
        try {
            /*Socket del servidor*/
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Servidor en espera...");
            for (;;) {
                /*Conexión con el cliente*/
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());
                /*Flujo de nivel de bits de entrada*/
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                /*Obtenemos el número de archivos enviados por el cliente*/
                int num_archivos = dis.readInt();
                System.out.println("\nNúmero de archivos recibidos: " + num_archivos);
                for (int i = 0; i < num_archivos; i++) {
                    byte[] b = new byte[1024];
                    System.out.print("\n-------------------Archivo recibido " + (i+1) + "-------------------\n");
                    String nombre = dis.readUTF();/*Nombre del archivo*/
                    System.out.println("Nombre del archivo: " + nombre);
                    long tam = dis.readLong();/*Tamaño del archivo*/
                    /*Flujo de salida para el archivo*/
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
                    long recibidos = 0;
                    int porcentaje, n;
                    /*Datos recibidos*/
                    while (recibidos < tam) {
                        n = dis.read(b);
                        dos.write(b, 0, n);
                        recibidos = recibidos + n;
                        porcentaje = (int) (recibidos * 100 / tam);
                        System.out.println("Recibido: " + porcentaje + "%\r");
                        dos.flush();
                    }
                    System.out.print("Archivos recibidos " + (i+1) + "/" + num_archivos + "\n");
                    dos.close();
                }
                dis.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}