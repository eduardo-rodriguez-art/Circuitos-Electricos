package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author joseeduardorodriguezreyes
 */



public class ClienteGui extends JFrame implements ActionListener, UserRecibeListener {
    
    JLabel IPLabel;
    JLabel PuertoLabel;
    JLabel MensajesLabel;
    JLabel MensajeLabel;
    JTextField IP;
    JTextField Puerto;
    JTextField Mensaje;
    JTextArea Mensajes;
    JScrollPane Panel;
    JPanel Norte;
    JPanel Norte1;
    JPanel Norte2;
    JPanel Sur;
    JPanel Centro;
    JButton Conectar;
    JButton Enviar;
    
    //* en construccion del protocolo
    UserEngine User;
    Socket Conexion;
    InetSocketAddress IPAddress;
    UserRecibeListener U;
    
    //* elementos para enviar al servidor y este realize lo necesario
    JLabel txtRest1;
    JLabel txtRest2;
    JLabel txtRest3;
    JLabel txtVolt;
    JTextField rest1;
    JTextField rest2;
    JTextField rest3;
    JTextField myVolt;
  
    
    public void Init()
    {
        IPLabel=new JLabel("IP");
        PuertoLabel=new JLabel("Puerto");
        MensajesLabel=new JLabel("Mensajes");
        MensajeLabel=new JLabel("Mensaje");
        IP=new JTextField("");
        IP.setColumns(20);
        Puerto=new JTextField("5000");
        Puerto.setColumns(4);
        Mensaje=new JTextField();
        Mensajes=new JTextArea();
        Panel=new JScrollPane(Mensajes);
        setLayout(new BorderLayout());
        Norte=new JPanel();
        Norte1=new JPanel();
        Centro=new JPanel();
        Sur=new JPanel();
        Conectar=new JButton("Conectar");
        Enviar=new JButton("Enviar");
        
        //* elementos para enviar
        txtRest1 = new JLabel("Resistencia 1 ");
        txtRest2 = new JLabel("Resistencia 2 ");
        txtRest3 = new JLabel("Resistencia 3 ");
        txtVolt = new JLabel("Voltaje suminstrado ");
        rest1 = new JTextField();
        rest1.setColumns(8);
        rest2 = new JTextField();
        rest2.setColumns(8);
        rest3 = new JTextField();
        rest3.setColumns(8);
        myVolt = new JTextField();
        myVolt.setColumns(8);
        
        Norte1.setLayout(new GridLayout(2,2));
        Norte1.add(IPLabel);
        Norte1.add(PuertoLabel);
        Norte1.add(IP);
        Norte1.add(Puerto);
        Norte.setLayout(new GridLayout(2,1));
        Norte.add(Norte1);
        Norte.add(Conectar);
        add(Norte,BorderLayout.NORTH);
        
        
        Mensajes.setText("Enviar valores de resistencias en 3 cifras\nEnviar valor de voltaje en 2 cifras");
        Mensajes.setEditable(false);
        
        Centro.setLayout(new BorderLayout());
        Centro.add(MensajesLabel,BorderLayout.NORTH);
        Centro.add(Panel,BorderLayout.CENTER);
        
        add(Centro,BorderLayout.CENTER);
        
        Sur.setLayout(new GridLayout(6,2));
        Sur.add(txtRest1);
        Sur.add(rest1);
        Sur.add(txtRest2);
        Sur.add(rest2);
        Sur.add(txtRest3);
        Sur.add(rest3);
        Sur.add(txtVolt);
        Sur.add(myVolt);
        Sur.add(Enviar);
        
        add(Sur,BorderLayout.SOUTH);
        
        Conectar.addActionListener(this);
        Enviar.addActionListener(this);
        setTitle("Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setSize(400,400);
        setVisible(true);
        
    }
    //se lo envia al servidor
    public void EnviarCadena() {
        //EnviarValores;100;100;100;30;
        String s = "EnviarValores;"+rest1.getText()+";"+rest2.getText()+";"+rest3.getText()+";"+myVolt.getText()+";";
        User.sendMessage(s);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(Enviar)){
            EnviarCadena();
        }
        else if(e.getSource().equals(Conectar)){
            //System.out.println("Conectado");
            Conectar();
            
        }
    } 

    @Override
    public void UserRecibeEvent(UserEngine UE, String S) {
        //JOptionPane.showMessageDialog(null,"Servidor "+S+"\n" );
        //Mensajes.append("Servidor "+S+"\n");
        System.out.println("Servidor "+S+"\n");
    }
    
    public void Conectar(){
        int PUERTO = Integer.parseInt(Puerto.getText());
        //int PUERTO = 5000;
        String DIRECCION = IP.getText();
        Conexion = new Socket();
        IPAddress = new InetSocketAddress(DIRECCION, PUERTO);
        
        try {
            Conexion.connect(IPAddress);
            User = new UserEngine(Conexion);
            User.setUserRecibeListener(this);
            User.start();
        }catch(IOException e){
            System.out.println("Sin respuesta");
        }
     
    }

}
