/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Datagram Channel
  Cliente
*/
package Cliente;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Cliente {
    public static final int PUERTO = 7;
    public static final int LIMITE = 100;
    
    public static void main(String[] args) {
        boolean bandera = false;
        SocketAddress remoto = new InetSocketAddress("127.0.0.1", PUERTO);
        try {
            /*Abrimos el canal*/
            DatagramChannel canal = DatagramChannel.open();
            /*Se configura el bloqueo en falso*/
            canal.configureBlocking(false);
            /*Conectamos el canal con el socket*/
            canal.connect(remoto);
            /*Se crea un selector*/
            Selector selector = Selector.open();
            /*Registra el canal con el selector en modo escritura*/
            canal.register(selector, SelectionKey.OP_WRITE);
            /*Creamos un buffer de bytes*/
            ByteBuffer buffer = ByteBuffer.allocateDirect(4);
            int n=0;
            while(true){
                /*Selección de conjunto de keys para I/O con una espera de 5 seg*/
                selector.select(5000);
                /*Obtenemos el conjunto de keys seleccionadas*/
                Set sk = selector.selectedKeys();
                /*Verificamos que tengamos llaves*/
                if(sk.isEmpty() && n == LIMITE || bandera){
                    /*Cerramos el canal*/
                    canal.close();
                    break;
                }else{
                    /*Ceamos un iterador*/
                    Iterator it = sk.iterator();
                    /*Verificamos que tenga elementos*/
                    while(it.hasNext()){
                        /*Pasamos el valor de la key selecciona*/
                        SelectionKey key = (SelectionKey)it.next();
                        /*Removemos ese valor*/
                        it.remove();
                        /*Verificamos que podamos escribir*/
                        if(key.isWritable()){
                            /*Limpiamos el buffer*/
                            buffer.clear();
                            /*Escribe el valor de n en el buffer*/
                            buffer.putInt(n);
                            buffer.flip();
                            /*Se escribe en canal*/
                            canal.write(buffer);
                            System.out.println("Escribiendo el dato: " + n);
                            n++;
                            /*Verificamos que el n sea igual al limite*/
                            if(n == LIMITE){
                                /*Limpiamos el buffer*/
                                buffer.clear();
                                /*Escribimos el valor 1000 en el buffer*/
                                buffer.putInt(1000);
                                buffer.flip();
                                /*Escribimos en el canal*/
                                canal.write(buffer);
                                /*Ponemos la bandera en verdadero*/
                                bandera = true;
                                /*Establece el interes de la key en lectura*/
                                key.interestOps(SelectionKey.OP_READ);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
