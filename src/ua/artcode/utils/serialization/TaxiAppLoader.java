package ua.artcode.utils.serialization;

import ua.artcode.controller.AppDataContainer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppLoader {

    public static AppDataContainer load(String nameFile){

        //TODO invalid closing stream (finally or try with resources)
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile));

            AppDataContainer temp = new AppDataContainer((AppDataContainer)ois.readObject());


            ois.close();

            return temp;

        } catch (Exception ex) {

            ex.printStackTrace();

        }


        return new AppDataContainer();
    }
}




