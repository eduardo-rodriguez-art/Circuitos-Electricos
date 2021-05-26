/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JPanel;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class Avanzados extends Frame implements ActionListener{
    
    //* barras de menu
    JMenuBar menu;
    JMenu file;
    JMenu system;
    JMenu help;
    /*JPanel norte;
    JPanel sur;
    JPanel subSur;
    JPanel subNort;*/
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
    
    
    public void init(){
        
        borde = new BorderLayout();
        
        menu = new JMenuBar();
        file = new JMenu("Archivo");
        system = new JMenu("Sistema");
        help = new JMenu("Ayuda");
        
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
        }
    }
    
    PrincipalFrame prf;
    
    public void setPrincipal(PrincipalFrame prf){
        this.prf = prf;
    }
    
}
