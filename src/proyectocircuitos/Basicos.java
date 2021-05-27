package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
public class Basicos extends Frame implements ActionListener, Archivos{
    //* menus
    JMenuBar menu;
    JMenu file;
    JMenu system;
    JMenu help;
    JPanel norte;
    JPanel sur;
    JPanel subSur;
    JPanel subNort;
    JPanel principal;
    JPanel botones;
    //* botones
    JButton calcular;
    JButton dibujar;
    JButton limpiar;
    JButton regresar;
    JButton licencia;
    
    JMenuItem archivo;
    JMenuItem openFile;
    JMenuItem settings;
    JMenuItem updates;
    JMenuItem sistemaOperativo;
    JMenuItem miLicencia;
    JMenuItem aboutOf;
    //*
    JLabel re1;
    JLabel re2;
    JLabel re3;
    JLabel volt;
    JLabel resultado;
    JLabel intensity;
    JLabel totalResis;
    JLabel vacio;
    //* textfield
    JTextField res1;
    JTextField res2;
    JTextField res3;
    JTextField volta;
    JTextField intensidadCalculada;
    JTextField restTotalCalculada;
    
    ButtonGroup grupoBotones;
    JRadioButton serie;
    JRadioButton paralelo;
    BorderLayout borde;
    GridLayout rejilla;
    GridLayout rejilla2;
    JButton dibujo;
    
    DecimalFormat decimal = new DecimalFormat("#.00");
    MenuItemActions menuOptions = new MenuItemActions();
    
    public void iniciar(){
        rejilla = new GridLayout();
        rejilla2 = new GridLayout(3,1);
        
        setVisible(true);
        setTitle("CIRCUITOS ELECTRICOS");
        borde = new BorderLayout();
        
        menu = new JMenuBar();
        file = new JMenu("Archivo");
        system = new JMenu("Sistema");
        help = new JMenu("Ayuda");
        //* menu items
        archivo = new JMenuItem("Guardar Archivo");
        openFile = new JMenuItem("Abrir Archivo");
        settings = new JMenuItem("Preferencias");
        updates = new JMenuItem("Actualizaciones");
        sistemaOperativo = new JMenuItem("Sistema Operativo");
        aboutOf = new JMenuItem("Acerca de");
        miLicencia = new JMenuItem("Licencia");
        
        re1 = new JLabel("Resistencia 1");
        re2 = new JLabel("Resistencia 2");
        re3 = new JLabel("Resistencia 3");
        volt = new JLabel("Voltaje");
        resultado = new JLabel("Resultados");
        intensity = new JLabel("Intensidad");
        totalResis = new JLabel("Resistencia Total");
        vacio = new JLabel();
        //* textfield
        res1 = new JTextField(8);
        res2 = new JTextField(8);
        res3 = new JTextField(8);
        volta = new JTextField(8);
        intensidadCalculada = new JTextField(8);
        restTotalCalculada = new JTextField(8);
        
        calcular=new JButton("Calcular");
        dibujar=new JButton("Dibujar");
        limpiar=new JButton("Limpiar");
        regresar = new JButton("Regresar");
        licencia = new JButton("Licencia");
        
        grupoBotones = new ButtonGroup();
        serie = new JRadioButton("Serie");
        paralelo = new JRadioButton("Paralelo");
        
        file.setForeground(Color.red);
        system.setForeground(Color.red);
        help.setForeground(Color.red);
        
        norte = new JPanel();
        sur = new JPanel();
        principal = new JPanel();
        botones = new JPanel();
        subSur = new JPanel();
        subNort = new JPanel();
        dibujo = new JButton();
        dibujo.setBackground(Color.green);
        
        file.setBackground(Color.red);
        system.setBackground(Color.red);
        help.setBackground(Color.red);
        
        //*radiobutton
        grupoBotones.add(serie);
        grupoBotones.add(paralelo);
        //* meter al menubar sus elementos
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
        
        norte.setBackground(Color.orange);
        sur.setBackground(Color.black);
        subSur.setBackground(Color.gray);
        //meter elementos al panel norte
        
        norte.setLayout(new GridLayout(5,2));
        norte.add(re1);
        norte.add(res1);
        norte.add(re2);
        norte.add(res2);
        norte.add(re3);
        norte.add(res3);
        norte.add(volt);
        norte.add(volta);
        norte.add(serie);
        norte.add(paralelo);
        
        //* a SubSur metemos las etiquetas de resultados
        subSur.setLayout(new GridLayout(3,2));
        subSur.add(resultado);
        subSur.add(vacio);
        subSur.add(intensity);
        subSur.add(intensidadCalculada);
        subSur.add(totalResis);
        subSur.add(restTotalCalculada);
        
        subNort.setLayout(new GridLayout());
        subNort.add(dibujo);
        
        //meter dos paneles, en uno van resultados y enotra la imagen
        sur.setLayout(new GridLayout(2,1));
        sur.add(subNort);
        sur.add(subSur);
        
        //* añadir elementos al panel prncipal
        principal.setLayout(rejilla);
        principal.add(norte);
        principal.add(sur);
        
        //* añadir elementos a la seccion de botones
        botones.setLayout(new GridLayout(1,5));
        botones.add(licencia);
        botones.add(regresar);
        botones.add(limpiar);
        botones.add(dibujar);
        botones.add(calcular);
        
        //ACCIONES DEL ACTIONLISTENER
        licencia.addActionListener(this);
        regresar.addActionListener(this);
        limpiar.addActionListener(this);
        dibujar.addActionListener(this);
        calcular.addActionListener(this);
        archivo.addActionListener(this);
        openFile.addActionListener(this);
        settings.addActionListener(this);
        updates.addActionListener(this);
        sistemaOperativo.addActionListener(this);
        miLicencia.addActionListener(this);
        aboutOf.addActionListener(this);
        
        setLayout(borde);
        add(menu, BorderLayout.NORTH);
        add(principal, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
        //add(sur, new GridLayout(1,3));
        
        setSize(500,300);
        //setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(licencia)||e.getSource().equals(miLicencia)){
            menuOptions.Licencia();
        }else if(e.getSource().equals(limpiar)){
            Limpiar();
        }else if(e.getSource().equals(dibujar)){
            DibujarImagen();
        }else if(e.getSource().equals(calcular)){
            Calculos();
        }else if(e.getSource().equals(regresar)){
            Regresar();
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
        }
    }
    
