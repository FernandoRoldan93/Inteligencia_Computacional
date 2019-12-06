/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational_inteligence.Practica1;

import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *
 * @author Fernando Roldán Zafra
 */
public class Perceptron {
    public int number;
    public double weights[];
    
    Perceptron(int n, int inputs){
        this.number = n;    
        double w;
        weights = new double [inputs+1]; 
        for(int i = 0; i<inputs+1; i++){
            double random = Math.random();
            w = (Math.random() - random) * 0.1 ;
            this.weights[i] = w;
        }
    }
    
    public boolean output(float input[][]){
        boolean activation = false;
        int indice_pesos = 0;
        double junction = 1 * weights[0];
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input.length; j++){
                junction += input[i][j]*weights[indice_pesos];
                indice_pesos++;
            }
        }
        
        activation = (junction>=0)? true:false;
        return activation;
    }

    void modifica_pesos(float[][] data, double learning_rate, boolean err) {
        double error;
        if(err)
            error = 1;
        else
            error = -1;
        int indice_pesos = 1;
        weights[0] += learning_rate * error;
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data.length; j++){
               weights[indice_pesos] = weights[indice_pesos] +  learning_rate * error * data[i][j];
               indice_pesos++;
            }
        }
    }
}
