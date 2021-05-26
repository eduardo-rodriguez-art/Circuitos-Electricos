/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class PrincipalFrame extends Frame implements ActionListener{
    BorderLayout borde;
    JTextArea imagen;
    JMenuBar menu;
    JMenu file;
    JMenu edit;
    JMenu ayuda;
    JMenuItem newFile;
    JMenuItem open;
    JMenuItem salir;
    JMenuItem save;
    JMenuItem delete;
    JMenuItem cut;
    JMenuItem paste;
    JPanel norte;
    JPanel sur;
    JButton basico;
    JButton avanzado;
    JButton exit;
    JSeparator linea;
    
    public void Init(){
        
        
        imagen = new JTextArea();
        imagen.setBackground(Color.red);
        borde = new BorderLayout();
        
        menu = new JMenuBar();
        file = new JMenu("Nuevo");
        edit = new JMenu("Editar");
        ayuda = new JMenu("Salir");
        
        newFile = new JMenuItem("Nuevo");
        open = new JMenuItem("Abrir");
        save = new JMenuItem("Guardar");
        salir = new JMenuItem("Salir");
        delete = new JMenuItem("Eliminar");
        cut = new JMenuItem("Cortar");
        paste = new JMenuItem("Pegar");
        
        linea = new JSeparator();
        
        norte = new JPanel();
        norte.setLayout(new FlowLayout());
        sur = new JPanel();
        sur.setLayout(new FlowLayout());
        basico = new JButton("Circuito Basico");
        avanzado = new JButton("Circuito Avanzado");
        exit = new JButton("Salir");
        
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(delete);
        file.add(linea);
        file.add(salir);
        
        edit.add(cut);
        edit.add(paste);
        
        menu.add(file);
        menu.add(edit);
        menu.setForeground(Color.red);
        
        norte.add(imagen);
        sur.add(basico);
        sur.add(avanzado);
        sur.add(exit);
        
        //** botones sobre el cual aplica el listener
        exit.addActionListener(this);
        basico.addActionListener(this);
        avanzado.addActionListener(this);
        
        add(menu, BorderLayout.NORTH);
        add(norte,BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
        
        setSize(500, 220);
        
        
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(exit)){
            Salir();
        }else if(e.getSource().equals(basico)){
            Basicos b = new Basicos();
            b.iniciar();
            b.setPrincipal(this);
            b.setVisible(true);
            setVisible(false);
        }else if(e.getSource().equals(avanzado)){
            Avanzados ad = new Avanzados();
            ad.init();
            ad.setPrincipal(this);
            ad.setVisible(true);
            setVisible(false);
        }
    }

    public void Salir(){
        dispose();
    }
    
}
