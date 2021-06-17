/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author joseeduardorodriguezreyes
 */

public class UserChatGui extends JFrame implements ActionListener, UserRecibeListener, UserConnectedListener{
    
    UserEngine User;
    
    JTextArea Mensajes;
    JTextField Mensaje;
    JLabel Etiqueta;
    JButton Enviar;
    JButton recibir;
    JScrollPane MensajesBar;
    GridLayout Rejilla;
    //RelacionClienteInterfaz ci = new RelacionClienteInterfaz();
    
    //static UserEngine user;
    static ServidorEngine server;
    static ArrayList<UserEngine> usuarios;
    
    void Init(UserEngine User){
        this.User = User;
        usuarios = new ArrayList();
        UserChatGui us;
        us=new UserChatGui();
        server = new ServidorEngine(5000);
        server.setConnectedListener(us);
        server.start();
        
        
        this.setTitle("Recibir Parametros del cliente");
        
        Mensajes=new JTextArea();
        Mensaje=new JTextField();
        Enviar=new JButton("Enviar");
        recibir = new JButton("Recibir valores");
        Rejilla=new GridLayout(4,1);
        Etiqueta=new JLabel("Mensajes");
        
        Mensajes.setEditable(false);
        MensajesBar=new JScrollPane(Mensajes);
        
        setLayout(Rejilla);
        add(Etiqueta);
        add(MensajesBar);
        add(Mensaje);
        add(Enviar);
        
        Enviar.addActionListener(this);
        
        setSize(300,380);
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
            registrar();
            Mensaje.setText("");
        }
    }
    
    public void registrar(){
        String S = Mensajes.getText();
        System.out.println(S);
    }

    @Override
    public void UserConnectedEvent(UserEngine UE) {
        usuarios.add(UE);
        System.out.println("Usuario conectado");
        UE.setUserRecibeListener(this);
        UE.start();
    }
    
    ///** este es el metodo que recoge la informacion del cliente!
    @Override
    public void UserRecibeEvent(UserEngine UE, String S) {
        Mensajes.append("Cliente:\n"+S+"\n");
        int comando;
        //EnviarValores;100;100;100;30;
        comando = S.indexOf("EnviarValores", 0);
        if(comando==0){
            int r1=S.indexOf(";", 14);
            int r2 = S.indexOf(";", r1+1);
            int r3 = S.indexOf(";", r2+1);
            int v = S.indexOf(";", r3+1);
            String res1 = S.substring(14, r1);
            String res2 = S.substring(r1+1, r2);
            String res3 = S.substring(r2+1, r3);
            String volta = S.substring(r3+1, v);
            System.out.println(res1+res2+res3+volta);
            
            JOptionPane.showMessageDialog(null, "Recibido", "Valores Agregados", JOptionPane.OK_OPTION);
            //Avanzados ad = new Avanzados(res1,res2,res3,volta);
            //ad.impr();
        }
        else{
            UE.sendMessage("Comando no reconocido");
        }
        
    }

}
