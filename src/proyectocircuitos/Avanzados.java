package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class Avanzados extends Frame implements ActionListener, Archivos{
    
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
    
    JButton resistencia;
    JButton voltaje;
    
    BorderLayout borde;
    MenuItemActions menuOptions = new MenuItemActions();
    
    public void init(){
        
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
        // botones de componentes
        resistencia = new JButton("Resistencia");
        voltaje = new JButton("Voltaje");
        
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
        barraComponentes.setLayout(new GridLayout(2,1));
        barraComponentes.add(resistencia);
        barraComponentes.add(voltaje);
        
        principal.setLayout(new GridLayout());
        
        
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
        
        setSize(500, 300);
        setVisible(true);
    }
    
    public void Regresar(){
        prf.setVisible(true);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(regresar)){
            Regresar();
        }else if(e.getSource().equals(limpiar)){
            System.out.println("limpia");
        }else if(e.getSource().equals(calcular)){
            System.out.println("calcula");
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
    
    PrincipalFrame prf;
    
    public void setPrincipal(PrincipalFrame prf){
        this.prf = prf;
    }

    //* Implementacion de la Interfaz, cada metodo actua de manera diferente
    // Falta su implementacion
    @Override
    public void GuardarArchivo() {
        System.out.println("Se guardo");    
    }

    @Override
    public void AbrirArchivo() {
        System.out.println("Se abrio el archivo");
    }
    
}
