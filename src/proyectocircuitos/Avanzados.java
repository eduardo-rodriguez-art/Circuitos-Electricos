package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author joseeduardorodriguezreyes
 */
// UserConnectedListener, UserRecibeListener
public class Avanzados extends JFrame implements ActionListener, Archivos,UserConnectedListener, UserRecibeListener{
    String v1,v2,v3,v4;
    
    //* barras de menu
    JMenuBar menu;
    JMenu file;
    JMenu system;
    JMenu help;
    //* menu Items
    JMenuItem archivo;
    JMenuItem openFile;
    JMenuItem settings;
    JMenuItem updates;
    JMenuItem sistemaOperativo;
    JMenuItem miLicencia;
    JMenuItem aboutOf;
    JPanel principal;
    JPanel botones;
    JPanel barraComponentes;
    
    //* botones
    JButton calcular;
    JButton limpiar;
    JButton licencia;
    JButton regresar;
    
    JButton calcularCliente;
    JButton myServer;
    JButton obtenerValores;
    
    //* informacion que viene del cliente
    JLabel txtTitleServer;
    JLabel txtVacio;
    JLabel txtRest1;
    JLabel txtRest2;
    JLabel txtRest3;
    JLabel txtVolt;
    JTextField rest1;
    JTextField rest2;
    JTextField rest3;
    JTextField myVolt;
    //* calcular elementos que vienen del cliente
    JLabel txtResistenciaTotal;
    JLabel txtINTENSIDADTOTAL;
    JTextField TOTALRESISTENCIA;
    JTextField INTENSIDADTOTAL;
    // buttonGroup
    ButtonGroup grupoBotones;
    JRadioButton serie;
    JRadioButton paralelo;
    
    BorderLayout borde;
    MenuItemActions menuOptions = new MenuItemActions();
    
    
    static UserEngine user;
    static ServidorEngine server = new ServidorEngine(5000);
    static ArrayList<UserEngine> usuarios;
    
    //UserChatGui chat;
    String cadenaTotal;
    public Avanzados(){
        
    }
    
    RelacionClienteInterfaz red;
    /*
    public void AgregarCliente(Cliente Ce){
        if (client==null){

            client=new ArrayList();
        }
        client.add(Ce);
    }
    }*/
    
    public void init(){
        //chat = new UserChatGui();
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
        archivo = new JMenuItem("Guardar Archivo");
        openFile = new JMenuItem("Abrir Archivo");
        settings = new JMenuItem("Preferencias");
        updates = new JMenuItem("Actualizaciones");
        sistemaOperativo = new JMenuItem("Sistema Operativo");
        aboutOf = new JMenuItem("Acerca de");
        miLicencia = new JMenuItem("Licencia");
        
        regresar = new JButton("Regresar");
        limpiar = new JButton("Limpiar");
        calcular = new JButton("Calcular");
        
        
        //INICIAR ELEMENTOS QUE VIENEN DEL SERVIDOR
        txtTitleServer = new JLabel(" Estos Elementos vienen del servicio Cliente");
        txtVacio = new JLabel("Informacion del servidor");
        txtRest1 = new JLabel("Resistencia 1 ");
        txtRest2 = new JLabel("Resistencia 2 ");
        txtRest3 = new JLabel("Resistencia 3 ");
        txtVolt = new JLabel("Voltaje suministrado ");
        rest1 = new JTextField();
        rest1.setColumns(8);
        rest2 = new JTextField();
        rest2.setColumns(8);
        rest3 = new JTextField();
        rest3.setColumns(8);
        myVolt = new JTextField();
        myVolt.setColumns(8);
        //* iniciar elementos para calcular lo que viene del servidor
        txtResistenciaTotal = new JLabel("Resistencia Total Calculada");
        txtINTENSIDADTOTAL = new JLabel("Intensidad Calculada");
        TOTALRESISTENCIA = new JTextField();
        INTENSIDADTOTAL = new JTextField();
        
        grupoBotones = new ButtonGroup();
        serie = new JRadioButton("Serie");
        paralelo = new JRadioButton("Paralelo");
        grupoBotones.add(serie);
        grupoBotones.add(paralelo);
        
        //cambiarlo a intensidad, para calcularla dependiento de las respuestas del cliente
        calcularCliente = new JButton("Realizar calculo");
        myServer = new JButton("Conectar con el servidor");
        obtenerValores = new JButton("Registrar valores del cliente");
        
        principal = new JPanel();
        principal.setBackground(Color.orange);
        botones = new JPanel();
        barraComponentes = new JPanel();
        barraComponentes.setBackground(Color.red);
        
        file.setBackground(Color.red);
        system.setBackground(Color.red);
        help.setBackground(Color.red);
        file.add(archivo);
        file.add(openFile);
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
        botones.add(limpiar);
        botones.add(calcular);
        
        //* añadir a la paleta de componentes
        barraComponentes.setLayout(new GridLayout(3,1));
        barraComponentes.add(calcularCliente);
        barraComponentes.add(myServer);
        barraComponentes.add(obtenerValores);
        
        principal.setLayout(new GridLayout(8,2));
        principal.add(txtTitleServer);
        principal.add(txtVacio);
        principal.add(txtRest1);
        principal.add(rest1);
        principal.add(txtRest2);
        principal.add(rest2);
        principal.add(txtRest3);
        principal.add(rest3);
        principal.add(txtVolt);
        principal.add(myVolt);
        principal.add(serie);
        principal.add(paralelo);
        principal.add(txtResistenciaTotal);
        principal.add(TOTALRESISTENCIA);
        principal.add(txtINTENSIDADTOTAL);
        principal.add(INTENSIDADTOTAL);
        
        setLayout(borde);
        add(menu, BorderLayout.NORTH);
        add(principal, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
        add(barraComponentes, BorderLayout.WEST);
        
        //* acciones del listener
        regresar.addActionListener(this);
        limpiar.addActionListener(this);
        calcular.addActionListener(this);
        archivo.addActionListener(this);
        openFile.addActionListener(this);
        settings.addActionListener(this);
        updates.addActionListener(this);
        sistemaOperativo.addActionListener(this);
        miLicencia.addActionListener(this);
        aboutOf.addActionListener(this);
        
        //acciones de los calculos
        calcularCliente.addActionListener(this);
        myServer.addActionListener(this);
        obtenerValores.addActionListener(this);
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
        System.out.println("Usuario conectado");
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
            
            red = new RelacionClienteInterfaz(res1,res2,res3,volta);
            red.Imprime();
            cadenaTotal = red.getR1()+red.getR2()+red.getR3()+red.getV();
            UE.sendMessage("Agregados");
            JOptionPane.showMessageDialog(null, "Recibido", "Valores Agregados", JOptionPane.OK_OPTION);       
        }
        else{
            UE.sendMessage("Comando no reconocido");
        }
        
    }
    
