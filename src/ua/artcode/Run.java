package ua.artcode;

import ua.artcode.controller.AdminContollerFactory;
import ua.artcode.controller.AdminController;
import ua.artcode.view.LoginFrame;

/**
 * Created by serhii on 20.12.15.
 */
public class Run {

    public static void main(String[] args) {
        new LoginFrame(AdminContollerFactory.getAdminController());
    }
}
