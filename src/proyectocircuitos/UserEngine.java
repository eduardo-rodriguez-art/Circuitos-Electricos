/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class UserEngine extends Thread{
    DataInputStream In;
    DataOutputStream Out;
    UserRecibeListener UserRecibe=null;
    UserEngine(Socket S){
        try{
            In=new DataInputStream(S.getInputStream());
            Out=new DataOutputStream(S.getOutputStream());      
        }
        catch(IOException e){
            
        }
        
    }
    
    void setUserRecibeListener(UserRecibeListener UserRecibe){
        this.UserRecibe=UserRecibe;
    }
    
    void sendMessage(String S){
        try{
            Out.writeUTF(S);
        }
        catch(IOException e){
            
        }
    }
    
    public void run(){
        try{
            while(true){
                String S=In.readUTF();
                if (UserRecibe!=null){
                    UserRecibe.UserRecibeEvent(this, S);
                }
            }           
        }
        catch(IOException e){
            
        }       
    }
}
