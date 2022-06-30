/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Datos Primitivos
  Servidor
*/
package Servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            /*Crea un socket de datragrama logado al puerto 2000*/
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor esperando el cliente");
            for(;;){
                /*Crea un paquete de datagrama para recibir paquetes*/
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                /*Recibe el paquete*/
                s.receive(p);
                System.out.println("Datagrama recibido desde " + p.getAddress() + ":" + p.getPort());
                /*Flujo de entrada para obtener los datos recibidos*/
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                /*Recibimos un dato de tipo int*/
                int x = dis.readInt();
                /*Recibimos un dato de tipo float*/
                float f = dis.readFloat();
                /*Recibimos un dato de tipo long*/
                long z = dis.readLong();
                /*Impresión de los datos recibidos*/
                System.out.println("\n\nEntero: " + x + " Flotante: " + f + " Long: " + z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
