package ua.artcode.controller;


import ua.artcode.model.IPerson;
import ua.artcode.utils.serialization.TaxiAppLoader;

public class ControllerFactory {

    private static AppDataContainer appDataContainer = TaxiAppLoader.load("file");

    public static ITaxiController login(String login, String pass) {

        IPerson person = searchIPerson(login, pass);
        if(person == null){
            // ask one more time.. or maybe he isn't in database
            return null;
        }

        return person.whoAmI().equals("admin") ? getAdminController() : null;
    }

    public static AdminController getAdminController(){
        return AdminController.getAdminController(appDataContainer);
    }

    private static IPerson searchIPerson(String login, String pass){

        for(IPerson tmp : appDataContainer.getListAdmins()){
            if(login.equals(tmp.getLogin()) && pass.equals(tmp.getPass())){
                return tmp;
            }
        }

        return null;
    }

}
