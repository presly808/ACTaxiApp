package ua.artcode.controller;


import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppLoader;

public class Login {

    private AppDataContainer appDataContainer;

    public Login(){

        appDataContainer = new AppDataContainer(TaxiAppLoader.loadContainer("db.json"));

    }

    public ITaxiController login(String login, String pass) {

        // we need this for default enter to adminController or DriverController
        //appDataContainer.addAdminToData(new Admin("admin", "admin", 1));
        //appDataContainer.addDriverToData(new Driver("driver", new Car("car", 123, "blue"), 0, false));

        Person person = appDataContainer.searchIPerson(login, pass);
        if(person == null){
            // ask one more time.. or maybe he isn't in database
            return null;
        }

        return person.getClass() == Admin.class ?
                ControllerFactory.getAdminController(appDataContainer) :
                person.getClass() == Client.class ?
                ControllerFactory.getClientController(person, appDataContainer):
                ControllerFactory.getDriverController(person, appDataContainer);
    }

    //if login have already used, method return false
    public boolean addClient(String name, int phone, String location, String pass ){
        return Registration.addClient(name, phone, location, pass, appDataContainer);
    }
}