    public void Regresar(){
        prin.setVisible(true);
        dispose();
    }
    
    public void Calculos(){
        double r1=0, r2=0, r3=0;
        
        try {
            r1 = Double.parseDouble(res1.getText());
            r2 = Double.parseDouble(res2.getText());
            r3 = Double.parseDouble(res3.getText());
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Algun campo de texto esta vacio", "Campos de texto vacios", JOptionPane.ERROR_MESSAGE);
        }
        // se llama al constructor para asiganr valores 
        Calculos calc = new Calculos(r1, r2, r3);

        double resTotal = 0.0;
        if (serie.isSelected()) {
            resTotal = calc.ResistenciaCircuitoSerie();
        } else if (paralelo.isSelected()) {
            resTotal = calc.ResistenciaCircuitoParalelo();
        }

        double voltaje = Double.parseDouble(volta.getText());
        
        double intensidad = Intensidad(voltaje, resTotal);
        restTotalCalculada.setText(String.valueOf(decimal.format(resTotal))+" Ohms");
        intensidadCalculada.setText(String.valueOf(decimal.format(intensidad))+" Amp");
    }
    
    public double Intensidad(double voltaje, double resTotal){
        double inten = 0;
        try {
            inten = voltaje/resTotal;
        }catch(ArithmeticException a){
            JOptionPane.showMessageDialog(this, "La resistencia total es 0", "Resistencia total nula", JOptionPane.WARNING_MESSAGE);
        }
        
        return inten;
    }
    
    public void DibujarImagen(){
        if (serie.isSelected()) {
            ImageIcon iconobtn = new ImageIcon("src/imagenes/series1.PNG");
            dibujo.setIcon(iconobtn);
        } else if (paralelo.isSelected()) {
            ImageIcon iconobtn = new ImageIcon("src/imagenes/paralelo1.png");
            dibujo.setIcon(iconobtn);
        }
    }
    public void Limpiar(){
        res1.setText("");
        res2.setText("");
        res3.setText("");
        volta.setText("");
        intensidadCalculada.setText("");
        restTotalCalculada.setText("");
    }
    
    //* objetos del panel principal
    PrincipalFrame prin;
    
    public void setPrincipal(PrincipalFrame prin){
        this.prin = prin;
    }

    @Override
    public void GuardarArchivo() {
        double r1 = 0, r2 = 0, r3 = 0, voltaje = 0;
        try {
            r1 = Double.parseDouble(res1.getText());
            r2 = Double.parseDouble(res2.getText());
            r3 = Double.parseDouble(res3.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Algún campo está vacio", "Error al Guardar", JOptionPane.ERROR_MESSAGE);
        }
        voltaje = Double.parseDouble(volta.getText());
        String ohm = String.valueOf(restTotalCalculada.getText());
        String amp = String.valueOf(intensidadCalculada.getText());
        
        String name = String.valueOf(JOptionPane.showInputDialog(null, "Como deseas nombra el archivo", "Guardar Archivo", JOptionPane.QUESTION_MESSAGE));
        
        try (BufferedWriter myBuffer = new BufferedWriter(new FileWriter(name+".txt"))) {
            myBuffer.write("Resistencia 1 "+r1);
            myBuffer.write("\nResistencia 2 "+r2);
            myBuffer.write("\nResistencia 3 "+r3);
            myBuffer.write("\nVoltaje "+voltaje);
            myBuffer.write("\nIntensidad "+amp);
            myBuffer.write("\nResistencia Total "+ohm);
            myBuffer.close();
        } catch (IOException ex) {
        }
    }

    @Override
    public void AbrirArchivo() {
        JFileChooser chosen = new JFileChooser();
        chosen.showSaveDialog(openFile);
        
        File archivo = chosen.getSelectedFile();
        
        try {
            String cadena = leerArchivo(archivo);
            JOptionPane.showMessageDialog(principal, "\n"+cadena+"\n", "Abrir archivo", JOptionPane.OK_OPTION);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(principal, "Imposible leer el archivo", "No lectura", JOptionPane.ERROR);
        }
    }
    
    public String leerArchivo(File arch) throws IOException{
        String textoLeido = "";
        try(BufferedReader in = new BufferedReader(new FileReader(arch))){
            String linea;
            while((linea = in.readLine()) != null){
                textoLeido += (linea+"\n"); 
            }
        }
        return textoLeido;
    }
}
