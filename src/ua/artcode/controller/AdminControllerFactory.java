package ua.artcode.controller;

import ua.artcode.model.Admin;
import ua.artcode.model.ID;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminControllerFactory {

    public Admin login(String login, String pass) {
        return new Admin(login, pass, ID.genId());
    }

    public static AdminController getAdminController(){
        return AdminController.getAdminController();
    }

}
