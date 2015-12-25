package ua.artcode.utils.serialization;

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

    public static List loadList(String nameFile, LoadMode mode){

        String objects = "";

        try (BufferedReader bf = new BufferedReader(new FileReader("./resources/db/" + nameFile))) {
            String line = "";
            while((line = bf.readLine()) != null){
                objects += line + "\n";
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return mode == LoadMode.ADMINS ? parseAdminsString(objects) :
                mode == LoadMode.CLIENTS ? parseClientsString(objects) :
                mode == LoadMode.DRIVERS ? parseDriversString(objects) :
                parseTicketsString(objects);
    }

    private static List<Admin> parseAdminsString(String adminsStr){

        ArrayList<Admin> adminsList = new ArrayList<>();
        adminsStr = fixTicketsString(adminsStr);


        String[] admins = adminsStr.split(",");
        if(admins.length < 1){
            return adminsList;
        }

        for(String tmp : admins){

            String[] parameters = tmp.split(";");
            adminsList.add(new Admin(parameters[0], parameters[1], new Long(parameters[2])));

        }

        return adminsList;
    }

    private static List<Client> parseClientsString(String clientsStr) {

        ArrayList<Client> adminsList = new ArrayList<>();
        clientsStr = fixTicketsString(clientsStr);

        String[] clients = clientsStr.split(",");
        if(clients.length < 1){
            return adminsList;
        }

        for(String tmp : clients){

            String[] parameters = tmp.split(";");

            adminsList.add(new Client(parameters[0], new Integer(parameters[1]),
                    parameters[2], parameters[3], new Long(parameters[4])));

        }

        return adminsList;
    }

    private static List<Driver> parseDriversString(String driversStr) {

        ArrayList<Driver> driversList = new ArrayList<>();
        driversStr = fixTicketsString(driversStr);

        String[] drivers = driversStr.split(",");
        if(drivers.length < 1){
            return driversList;
        }

        for(String tmp : drivers){

            String[] parameters = tmp.split(";");

            driversList.add(new Driver(parameters[0], new Car(parameters[1]),
                    new Long(parameters[2]), new Boolean(parameters[3]), new Long(parameters[4]), parameters[5]));

        }

        return driversList;

    }

    private static List<Ticket> parseTicketsString(String ticketsStr) {

        ArrayList<Ticket> ticketsList = new ArrayList<>();
        ticketsStr = fixTicketsString(ticketsStr);

        String[] tickets = ticketsStr.split(",");
        if(tickets.length < 1){
            return ticketsList;
        }

        for(String tmp : tickets){

            String[] parameters = tmp.split(";");

            ticketsList.add(new Ticket(new Long(parameters[0]), new Long(parameters[1]), parameters[2],
                    parameters[3], new Double(parameters[4]), parameters[5], new Date(new Long(parameters[6])),
                    new Date(new Long(parameters[7])), new Date(new Long(parameters[8])),
                    new Long(parameters[9])));

        }

        return ticketsList;

    }

    private static String fixTicketsString(String ticketsStr) {

        if(ticketsStr.endsWith("\n")){
            ticketsStr = ticketsStr.substring(0, ticketsStr.length() - 1);
        }

        while(true) {
            if(ticketsStr.startsWith("[") || ticketsStr.endsWith("]")) {
                if (ticketsStr.startsWith("[")) {
                    ticketsStr = ticketsStr.substring(1, ticketsStr.length());
                }
                if (ticketsStr.endsWith("]")) {
                    ticketsStr = ticketsStr.substring(0, ticketsStr.length() - 1);
                }
            } else return ticketsStr;
        }
    }

}




