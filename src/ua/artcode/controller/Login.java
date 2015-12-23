package ua.artcode.controller;


import ua.artcode.model.Admin;
import ua.artcode.model.Client;
import ua.artcode.model.ID;
import ua.artcode.model.IPerson;
import ua.artcode.utils.serialization.TaxiAppSave;

public class Login {

    private AppDataContainer appDataContainer;

    public Login(){

        appDataContainer = new AppDataContainer();

    }

    public ITaxiController login(String login, String pass) {

        //create default admin... he won't be save in "file"..
        // we need this for default enter to adminController
        appDataContainer.addAdminToData(new Admin("admin", "admin", 1));

        IPerson person = appDataContainer.searchIPerson(login, pass);
        if(person == null){
            // ask one more time.. or maybe he isn't in database
            return null;
        }

        return person.whoAmI().equals("admin") ?
                ControllerFactory.getAdminController(appDataContainer) :
                ControllerFactory.getClientController();
    }

    //if login have already used, method return false
    public boolean addClient(String name, int phone, String location, String pass ){

        IPerson person = appDataContainer.getClient(name);
        if(person != null){
            return false;
        }

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return true;
    }
}
