package ua.artcode.controller;

import ua.artcode.model.Admin;
import ua.artcode.model.ID;
import ua.artcode.utils.serialization.TaxiAppLoader;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminControllerFactory {

    public static ITaxiController login(String login, String pass) {


        TaxiAppLoader.load("file");
        return null;
    }

    public static AdminController getAdminController(){
        return null;//AdminController.getAdminController();
    }

}
