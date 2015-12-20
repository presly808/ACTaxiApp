package ua.artcode.controller;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminControllerFactory {

    public static ITaxiController login(String login, String pass) {
        return null;
        //return new Admin(login, pass, ID.genId());
    }

    public static AdminController getAdminController(){
        return AdminController.getAdminController();
    }

}
