package ua.artcode.controller;


import ua.artcode.model.Admin;
import ua.artcode.model.Person;

public class Login {

    private AppDataContainer appDataContainer;

    public Login(){

        appDataContainer = new AppDataContainer();

    }

    public ITaxiController login(String login, String pass) {

        //create default admin... he won't be save in "file"..
        // we need this for default enter to adminController
        appDataContainer.addAdminToData(new Admin("admin", "admin", 1));

        Person person = appDataContainer.searchIPerson(login, pass);
        if(person == null){
            // ask one more time.. or maybe he isn't in database
            return null;
        }

        return person.getClass() == Admin.class ?
                ControllerFactory.getAdminController(appDataContainer) :
                ControllerFactory.getClientController(person, appDataContainer);
    }

    //if login have already used, method return false
    public boolean addClient(String name, int phone, String location, String pass ){
        return Registration.addClient(name, phone, location, pass, appDataContainer);
    }
}
