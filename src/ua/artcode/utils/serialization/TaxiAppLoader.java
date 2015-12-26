package ua.artcode.utils.serialization;

import com.google.gson.Gson;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.model.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppLoader {

    @Deprecated
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

    public static<T> List loadList(String nameFile){

        String objects = "";

        try (BufferedReader bf = new BufferedReader(new FileReader("./resources/db/" + nameFile))) {
            String line = "";
            while((line = bf.readLine()) != null){
                objects += line + "\n";
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<T>();
        }

        Gson gson = new Gson();
        List<T> list = gson.<ArrayList<T>>fromJson(objects, ArrayList.class);

        return list;
    }
}




