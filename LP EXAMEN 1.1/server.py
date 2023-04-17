import socket   
import threading


host = '127.0.0.1'
port = 55555
    # socket son punto en una red que sse pueden comunicafr entre si.
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # AF_INET significa que bamosa utilizar un socket tipo internet que significa que vamosa utilizar el localhots

server.bind((host, port))   #  bind es para pasar los datos de coneccion que este servidor va tener.
server.listen()  # este escucha a los  conecciones de los clientes.
print(f"Server running on {host}:{port}")   # es para avisar si el servidor esta corriendo o no. 

  # son los 2 listas 
clients = []   # es para almacenar las conecciones de los clientes.
  #  = []   # para almacenar los nombres de cada cliente.
              # --parametros--
def broadcast(message, _client): # esta funcion se va encargar de envia el sms a todo los clientes.
                                # pero --cliente va ser el cliente quien envia el sms para poder indetificar y asi no enviarle su mismmosms.
    for client in clients: #  estos codigos va enviar el sms incluso al quien envio el sms.
        if client != _client:# esto quiere decir que no es igual al cliente que envio sms osea que envie pero no al mismo cliente.
            client.send(message)

def handle_messages(client):# con esta funcion el  servidor va poder manejar los mensajes de cada cliente.
     #---------------------------------
   # todo esto quiere decir que cada cliente va tenber la funcion dedicada a ese cliente y asi todo estos funciones 
    #que estan para cadas uno de los clientes vanga estar corriendo al mismo tiempo para poder manejar los mensajes por separado de cada cliente
    while True:
        try:
            # --variable--
            message = client.recv(1024) # esto es la funcion o limite que va poder leer el mesaje.
            broadcast(message, client) #es optener el broadcast y ponemos los 2 parametros para enviar el sms menos al mismo clientes.
        except:
            index = clients.index(client)  #  Laclientsprobableindex en la lista clients y pasando como argumento el objeto client, se obtiene el índice de la posición de ese cliente en la lista.
            username = username[index] #  Elusernames con el índice almacenado en la variable indexesusername,




            broadcast(f"ChatBot: {username} disconnected".encode('utf-8'), client)
            clients.remove(client)
            username.remove(username)
            client.close()
            break


def receive_connections(): # con esta funcion el servidor va poder aceptar y manejar las conecciones.
   
    while True:
        client, address = server.accept() # esto acepta las conecicones de los clientes de conecten a los datos que pusimos
                                         # y retornan a 2 variable clientes y direccion osea el ip y el port de donde se conectan los clientes.

        client.send("@username".encode("utf-8"))#esta pidiendo su nombre al ususario 
        username = client.recv(1024).decode('utf-8')

        clients.append(client)
        username.append(username)

        print(f"{username} is connected with {str(address)}")#cuando se conecte el usuario hara que se imprima este usuario 

        message = f"ChatBot: {username} joined the chat!".encode("utf-8")#Esta línea de código crea una cadena de mensaje que está formateada para incluir la variable de nombre de usuario y la cadena "ChatBot:"
        broadcast(message, client) #es para enviar un mesaje diciendole que se conecto.
        client.send("Connected to server".encode("utf-8"))

        thread = threading.Thread(target=handle_messages, args=(client,))
        thread.start()


receive_connections()