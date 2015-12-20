package ua.artcode.controller;


import ua.artcode.model.Client;
import ua.artcode.model.ID;
import ua.artcode.model.IPerson;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

public class Login {

    private static AppDataContainer appDataContainer = TaxiAppLoader.load("file");

    public static ITaxiController login(String login, String pass) {

        IPerson person = searchIPerson(login, pass);
        if(person == null){
            // ask one more time.. or maybe he isn't in database
            return null;
        }

        return person.whoAmI().equals("admin") ?
                ControllerFactory.getAdminController(appDataContainer) : null;
    }

    private static IPerson searchIPerson(String login, String pass){

        for(IPerson tmp : appDataContainer.getListAdmins()){
            if(login.equals(tmp.getLogin()) && pass.equals(tmp.getPass())){
                return tmp;
            }
        }

        return null;
    }

    public static Client addClient(String name, int phone, String location, String pass, long cash){

        Client client = new Client(name, phone, location, cash, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("file", appDataContainer);

        return client;
    }


}
