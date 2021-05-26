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
import javax.swing.JPanel;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class Avanzados extends Frame implements ActionListener{
    BorderLayout borde;
    //* botones
    JButton regresar;
    JButton l;
    JPanel principal;
    
    public void init(){
        
        borde = new BorderLayout();
        l=new JButton();
        l.setBackground(Color.red);
        regresar = new JButton("Regresar");
        principal = new JPanel();
        
        principal.setLayout(new GridLayout());
        principal.add(l);
        principal.add(regresar);
        
        setLayout(borde);
        add(principal, BorderLayout.CENTER);
        
        //* acciones del listener
        regresar.addActionListener(this);
        
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
            System.out.println("hola");
            Regresar();
        }
    }
    
    PrincipalFrame prf;
    
    public void setPrincipal(PrincipalFrame prf){
        this.prf = prf;
    }
    
}
