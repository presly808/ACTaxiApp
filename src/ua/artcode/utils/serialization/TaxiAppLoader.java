package ua.artcode.utils.serialization;

import ua.artcode.controller.AppDataContainer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppLoader {

    public static AppDataContainer load(String nameFile) {
        AppDataContainer temp = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./resources/db/" + nameFile))) {

            temp = new AppDataContainer((AppDataContainer) ois.readObject());


        } catch (Exception ex) {
            ex.printStackTrace();
            return new AppDataContainer();
        }

        return temp;
    }
}




