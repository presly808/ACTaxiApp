package ua.artcode.controller;

/**
 * Created by dexter on 20.12.15.
 */
public class ControllerFactory {

    public static AdminController getAdminController(AppDataContainer appDataContainer){
        return AdminController.getAdminController(appDataContainer);
    }


}
