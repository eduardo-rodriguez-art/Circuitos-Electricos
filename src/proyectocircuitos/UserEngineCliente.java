package proyectocircuitos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class UserEngineCliente extends Thread{
    DataInputStream in;
    DataOutputStream out;
    UserRecibeListenerCliente UserRecibeCliente = null;
    
    public UserEngineCliente(Socket mySock){
        try{
            in = new DataInputStream(mySock.getInputStream());
            out = new DataOutputStream(mySock.getOutputStream());
        }catch(IOException e){
            System.out.println("Error en los flujos de entrada/salida");
        }
    }
    
    // asociar el manejador de eventos al recibir un dato del servidor 
    
    public void setUserRecibeListenerCliente(UserRecibeListenerCliente UserRecibeCliente){
        this.UserRecibeCliente = UserRecibeCliente;
    }
    
    // writeUTF Escribe en el flujo de salida asociado al 
    //canal de comunicacion (envia info al servidor)
    
    public void sendMessage(String S){
        try {
            out.writeUTF(S);
        }catch(IOException e){
            System.out.println("Error en la salida");
        }
    }
    
    //readUTF lee en el flujo de entrada asociado al canal de comunicacion
    // (recibe info del servidor)
    //.UserRecibeEvent -> envia info al manejador del evento
    
    @Override
    public void run(){
        try {
            while (true) {
                String S = in.readUTF();
                if (UserRecibeCliente != null) {
                    UserRecibeCliente.UserRecibeEventCliente(this, S);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de entrada");
        }
    } 
}
