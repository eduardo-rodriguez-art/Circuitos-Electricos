/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import javax.swing.JOptionPane;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class MenuItemActions {
    String sSistemaOperativo = System.getProperty("os.name");
    
    /*
    file.add(archivo); //ya estan dados en la interface
        file.add(openFile);//interface
        system.add(settings);
        system.add(updates);
        system.add(sistemaOperativo);
        help.add(miLicencia);
        help.add(aboutOf);
    */
    
    public void setSettings(){
        JOptionPane.showMessageDialog(null, "Version 1.0", "Propiedades", JOptionPane.OK_OPTION);
    }
    public void Actualizaciones(){
        JOptionPane.showMessageDialog(null, "Ninguna Actualizacion", "Actualizaciones", JOptionPane.OK_OPTION);
    }
    public void Operativo(){
        JOptionPane.showMessageDialog(null, " "+sSistemaOperativo, "Sistema Operativo", JOptionPane.OK_OPTION);
    }
    public void Licencia(){
        JOptionPane.showMessageDialog(null, "Todos los derechos reservados 2021", "Licencia", 1);
    }
    public void AcercaDe(){
        JOptionPane.showMessageDialog(null, "Jose Eduardo Rodriguez Reyes", "Desarrollador", 1);
    }
}
