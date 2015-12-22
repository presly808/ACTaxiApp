package ua.artcode.controller;


import ua.artcode.model.Admin;
import ua.artcode.model.Client;
import ua.artcode.model.ID;
import ua.artcode.model.IPerson;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

public class Login {

    private AppDataContainer appDataContainer;

    public Login(){

        appDataContainer = new AppDataContainer();

    }

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

        IPerson tmp = getAdmin(login);
        if (tmp != null){

            if(pass.equals(tmp.getPass())){
                return tmp;
            } //else message "unknown pass"
        }
        tmp = getClient(login);
        if (tmp != null){

            if(pass.equals(tmp.getPass())){
                return tmp;
            } //else message "unknown pass"
        }

        // message "unknown login"
        return null;
    }

    private IPerson getClient(String login) {
        for(IPerson tmp : appDataContainer.getListClients()){
            if(login.equals(tmp.getLogin())){
                return tmp;
            }
        }
        return null;
    }

    private IPerson getAdmin(String login) {
        for(IPerson tmp : appDataContainer.getListAdmins()){
            if(login.equals(tmp.getLogin())){
                return tmp;
            }
        }
        return null;
    }

    //if login have already used, method return null
    public boolean addClient(String name, int phone, String location, String pass ){

        IPerson person = getClient(name);
        if(person != null){
            return false;
        }

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return true;
    }
}
