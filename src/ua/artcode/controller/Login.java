package ua.artcode.controller;


import ua.artcode.model.Admin;
import ua.artcode.model.Client;
import ua.artcode.model.ID;
import ua.artcode.model.IPerson;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

public class Login {

    private AppDataContainer appDataContainer = TaxiAppLoader.load("file");

    public ITaxiController login(String login, String pass) {

        //create default admin... he wont be save in "file"..
        // we need this for default enter to adminController
        appDataContainer.addAdminToData(new Admin("admin", "admin", 1));

        IPerson person = searchIPerson(login, pass);
        if(person == null){
            // ask one more time.. or maybe he isn't in database
            return null;
        }

        return person.whoAmI().equals("admin") ?
                ControllerFactory.getAdminController(appDataContainer) :
                ControllerFactory.getClientController();
    }

    private IPerson searchIPerson(String login, String pass){

        for(IPerson tmp : appDataContainer.getListAdmins()){
            if(login.equals(tmp.getLogin()) && pass.equals(tmp.getPass())){
                return tmp;
            }
        }
        for(IPerson tmp : appDataContainer.getListClients()){
            if(login.equals(tmp.getLogin()) && pass.equals(tmp.getPass())){
                return tmp;
            }
        }

        return null;
    }

    public Client addClient(String name, int phone, String location, String pass ){

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("file", appDataContainer);

        return client;
    }
}
