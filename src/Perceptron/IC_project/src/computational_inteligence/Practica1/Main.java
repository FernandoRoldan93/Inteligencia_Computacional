/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computational_inteligence.Practica1;

import static computational_inteligence.Practica1.MNISTDatabase.readImages;
import java.io.IOException;

/**
 *
 * @author Fernando Roldán Zafra
*/

public class Main {
    public static void main(String[] args) throws IOException {
//        String url = "IC_project\data\\mnist\\";
//        MNISTDatabase.downloadMNIST(url);
        int images_train[][][];
        int images_test[][][];

        images_train = readImages( "IC_project\\data\\mnist\\train-images-idx3-ubyte.gz");
        images_test = readImages("IC_project\\data\\mnist\\t10k-images-idx3-ubyte.gz");
        
        //System.out.println(MNISTDatabase.toString(images_train[0]));
       
        int labels_train[];
        int labels_test[];
		labels_train = MNISTDatabase.readLabels("IC_project\\data\\mnist\\train-labels-idx1-ubyte.gz");
		labels_test = MNISTDatabase.readLabels("IC_project\\data\\mnist\\t10k-labels-idx1-ubyte.gz");
        
        Entrenador entrena = new Entrenador(images_train, images_test, labels_test, labels_train);
        entrena.crea_red();
        entrena.entrena();
        System.out.println("-----------------Fin de entrenamiento, comienza el test-----------------");
        entrena.test();
        

    }
}
