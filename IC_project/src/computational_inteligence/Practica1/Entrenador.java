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
public class Entrenador {
    private ArrayList<Perceptron> red;
    private int imagenes_train[][][];
    private int imagenes_test[][][];
    private int etiquetas_train[];
    private int etiquetas_test[];
    private double learning_rate = 0.001;
    private int tamaño_imagenes = 28*28;

    public Entrenador (int imagenes_train[][][], int imagenes_test[][][], int etiquetas_test[], int etiquetas_train[]){
        this.red = new ArrayList();
        this.imagenes_train = imagenes_train;
        this.imagenes_test = imagenes_test;
        this.etiquetas_test = etiquetas_test;
        this.etiquetas_train = etiquetas_train;
    }

    public void crea_red(){
        Perceptron p = null;
        for(int i = 0; i <= 9; i++){
            p = new Perceptron(i,tamaño_imagenes);
            this.red.add(p);
        }
    }

    public void entrena(){
        boolean activada = false; 

        float data[][];
            for(int i = 0; i < etiquetas_train.length;i++){
                for (Perceptron p: red){
                   data = MNISTDatabase.normalize(imagenes_train[i]);
                   activada =  p.output(data);
                   if(activada && p.number != etiquetas_train[i])
                        p.modifica_pesos(data,learning_rate, false);
                   else if(!activada && p.number == etiquetas_train[i])
                        p.modifica_pesos(data,learning_rate, true);

                   if(i % 20 == 0){
                    System.out.println("Imagen: " + i);
                    if(activada)
                        System.out.println(p.number);
                    }
                }
            }
    }

    public void test(){
        boolean activada = false; 
        boolean acierto = false;
        int cuenta_aciertos = 0;
        int cuenta_fallos = 0;
        int predic_n = Integer.MAX_VALUE;
        float data[][];
        for(int i = 0; i < etiquetas_test.length;i++){
            for (Perceptron p: red){
               data = MNISTDatabase.normalize(imagenes_test[i]);
               activada =  p.output(data);
               if(etiquetas_test[i] == p.number && !activada)
                   cuenta_fallos++;
               else if(etiquetas_test[i] == p.number && activada)
                   cuenta_aciertos++;
            }
        }
        double porcentaje_error = (cuenta_fallos*100)/etiquetas_test.length;
        System.out.println("Se ha obtenido un " + porcentaje_error + "% de error." + 
                "\n Numero de fallos: " + cuenta_fallos + 
                "\n Numero de aciertos: " + cuenta_aciertos);
    }
    
}