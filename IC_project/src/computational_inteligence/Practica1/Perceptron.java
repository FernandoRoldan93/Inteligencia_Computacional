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
    public double[] entry;
    public double[] weights;
    public boolean active;
    public double threshold;
    
    Perceptron(int n, double threshold){
        this.number = n;                //Hay que incluir la entrada de bias
        this.threshold = threshold;     // Y el peso tambien
    }
    
    double sigmoid(double z){
        double sigma = 0.0;
            sigma = 1/(1+Math.pow(Math.E, -z));
        return sigma;
    }
    
    public boolean output(int[] input){
        double junction = 1.0;
        boolean activation = false;
        
        for(int i = 1; i <= input.length; i++){
            junction += input[i]*weights[i-1];
        }
        double sigma = sigmoid(junction);
        
        activation = (sigma>=threshold)? true:false;
        this.active = activation;
        return activation;
    }
    
    
}
