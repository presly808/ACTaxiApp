package ua.artcode.controller;

import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Person;

/**
 * Created by dexter on 20.12.15.
 */
public class ControllerFactory {

    public static AdminController getAdminController(AppDataContainer appDataContainer){
        return AdminController.getAdminController(appDataContainer);
    }

    public static ClientController getClientController(Person client, AppDataContainer appDataContainer){
        return new ClientController((Client)client, appDataContainer);
    }

    public static ITaxiController getDriverController(Person person, AppDataContainer appDataContainer) {
        return new DriverController((Driver)person, appDataContainer);
    }
}
