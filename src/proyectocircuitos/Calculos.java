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
public class Calculos {

    private double firstResistance;
    private double secondResistance;
    private double thirdResistance;
//TAMBIEN PUEDE SER UN ARREGLO DE RESISTENCIAS

    public Calculos(double firstResistance, double secondResistance, double thirdResistance) {
        this.firstResistance = firstResistance;
        this.secondResistance = secondResistance;
        this.thirdResistance = thirdResistance;
    }
    

    public double getFirstResistance() {
        return firstResistance;
    }

    public double getSecondResistance() {
        return secondResistance;
    }

    public double getThirdResistance() {
        return thirdResistance;
    }
    
    double r1 = getFirstResistance();
    double r2 = getSecondResistance();
    double r3 = getThirdResistance();
    
    public double ResistenciaCircuitoSerie() {
        return (getFirstResistance() + getSecondResistance() + getThirdResistance());
    }

    public double ResistenciaCircuitoParalelo() {
        double denominador = ((1 / getFirstResistance()) + (1 / getSecondResistance()) + (1 / getThirdResistance()));
        double resistenciaTotal = 1 / denominador;
        return resistenciaTotal;
    }
    
}
