/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Socket de Datagrama
  Cliente
*/
package Cliente;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) {
        try{
            
            System.out.println("Cliente iniciado ");
            /*Direccion destino*/
            String dst = "127.0.0.1";
            /*Puerto destino*/
            int pto = 2000;
            int num_datag;
            String data;
            for(;;){
                /*INICIO DEL BLOQUE DE ENVIO DE MENSAJES*/
                /*Creamos el socket de datagrama*/
                DatagramSocket cl = new DatagramSocket();
                System.out.print("\nEscriba un mensaje: ");
                /*Flujo de entrada*/
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String mensaje = br.readLine();
                /*Pasamos el mensaje a la variable de tipo byte*/
                byte [] b = mensaje.getBytes();
                /*Obtenemos la cantidad de paquetes que se van a enviar*/
                num_datag = (int)Math.floor(b.length/50) + 1;
                data = String.valueOf(num_datag);
                b = data.getBytes();
                /*Creamos el DatagramPacket para enviar paquetes*/
                DatagramPacket p = new DatagramPacket(b,b.length, InetAddress.getByName(dst),pto);
                /*Enviamos la cantidad de paquetes que se van a enviar de acuerdo
                 a la longitud del mensaje*/
                cl.send(p);
                /*Validación en caso de que sea un solo paquete*/
                if(num_datag>1){
                    /*Iteración de acuerdo al número de paquetes*/
                    for (int i=0;i<num_datag-1;i++){
                        /*Creando subcadenas de longitud 50 para su envio*/
                        String sub_mensaje = mensaje.substring(i*50, ((i+1)*50)-1);
                        /*Pasando a bytes la subcadena*/
                        b = sub_mensaje.getBytes();
                        /*Creamos el DatagramPacket para enviar*/
                        p = new DatagramPacket(b,b.length, InetAddress.getByName(dst),pto);
                        /*Envio del mensaje*/
                        cl.send(p);
                    }
                    /*Aqui se realiza el envio del ultimo paquete*/
                    /*Creamos la subcadena*/
                    String sub_mensaje = mensaje.substring((num_datag-1)*50-1, mensaje.length());
                    /*Pasamos a bytes la cadena*/
                    b = sub_mensaje.getBytes();
                    /*Creamos el DatagramPacket para enviarla*/
                    p = new DatagramPacket(b,b.length, InetAddress.getByName(dst),pto);
                    /*Envio de la cadena*/
                    cl.send(p);
                }else{
                    /*En caso de que solo sea un paquete, aqui se realiza su envio*/
                    /*Pasamos a bytes la cadena*/
                    b=mensaje.getBytes();
                    /*Creamos el DatagramPacket para enviarla*/
                    p = new DatagramPacket(b,b.length, InetAddress.getByName(dst),pto);
                    /*Envio de la cadena*/
                    cl.send(p);
                }
                System.out.println("Paquetes enviados: " + num_datag);
                /*FIN DEL BLOQUE DE ENVIO DE MENSAJES*/
                /*INICIO DEL BLOQUE DE RECEPCION DE MENSAJES*/
                /*Creamos el DatagramPacket para recibir paquetes, en este caso de 50*/
                DatagramPacket p_rec = new DatagramPacket(new byte[50],50);
                /*Esperamos el mensaje que nos indica la cantidad de paquetes recibidos*/
                cl.receive(p_rec);
                /*Pasamos la cantidad a un String*/
                data = new String(p_rec.getData(),0,p_rec.getLength());
                /*Pasamos la cantidad a una variable de tipo Int*/
                num_datag = Integer.parseInt(data);
                System.out.println("\nMensaje recibido: ");
                /*Iteramos de acuerdo a la cantidad de paquetes recibidos*/
                for(int i=0; i<num_datag;i++){
                    /*Esperamos el mensaje*/
                    cl.receive(p_rec);
                    /*Pasamos el mensaje a un String*/
                    String msj = new String(p_rec.getData(),0,p_rec.getLength());
                    /*Imprimimos el mensaje*/
                    System.out.print(msj);
                }
                System.out.println("\nPaquetes recibidos: " + num_datag);
                /*FIN DEL BLOQUE DE RECEPCION DE MENSAJES*/
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
