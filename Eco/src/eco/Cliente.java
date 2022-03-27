/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa:ECO
    CLIENTE
*/
package eco;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            /*Flujo de lectura de entrada estandar*/
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            
            /*Direccion del servidor localhost*/
            System.out.print("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            
            /*Puerto del servidor 1234*/
            System.out.print("\nEscriba el puerto: ");
            int puerto = Integer.parseInt(br1.readLine());
            
            /*Creación de socket con el respectivo host y puerto*/
            Socket cl = new Socket(host,puerto);
            
            /*Flujo de lectura para el socket*/
            BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String mensaje = br2.readLine();
            System.out.println("Recibimos un mensaje desde el servidor");
            System.out.println("Mensaje: " + mensaje);
            
            /*Flujo de escritura para el envio del mensaje al servidor*/
            System.out.println("\n\nEnviando mensaje de vuelta..");
            BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(cl.getOutputStream()));
            
            /*Envio de mensaje*/
            pw.write(mensaje);
            pw.flush();
            
            pw.close();
            br1.close();
            br2.close();
            cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
