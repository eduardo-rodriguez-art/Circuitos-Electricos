/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class UserChatGui extends JFrame implements ActionListener, UserRecibeListener{
    
    UserEngine User;
    
    JTextArea Mensajes;
    JTextField Mensaje;
    JLabel Etiqueta;
    JButton Enviar;
    JScrollPane MensajesBar;
    GridLayout Rejilla;
    
    void Init(UserEngine User)
    {
        this.User = User;
        this.setTitle("Pasar parametros");
        
        Mensajes=new JTextArea();
        Mensaje=new JTextField();
        Enviar=new JButton("Enviar");
        Rejilla=new GridLayout(4,1);
        Etiqueta=new JLabel("Mensajes");
        MensajesBar=new JScrollPane(Mensajes);
        
        setLayout(Rejilla);
        add(Etiqueta);
        add(MensajesBar);
        add(Mensaje);
        add(Enviar);
        
        Enviar.addActionListener(this);
        
        setSize(240,320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //lineas de la guia
        User.setUserRecibeListener(this);
        User.start();
        
        setVisible(true);   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Enviar){
            //linea de la guia solo la 65
            User.sendMessage(Mensaje.getText());
            Mensajes.append("Servidor: "+Mensaje.getText()+"\n");
            Mensaje.setText("");
        }
    }

    @Override
    public void UserRecibeEvent(UserEngine UE, String S) {
        Mensajes.append("Cliente: "+S+"\n");
    }
}

