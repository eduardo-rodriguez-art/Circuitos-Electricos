/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class ServidorEngine extends Thread{
    int Port;
    ServerSocket Servidor;
    Socket Aceptar;
    boolean detenerFlag = true;
    UserEngine User;
    UserConnectedListener UserConnected = null;
    
    ServidorEngine(int Port){
        this.Port = Port;
    }
    
    public void setDetenerServidor(boolean f){
        detenerFlag = f;
        try {
            Servidor.close();
        } catch (IOException ex) {
            
        }
    }
    
    public void setConnectedListener(UserConnectedListener UserConnected){
        this.UserConnected = UserConnected;
    }
    
    @Override
    public void run(){
        try {
            Servidor = new ServerSocket(Port);
            detenerFlag = false;
            while(detenerFlag == false){
                Socket S = Servidor.accept();
                if(UserConnected != null){
                    UserEngine UE = new UserEngine(S);
                    UserConnected.UserConnectedEvent(UE);
                }
            }
            Servidor.close();
        } catch (IOException e) {

        }
    }
}
