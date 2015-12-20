package ua.artcode.controller;


import ua.artcode.utils.serialization.TaxiAppLoader;

public class AdminControllerFactory {

    public static ITaxiController login(String login, String pass) {


        return null;
    }

    public static AdminController getAdminController(){
        return AdminController.getAdminController(TaxiAppLoader.load("file"));
    }

}
