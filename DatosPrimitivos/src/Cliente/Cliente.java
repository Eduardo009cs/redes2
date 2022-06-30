/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Datos Primitivos
  Cliente
*/
package Cliente;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            int pto = 2000;
            /*Obtenemos la dirección*/
            InetAddress dst = InetAddress.getByName("127.0.0.1");
            /*Creamos el socket de datagrama*/
            DatagramSocket cl = new DatagramSocket();
            /*Flujo de salida de bytes*/
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            /*Flujo de salida*/
            DataOutputStream dos = new DataOutputStream(baos);
            /*Escribimos el número 4*/
            dos.writeInt(4);
            /*Limpiamos nuestro flujo de salida*/
            dos.flush();
            /*Escribimos el número 4.1*/
            dos.writeFloat(4.1f);
            dos.flush();
            /*Escribimos el número 72*/
            dos.writeLong(72);
            dos.flush();
            /*Pasamos a un arreglo de bytes*/
            byte []b = baos.toByteArray();
            /*Creamos el paquete de datagrama*/
            DatagramPacket p = new DatagramPacket(b, b.length,dst,pto);
            /*Envio del paquete*/
            cl.send(p);
            System.out.println("Datos enviados");
            cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
