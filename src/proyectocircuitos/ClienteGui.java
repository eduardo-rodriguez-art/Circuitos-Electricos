package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    
    //* elementos para enviar al servidor y este realize lo necesario
    JLabel txtRest1;
    JLabel txtRest2;
    JLabel txtRest3;
    JLabel txtVolt;
    JTextField rest1;
    JTextField rest2;
    JTextField rest3;
    JTextField myVolt;
    // buttonGroup
    ButtonGroup grupoBotones;
    JRadioButton serie;
    JRadioButton paralelo;
    
    public void Init()
    {
        
        IPLabel=new JLabel("IP");
        PuertoLabel=new JLabel("Puerto");
        MensajesLabel=new JLabel("Mensajes");
        MensajeLabel=new JLabel("Mensaje");
        IP=new JTextField();
        IP.setColumns(20);
        Puerto=new JTextField();
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
        
        /*grupoBotones = new ButtonGroup();
        serie = new JRadioButton("Serie");
        paralelo = new JRadioButton("Paralelo");
        grupoBotones.add(serie);
        grupoBotones.add(paralelo);*/
        
        Norte1.setLayout(new GridLayout(2,2));
        Norte1.add(IPLabel);
        Norte1.add(PuertoLabel);
        Norte1.add(IP);
        Norte1.add(Puerto);
        Norte.setLayout(new GridLayout(2,1));
        Norte.add(Norte1);
        Norte.add(Conectar);
        add(Norte,BorderLayout.NORTH);
        
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
        //Sur.add(serie);
        //Sur.add(paralelo);
        Sur.add(Enviar);
        /*Sur.add(MensajeLabel);
        Sur.add(Mensaje);
        Sur.add(Enviar);*/
        
        add(Sur,BorderLayout.SOUTH);
        
        Conectar.addActionListener(this);
        Enviar.addActionListener(this);
        setTitle("Cliente");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(Enviar))
        {
            //Mensajes.append("Cliente: "+Mensaje.getText()+"\n");
            //linea del cliente
            //User.sendMessage(Mensaje.getText());
            //Mensaje.setText("");
            String cadenaResistencias = rest1.getText()+"\n"+rest2.getText()+"\n"+rest3.getText()+"\n";
            Mensajes.append(cadenaResistencias+myVolt.getText());
            User.sendMessage(cadenaResistencias + myVolt.getText());
        }
        else if(e.getSource().equals(Conectar)){
            Conectar();
            User.setUserRecibeListener(this);
            User.start();
        }
    } 

    @Override
    public void UserRecibeEvent(UserEngine UE, String S) {
        Mensajes.append("Servidor "+S+"\n");
    }
    
    public void Conectar(){
        int PUERTO = Integer.parseInt(Puerto.getText());
        String DIRECCION = IP.getText();
        Conexion = new Socket();
        IPAddress = new InetSocketAddress(DIRECCION, PUERTO);
        
        try {
            Conexion.connect(IPAddress);
            User = new UserEngine(Conexion);
        }catch(IOException e){
            System.out.println("Sin respuesta");
        }
        
    }

    
}
