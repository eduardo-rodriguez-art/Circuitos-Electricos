/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocircuitos;

/**
 *
 * @author joseeduardorodriguezreyes
 */
public class RelacionClienteInterfaz {
    String r1,r2,r3,v;
    
    public RelacionClienteInterfaz(){
        
    }
    
    public RelacionClienteInterfaz(String r1, String r2, String r3, String v) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.v = v;
        System.out.println("Valores de la clase!");
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public String getR3() {
        return r3;
    }

    public String getV() {
        return v;
    }
    
    
    public void Imprime(){
        System.out.println("Imprimo mis valores");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(v);
    }
    
}