    public static void Registro(String c1, String c2, String c3, String c4){
        
    }
    //RelacionClienteInterfaz b;
    /*public void RealizarCalculo(){
        
        double z1=0,z2=0,z3=0,z4=0;
        double resTotal=0.0, intensity;
        try {
            z1 = Double.parseDouble(b.getR1());
            z2 = Double.parseDouble(b.getR2());
            z3 = Double.parseDouble(b.getR3());
            z4 = Double.parseDouble(b.getV()); //voltaje
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Algun valor es nulo", "Valores nulos", JOptionPane.ERROR_MESSAGE);
        }
        
        Calculos cal = new Calculos(z1,z2,z3);
        
        if(serie.isSelected()){
            resTotal = cal.ResistenciaCircuitoSerie();
        }
        else if(paralelo.isSelected()){
            resTotal = cal.ResistenciaCircuitoParalelo();
        }
        
        intensity = Intensidad(z4, resTotal);
        System.out.println("intensidad "+intensity);
        System.out.println("res total "+resTotal);
        //TOTALRESISTENCIA.setText(String.valueOf(resTotal));
        //INTENSIDADTOTAL.setText(String.valueOf(intensity));
    }*/
    
    public double Intensidad(double voltaje, double resTotal){
        double inten = 0;
        try {
            inten = voltaje/resTotal;
        }catch(ArithmeticException a){
            JOptionPane.showMessageDialog(this, "La resistencia total es 0", "Resistencia total nula", JOptionPane.WARNING_MESSAGE);
        }
        
        return inten;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(regresar)){
            Regresar();
        }
        else if(e.getSource().equals(myServer)){
            AparecerCliente();
            AparecerServidor();
        }else if(e.getSource().equals(obtenerValores)){
            //Rellenar();
            //Registrar();
            /*rest1.setText(getV1());
            rest2.setText(getV2());
            rest3.setText(getV3());
            myVolt.setText(getV4());*/
            JOptionPane.showMessageDialog(null, "Recibido", "Parametros recibidos", JOptionPane.OK_OPTION);
        }
        else if(e.getSource().equals(calcularCliente)){
            System.out.println("CALCULOS");
            //RealizarCalculo();
            System.out.println(cadenaTotal);
        }
        else if(e.getSource().equals(limpiar)){
            Limpiar();
        }else if(e.getSource().equals(calcular)){
            //RealizarCalculo();
            System.out.println(cadenaTotal);
        }else if(e.getSource().equals(archivo)){
            GuardarArchivo();
        }else if(e.getSource().equals(openFile)){
            AbrirArchivo();
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
    
    // Implementacion de la Interfaz, cada metodo actua de manera diferente
    // Falta su implementacion
    @Override
    public void GuardarArchivo() {
        System.out.println("Se guardo");    
    }

    @Override
    public void AbrirArchivo() {
        System.out.println("Se abrio el archivo");
    }
    
    public void Limpiar() {
        rest1.setText(" ");
        rest2.setText(" ");
        rest3.setText(" ");
        myVolt.setText(" ");
        TOTALRESISTENCIA.setText("");
        INTENSIDADTOTAL.setText("");
    }

    
}
