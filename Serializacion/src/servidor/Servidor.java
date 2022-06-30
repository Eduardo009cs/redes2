package servidor;

import java.net.*;
import java.io.*;
import persona.Persona;
import persona.Productos;

public class Servidor {
    public static void main(String[] args) {
        try{
            /*Socket del servidor*/
            ServerSocket s = new ServerSocket(1234);
            System.out.println("----------------Conexión con el cliente----------------");
            System.out.println("Esperando conexión...");
            for(;;){
                /*Conexion con el cliente*/
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());
                /*Flujo de entra para obtener el archivo*/
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                /*Datos del archivo*/
                String nombre = dis.readUTF();
                long tam = dis.readLong();
                /*Flujo de salida para crear el archivo*/
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
                long recibidos = 0;
                int porcentaje,n;
                byte[] b = new byte[1024];
                /*Recibiendo y creando archivo  */
                System.out.println("\n----------------Recibiendo archivo----------------");
                while(recibidos<tam){
                    n=dis.read(b);
                    dos.write(b, 0, n);
                    recibidos = recibidos + n;
                    porcentaje = (int)(recibidos*100/tam);
                    System.out.println("Recibido: " + porcentaje + "%\r");
                    dos.flush();
                }
                /*Flujo de lectura para obtener el objeto serializado*/
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombre));
                Productos[] productos = (Productos[])ois.readObject();
                ois.close();
                
                for(Productos p : productos ){
                    System.out.println(p);
                }
                dos.close();
                dis.close();
                cl.close();
                
                
            }   
            
        }catch(Exception e){
            
        }
    }
}
