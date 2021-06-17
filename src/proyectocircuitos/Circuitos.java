/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

import java.util.ArrayList;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class Circuitos {
    private ArrayList<AgregarDatos> elementos;
    
    public Circuitos(){
        elementos = new ArrayList();
    }
    
    public void AgregarElemento(AgregarDatos dato){
        if(elementos==null){
            elementos = new ArrayList();
        }
        elementos.add(dato);
    }
    
    public int ObtenerValores(){
        return elementos.size();
    }
}
