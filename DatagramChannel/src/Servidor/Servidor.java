/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Datagram Channel
  Servidor
*/
package Servidor;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Servidor {
    
    public static final int PUERTO = 7;
    public static final int TAM_MAXIMO = 65507;
    
    public static void main(String[] args) {
        int port = PUERTO;
        try {
            /*Creamos un canal*/
            DatagramChannel canal = DatagramChannel.open();
            /*Ponemos el bloqueo en falso*/
            canal.configureBlocking(false);
            /*Creamos un socket ligado al canal*/
            DatagramSocket socket = canal.socket();
            /*Establecemos la dirección*/
            SocketAddress dir = new InetSocketAddress(port);
            /*Ligamos el canal*/
            socket.bind(dir);
            /*Creamos un selector*/
            Selector selector = Selector.open();
            /*Registra el canal en modo lectura*/
            canal.register(selector, SelectionKey.OP_READ);
            /*Creamos un buffer de bytes*/
            ByteBuffer buffer = ByteBuffer.allocateDirect(TAM_MAXIMO);
            while(true){
                /*Seleccion de keys con una espera de 5 segundos*/
                selector.select(5000);
                /*Obtenemos el conjunto de keys seleccionadas*/
                Set sk = selector.selectedKeys();
                /*Creamos un iterador*/
                Iterator it = sk.iterator();
                /*Verificamos que tengamos keys*/
                while(it.hasNext()){
                    /*Pasamos el valor de la key selecciona*/
                    SelectionKey key = (SelectionKey) it.next();
                    /*Removemos ese valor*/
                    it.remove();
                    /*Verificamos que se pueda leer esa key*/
                    if(key.isReadable()){
                        /*Limpiamos el buffer*/
                        buffer.clear();
                        /*Recibimos el buffer*/
                        SocketAddress client = canal.receive(buffer);
                        buffer.flip();
                        /*Obtenemos el valor del buffer*/
                        int eco = buffer.getInt();
                        /*Si el valor es 1000 es que ya se recibieron todos los paquetes*/
                        if(eco==1000){
                            /*Cerramos el canal*/
                            canal.close();
                            /*Terminamos la ejecución*/
                            System.exit(0);
                        }else{
                            /*Escribimos el dato recibido*/
                            System.out.println("Dato leido: " + eco);
                            buffer.flip();
                            /*Envia el datagrama através del canal*/
                            canal.send(buffer, client);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}