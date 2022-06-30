package cliente;

import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import persona.Persona;
import persona.Productos;

public class Cliente {
    public static void main(String[] args) {
        try {
            /*Instancia a la clase*/
            //Persona persona = new Persona("Juan","Castillo","Perez",25,84.6,(float)179.6);
            Productos[] producto = new Productos[6];
            producto[0] = new Productos(1,"Xiaomi Poco X3",5899,"Pantalla de 6.67 pulgadas\n.Camara Cuadruple.\nBateria 5160 mAh",2);
            producto[1] = new Productos(2,"Samsung A52",7848,"Pantalla de 6.5 pulgadas FHD+ Super AMOLED Infinity-O Display de 90Hz.",2);
            producto[2] = new Productos(3,"Samsung A71",9499,"Cámara frontal 32MP\nPantalla Infinity-O de 6,7”, Super AMOLED\nBatería de 4,500 mAh",2);
            producto[3] = new Productos(4,"Xiaomi Redmi Note 10",6199,"Pantalla de 6.67 pulgadas\nMemoria RAM 6GB\nBatería de 5,020 mah",2);
            producto[4] = new Productos(5,"iPhone 11",12999,"Pantalla de 6.1 pulgadas con liquid retina\nSistema operativo iOS 13\nResolución de 1792 x 828",2);
            producto[5] = new Productos(6,"Samsung A32",5996,"Pantalla de 6.4 pulgadas\nCámara 64+8+5+5 MP\nBatería de 5,000 mAh con carga rápida 15W",2);
            /*Flujo de entrada para los datos del servidor*/
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----------------Conexión con el servidor----------------");
            System.out.print("Dirección del servidor: ");
            String host = br.readLine();
            System.out.print("Puerto del servidor: ");
            int port = Integer.parseInt(br.readLine());
            /*Conexion con el servidor*/
            Socket cl = new Socket(host,port);
            /*Objeto sin serializar*/
            System.out.println("\n----------------Objeto sin serializar----------------");
             for(Productos p : producto ){
                    System.out.println(p);
                }
            /*Flujo de salida para crear el archivo con el objeto serializado*/
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cliente/archivo_serializado.txt"));
            /*Escritura del objeto*/
            oos.writeObject(producto);
            /*Flujo de entrada para obtener el objeto serializado*/
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cliente/archivo_serializado.txt"));
            Productos[] p = (Productos[])ois.readObject();
            System.out.println("\n----------------Objeto serializado----------------");
            //for(Productos pr : p ){
                    System.out.println(p);
              //  }
            /*Seleccion del archivo*/
            JFileChooser jf = new JFileChooser();
            File f = new File(new File("cliente/archivo_serializado.txt").getCanonicalPath());
            jf.setSelectedFile(f);
            File f1 = jf.getSelectedFile();
            /*Datos del archivo*/
            String archivo = f1.getAbsolutePath();
            String nombre = f1.getName();
            long tam = f1.length();
            /*Flujo de lectura del archivo*/
            DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
            /*Flujo de salida para enviar los datos al servidor*/
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            /*Enviando los datos del archivo*/
            dos.writeUTF(nombre);
            dos.flush();
            dos.writeLong(tam);
            dos.flush();
            byte [] b = new byte[1024];
            long enviados = 0;
            int porcentaje, n;
            /*Envio del archivo*/
            System.out.println("\n----------------Enviando archivo----------------");
            while(enviados<tam){
                n = dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                enviados = enviados + n;
                porcentaje = (int)(enviados*100/tam);
                System.out.println("Enviado: " + porcentaje + "%\r");
            }
            System.out.println("\n----------------Archivo enviado----------------");
            ois.close();
            oos.close();
            dis.close();
            dos.close();
            cl.close();
        } catch (Exception e) {
        }
    }
}
