package ua.artcode.utils.serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONValue;
import ua.artcode.controller.AppDataContainer;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class TaxiAppSave {

    @Deprecated
    public static<T> void save(String nameFile, List<T> list){


        Type listOfTestObject = new TypeToken<List<T>>(){}.getType();
        Gson gson = new Gson();
        String jsonString = gson.toJson(list, listOfTestObject);

        try(Writer out = new FileWriter("./resources/db/" + nameFile);) {

            out.write(jsonString);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void save(AppDataContainer container){


        Gson gson = new Gson();
        String jsonString = gson.toJson(container);

        try(Writer out = new FileWriter("./resources/db/db.json")) {

            out.write(jsonString);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
