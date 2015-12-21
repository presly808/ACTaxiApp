package ua.artcode.utils.serialization;

import ua.artcode.controller.AppDataContainer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppSave {

    public static void save(String nameFile, AppDataContainer appDataContainer){

        try(FileOutputStream out = new FileOutputStream("./resources/db/" + nameFile);
            ObjectOutputStream oout = new ObjectOutputStream(out)) {

            oout.writeObject(appDataContainer);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
