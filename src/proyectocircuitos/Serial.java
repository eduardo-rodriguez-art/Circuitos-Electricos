/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class Serial extends JFrame implements ActionListener{
    JButton regresar;
    public void Init(){
        regresar = new JButton("REGRESAR");
        add(regresar);
        
        
        regresar.addActionListener(this);
        setSize(500, 300);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(regresar)){
            Regresar();
        }
    }
    
    PrincipalFrame prf;
    
    public void setPrincipal(PrincipalFrame prf){
        this.prf = prf;
    }
    
    public void Regresar(){
        prf.setVisible(true);
        dispose();
    }
}
