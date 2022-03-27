/*Eduardo Cuevas Solorza
  Aplicaciones para Comunicaciones en Red
  Programa: Envío de archivos multiples
  CLIENTE
*/
package cliente;

import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class Cliente {

    public static void main(String[] args) {
        try {
            /*Flujo de entrada para obtener los datos del servidor*/
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            /*Servidor Localhost*/
            System.out.print("Escriba la dirección del servidor: ");
            String host = br.readLine();
            /*Puerto 1234*/
            System.out.print("\nEscriba el puerto: ");
            int port = Integer.parseInt(br.readLine());
            /*Creación del socket*/
            Socket cl = new Socket(host, port);
            /*Eleccion de archivos*/
            JFileChooser jf = new JFileChooser();
            /*Habilitamos la elección de multiples archivos*/
            jf.setMultiSelectionEnabled(true);
            int r = jf.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File[] f = jf.getSelectedFiles();/*Archivos seleccionados*/
                /*Flujo para enviar datos*/
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                /*Enviamos la cantidad de archivos*/
                dos.writeInt(f.length);
                dos.flush();
                for (int i = 0; i < f.length; i++) {
                    System.out.println("\n-------------------Envio de Archivo " + (i+1) + "-------------------\n");
                    String archivo = f[i].getAbsolutePath();/*Ruta del archivo*/
                    String nombre = f[i].getName();/*Nombre del archivo*/
                    long tam = f[i].length();/*Tamaño del archivo*/
                    /*Flujo para la lectura del archivo*/
                    DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
                    /*Enviamos el nombre y tamaño*/
                    dos.writeUTF(nombre);
                    dos.flush();
                    dos.writeLong(tam);
                    dos.flush();
                    System.out.println("Nombre del archivo:" + nombre);
                    byte[] b = new byte[1024];
                    long enviados = 0;
                    int porcentaje, n;
                    /*Lectura de los datos del archivo y envio por el socket*/
                    while (enviados < tam) {
                        n = dis.read(b);
                        dos.write(b, 0, n);
                        enviados = enviados + n;
                        porcentaje = (int) (enviados * 100 / tam);
                        System.out.print("Enviado: " + porcentaje + "%\r");
                        dos.flush();
                    }
                    System.out.print("\nArchivos enviados " + (i+1) + "/" + f.length + "\n");
                    dis.close();
                }
                dos.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}