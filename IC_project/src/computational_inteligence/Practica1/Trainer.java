/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computational_inteligence.Practica1;
import java.util.ArrayList;
/**
 *
 * @author Fernando Roldán Zafra
 */
public class Trainer {
    private int n;
    private ArrayList<Perceptron> red;
    private double datos_train[][];
    private double datos_test[][];
    private int etiquetas_train[];
    private int etiquetas_test[];
    
    public Trainer (int n, double datos_train[][], double datos_test[][], int etiquetas_test[], int etiquetas_train[]){
        this.red = new ArrayList();
        this.datos_train = datos_train;
        this.datos_test = datos_test;
        this.etiquetas_test = etiquetas_test;
        this.etiquetas_train = etiquetas_train;
    }
    
    public void crea_red(){
        Perceptron p = null;
        for(int i = 0; i <= 9; i++){
            p = new Perceptron(i);
            this.red.add(p);
        }
    }
    
    public void modifica_pesos(){
    
    }
    
    
    public void entrena(){
    
    }
    
    public void test(){
    
    }
    
}
