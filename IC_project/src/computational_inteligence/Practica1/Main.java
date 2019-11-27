/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational_inteligence.Practica1;

import static computational_inteligence.Practica1.MNISTDatabase.normalize;
import static computational_inteligence.Practica1.MNISTDatabase.readImages;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Fernando Roldán Zafra
*/

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "D:\\Documentos\\NetBeansProjects\\Inteligencia_computacional\\IC_project\\data\\mnist\\";
        MNISTDatabase.downloadMNIST(url);
        int images_train[][][];
        int images_test[][][];
        images_train = readImages("data\\mnist\\train-images-idx3-ubyte.gz");
        images_test = readImages("data\\mnist\\t10k-images-idx3-ubyte.gz");
        
        ArrayList<float[][]> train_datas = new ArrayList();
        ArrayList<float[][]> test_datas = new ArrayList();
        float data_train[][] = null;
        float data_test[][] = null;
        for(int i = 0; i <= images_train.length; i++){
            data_train = MNISTDatabase.normalize(images_train[i]);  
            data_test = MNISTDatabase.normalize(images_train[i]);
        }
        
        int labels_train[];
        int labels_test[];
		labels_train = MNISTDatabase.readLabels("data\\mnist\\train-labels-idx1-ubyte.gz");
		labels_test = MNISTDatabase.readLabels("data\\mnist\\t10k-labels-idx1-ubyte.gz");
        
        
        
    
    }
}
