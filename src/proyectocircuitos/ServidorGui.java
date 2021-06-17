/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class ServidorGui extends JFrame implements ActionListener, UserConnectedListener{

    boolean ServidorIniciadoFlag=false;
    /**
     * Segundos pasos
     */
    ServidorEngine Servidor;
    
    JTextField Port;
    JLabel Etiqueta;
    JButton Iniciar;
    FlowLayout Flujo;
    
    public void Init(){
        Port=new JTextField("5000");
        Port.setColumns(4);
        Etiqueta=new JLabel("Número de puerto de escucha");
        Iniciar=new JButton("Iniciar");
        Flujo=new FlowLayout();
        
        setLayout(Flujo);
        
        add(Etiqueta);
        add(Port);
        add(Iniciar);
        
        Iniciar.addActionListener(this);
        
        setSize(250,150);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Iniciar){
            if (ServidorIniciadoFlag==false){
                Iniciar.setText("Escuchando");
                int Puerto;
                try{
                    Puerto=Integer.parseInt(Port.getText());
                    // lineas extra de la guia
                    Servidor = new ServidorEngine(Puerto);
                    //duda
                    Servidor.setConnectedListener(this);
                    ServidorIniciadoFlag = true;
                    Servidor.start();
                }
                catch(NumberFormatException ea){
                    Puerto=5000;
                    Port.setText("5000");
                }
                ServidorIniciadoFlag=true;             
            }
            else{
                Iniciar.setText("Iniciar");
                ServidorIniciadoFlag=false;
                //linea de la guia
                Servidor.setDetenerServidor(true);
            }
        }
    
    }

    @Override
    public void UserConnectedEvent(UserEngine UE) {
        UserChatGui chat = new UserChatGui();
        chat.Init(UE);
    }
    
}
