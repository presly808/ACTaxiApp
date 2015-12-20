package ua.artcode.taxiAppLoader;

import ua.artcode.controller.AppDataContainer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppLoader {

    public static AppDataContainer load(String nameFile){

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




