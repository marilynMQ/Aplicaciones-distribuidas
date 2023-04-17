package trabajo1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;//La declaración "import java.util.logging.Logger" importa la clase Logger del paquete java.util.logging. Logger es una clase que se utiliza para generar registros de mensajes de información, advertencias, errores, etc. en una aplicación.

public class Servidor {

    public static void main(String[] args) {//El método "public static void main(String[] args)" es el punto de entrada para una aplicación Java. Es el método que se ejecuta cuando se inicia una aplicación Java y es requerido en todas las aplicaciones Java.

        ServerSocket servidor = null;//La línea de código "ServerSocket servidor = null;" declara una variable llamada "servidor" de tipo ServerSocket y le asigna un valor inicial de "null".
        Socket sc = null;//La línea de código "Socket sc = null;" declara una variable llamada "sc" de tipo Socket y le asigna un valor inicial de "null".
        DataInputStream in;//La línea de código "DataInputStream in;" declara una variable llamada "in" de tipo DataInputStream.
        DataOutputStream out;//La línea de código "DataOutputStream out;" declara una variable llamada "out" de tipo DataOutputStream.

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {//La sentencia "try" en Java se utiliza para delimitar un bloque de código que se espera que pueda generar excepciones en tiempo de ejecución.
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");//La línea de código "System.out.println("Servidor iniciado");" es una instrucción de salida en Java que muestra el mensaje "Servidor iniciado" en la consola del sistema.

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");//La clase "System" es una clase incorporada de Java que proporciona una variedad de funciones y propiedades del sistema. El método "out" de la clase System se utiliza para acceder a la salida estándar del sistema, que normalmente es la consola. El método "println" se utiliza para imprimir una cadena de texto en la consola, seguida de un salto de línea.
                in = new DataInputStream(sc.getInputStream());//crea un objeto de la clase DataInputStream y lo asocia con el flujo de entrada de un objeto Socket "sc" que representa una conexión de red establecida con un cliente.
                out = new DataOutputStream(sc.getOutputStream());//crea un objeto de la clase DataOutputStream y lo asocia con el flujo de salida de un objeto Socket "sc" que representa una conexión de red establecida con un cliente.

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();//lee una cadena de texto en formato UTF-8 del flujo de entrada asociado con el objeto DataInputStream "in" y la asigna a la variable "mensaje" de tipo String.

                System.out.println(mensaje);//imprime el contenido de la variable "mensaje" en la consola del sistema.

                //Le envio un mensaje
                out.writeUTF("¡Hola mundo desde el servidor!");// Este método es utilizado en este caso para enviar una respuesta del servidor al cliente. El cliente puede leer la respuesta a través de su flujo de entrada asociado con el objeto Socket.

                //Cierro el socket
                sc.close();//cierra la conexión del socket representado por el objeto "sc"
                System.out.println("Cliente desconectado");//se utiliza para imprimir un mensaje en la consola del servidor indicando que el cliente se ha desconectado

            }

        } catch (IOException ex) {//se utiliza para capturar una excepción de tipo IOException que puede ocurrir al trabajar con los flujos de entrada y salida asociados con el objeto Socket y los objetos DataInputStream y DataOutputStream.
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //se utiliza para registrar un mensaje de error en el registro de eventos del servidor.
        //El método "getLogger()" de la clase Logger se utiliza para obtener una instancia del objeto Logger que se utilizará para registrar mensajes de error. En este caso, se utiliza la clase Servidor para obtener el nombre del logger.
        //El método "log()" de la clase Logger se utiliza para registrar el mensaje de error. En este caso, se utiliza el nivel de registro SEVERE para indicar que se trata de un error grave. El tercer argumento es la excepción capturada, que se registra junto con el mensaje de error.

    }

}