package ua.artcode.utils.serialization;

import com.google.gson.Gson;
import org.json.simple.JSONValue;
import ua.artcode.controller.AppDataContainer;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppSave {

    public static void save(String nameFile, List list){

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        try(Writer out = new FileWriter("./resources/db/" + nameFile);) {

            out.write(jsonString);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
