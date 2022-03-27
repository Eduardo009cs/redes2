/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa:ECO
    SERVIDOR
*/
package eco;

import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            /*Creación del socket del servidor en el puerto 1234*/ 
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente...");
            for(;;){
                /*Conexion con el cliente*/
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());
                
                String mensaje = "Eduardo Cuevas Solorza";
                
                /*Flujo de salida de caracter*/
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                
                /*Envio de mensaje*/
                pw.println(mensaje);
                pw.flush();
                
                
                /*Recepción de mensaje del cliente*/
                System.out.println("\nMensaje recibido desde " + cl.getInetAddress() + ":" + cl.getPort());
                
                /*Flujo de caracter de salida  */
                BufferedReader br1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                
                /*Lectura del mensaje*/
                String mensajeC = br1.readLine();
                System.out.println("Contenido del mensaje: "  + mensajeC);
                pw.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
