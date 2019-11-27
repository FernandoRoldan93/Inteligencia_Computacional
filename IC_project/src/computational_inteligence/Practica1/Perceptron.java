/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational_inteligence.Practica1;

import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.Random;

/**
 *
 * @author Fernando Roldán Zafra
 */
public class Perceptron {
    public int number;
    public double[] weights;
    public boolean active;
    
    Perceptron(int n){
        this.number = n;    
        Random rand = new Random();
        double w;
        for(int i = 0; i<n+1; i++){
            w = rand.nextDouble();
            this.weights[i] = w;
        }
    }
    
    double sigmoid(double z){
        double sigma = 0.0;
            sigma = 1/(1+Math.pow(Math.E, -z));
        return sigma;
    }    
    
    public boolean output(int[] input){
        double junction = 0.0;
        boolean activation = false;
        
        for(int i = 1; i <= input.length; i++){
            junction += input[i]*weights[i-1];
        }
        
        activation = (junction>=0)? true:false;
        this.active = activation;
        return activation;
    }
}
