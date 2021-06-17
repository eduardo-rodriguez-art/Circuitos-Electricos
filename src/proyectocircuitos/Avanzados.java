package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author joseeduardorodriguezreyes
 */
// UserConnectedListener, UserRecibeListener
public class Avanzados extends JFrame implements ActionListener,UserConnectedListener, UserRecibeListener{
    String v1,v2,v3,v4;
    DecimalFormat decimal = new DecimalFormat("#.000");
    Circuitos circ = new Circuitos();
    
    //* barras de menu
    JMenuBar menu;
    JMenu file;
    JMenu system;
    JMenu help;
    //* menu Items
    JMenuItem settings;
    JMenuItem updates;
    JMenuItem sistemaOperativo;
    JMenuItem miLicencia;
    JMenuItem aboutOf;
    JPanel principal;
    JPanel botones;
    JPanel barraComponentes;
    
    //* botones
    JButton licencia;
    JButton regresar;
    JButton myServer;
    
    //* informacion que viene del cliente
    JLabel txtTitleServer;
    JLabel txtVacio;
    
    BorderLayout borde;
    MenuItemActions menuOptions = new MenuItemActions();
    
    static UserEngine user;
    static ServidorEngine server = new ServidorEngine(5000);
    static ArrayList<UserEngine> usuarios;
    
    public Avanzados(){
        
    }
    
    
    public void init(){
        usuarios = new ArrayList();
        Avanzados adv;
        adv=new Avanzados();
        server.setConnectedListener(adv);
        server.start();
        
        
        this.setTitle("Calculo por Parametros del cliente");
        borde = new BorderLayout();
        
        menu = new JMenuBar();
        file = new JMenu("Archivo");
        system = new JMenu("Sistema");
        help = new JMenu("Ayuda");
        //* menus
        settings = new JMenuItem("Preferencias");
        updates = new JMenuItem("Actualizaciones");
        sistemaOperativo = new JMenuItem("Sistema Operativo");
        aboutOf = new JMenuItem("Acerca de");
        miLicencia = new JMenuItem("Licencia");
        
        regresar = new JButton("Regresar");
        
        
        //INICIAR ELEMENTOS QUE VIENEN DEL SERVIDOR
        txtTitleServer = new JLabel(" Estos Elementos vienen del servicio Cliente");
        txtVacio = new JLabel("Informacion del Cliente");
        //cambiarlo a intensidad, para calcularla dependiento de las respuestas del cliente
        
        myServer = new JButton("Conectar con el servidor");
        
        principal = new JPanel();
        principal.setBackground(Color.orange);
        botones = new JPanel();
        barraComponentes = new JPanel();
        barraComponentes.setBackground(Color.red);
        
        file.setBackground(Color.red);
        system.setBackground(Color.red);
        help.setBackground(Color.red);
        system.add(settings);
        system.add(updates);
        system.add(sistemaOperativo);
        help.add(miLicencia);
        help.add(aboutOf);
        menu.add(file);
        menu.add(system);
        menu.add(help);
        
        //* botones a un panel
        botones.setLayout(new GridLayout(1,4));
        botones.add(regresar);
        
        //* añadir a la paleta de componentes
        barraComponentes.setLayout(new GridLayout(1,1));
        
        barraComponentes.add(myServer);
        
        principal.setLayout(new GridLayout(2,1));
        principal.add(txtTitleServer);
        principal.add(txtVacio);
        
        setLayout(borde);
        add(menu, BorderLayout.NORTH);
        add(principal, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
        add(barraComponentes, BorderLayout.WEST);
        
        //* acciones del listener
        regresar.addActionListener(this);
        
        settings.addActionListener(this);
        updates.addActionListener(this);
        sistemaOperativo.addActionListener(this);
        miLicencia.addActionListener(this);
        aboutOf.addActionListener(this);
        
        //acciones de los calculos
        myServer.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 300);
        setVisible(true);
    }
    
    public void Regresar(){
        prf.setVisible(true);
        dispose();
    }
    
    public void AparecerServidor(){
        /*ServidorGui sg = new ServidorGui();
        sg.Init();*/
        
    }

    public void AparecerCliente(){
        ClienteGui myClient = new ClienteGui();
        myClient.Init();
    }
    
    PrincipalFrame prf;
    public void setPrincipal(PrincipalFrame prf){
        this.prf = prf;
    }
    
    @Override
    public void UserConnectedEvent(UserEngine UE) {
        usuarios.add(UE);
        JOptionPane.showMessageDialog(this, "Usuario Conectado", "Conexion realizada", JOptionPane.OK_OPTION);
        //System.out.println("Usuario conectado");
        UE.setUserRecibeListener(this);
        UE.start();
    }
    
    ///** este es el metodo que recoge la informacion del cliente!
    @Override
    public void UserRecibeEvent(UserEngine UE, String S) {
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
            
            AgregarDatos ad;
            ad = new AgregarDatos();
            ad.setValor1(res1);
            ad.setValor2(res2);
            ad.setValor3(res3);
            ad.setValor4(volta);
            
            circ.AgregarElemento(ad);
            double w1, w2, w3, b1, inte1, inte2;
            String cad = "Resistencia 1: "+ad.getValor1()+"\n"+"Resistencia 2: "+ad.getValor2()+"\n"+"Resistencia 3: "+ad.getValor3()+"\n"+"Voltaje: "+ad.getValor4();          
            
            w1 = Double.parseDouble(ad.getValor1());
            w2 = Double.parseDouble(ad.getValor2());
            w3 = Double.parseDouble(ad.getValor3());
            b1 = Double.parseDouble(ad.getValor4());
            
            double resisSerie = w1+w2+w3;
            double suma1 = ((1/w1)+(1/w2)+(1/w3));
            double resisParalelo = 1/suma1;
            inte1 = b1/resisSerie;
            inte2 = b1/resisParalelo;
            String i = "La Intensidad total en serie es: "+decimal.format(inte1)+" Amp\nLa Intensidad total en paralelo es: "+decimal.format(inte2)+" Amp";
            String r = "La Resistencia Total en serie es: "+decimal.format(resisSerie)+" Ohms\nLa Resistencia Total en paralelo es: "+decimal.format(resisParalelo)+" Ohms";
            JOptionPane.showMessageDialog(null, "Recibido", "Valores Agregados", JOptionPane.OK_OPTION);       
            JOptionPane.showMessageDialog(this, cad+"\n"+r+"\n"+i , "Resultados", JOptionPane.OK_OPTION);
            
        }
        else{
            UE.sendMessage("Comando no reconocido");
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(regresar)){
            Regresar();
        }
        else if(e.getSource().equals(myServer)){
            AparecerCliente();
            AparecerServidor();
        
        }else if(e.getSource().equals(settings)){
            menuOptions.setSettings();
        }else if(e.getSource().equals(updates)){
            menuOptions.Actualizaciones();
        }else if(e.getSource().equals(sistemaOperativo)){
            menuOptions.Operativo();
        }else if(e.getSource().equals(aboutOf)){
            menuOptions.AcercaDe();
        }else if(e.getSource().equals(miLicencia)){
            menuOptions.Licencia();
        }
    }
    

}
