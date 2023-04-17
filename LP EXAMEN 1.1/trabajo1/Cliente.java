package trabajo1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;// se utiliza para importar las clases del paquete java.net.

public class Cliente {

    public static void main(String[] args) {//es el método principal de un programa Java que se encarga de iniciar la ejecución del programa y contiene las instrucciones que se ejecutarán al iniciar

        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;//declara una variable llamada "in" de tipo DataInputStream. Esta variable se utiliza para leer datos de un flujo de entrada de datos
        DataOutputStream out;//declara una variable llamada "out" de tipo DataOutputStream. Esta variable se utiliza para escribir datos en un flujo de salida de datos.

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());// crea un nuevo objeto de tipo DataInputStream asociado con un objeto Socket. El objeto Socket, "sc", representa una conexión de red entre el cliente y el servidor.
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente
            out.writeUTF("¡Hola mundo desde el cliente!");

            //Recibo el mensaje del servidor
            String mensaje = in.readUTF();

            System.out.println(mensaje);

            sc.close();

        } catch (IOException ex) {//captura y maneja cualquier excepción de tipo IOException que pueda ocurrir en el bloque de código "try" anterior.
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}