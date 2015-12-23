package ua.artcode.controller;

import ua.artcode.model.Client;
import ua.artcode.model.IPerson;

/**
 * Created by dexter on 20.12.15.
 */
public class ControllerFactory {

    public static AdminController getAdminController(AppDataContainer appDataContainer){
        return AdminController.getAdminController(appDataContainer);
    }

    public static ClientController getClientController(IPerson client, AppDataContainer appDataContainer){
        return new ClientController((Client)client, appDataContainer);
    }

}
