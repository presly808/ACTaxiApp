package ua.artcode.model;

import java.io.Serializable;
import java.util.Observer;

public class Admin extends Person implements Serializable {


    public Admin(String login, String pass, long iD) {

        this.login = login;
        this.pass = pass;
        this.iD = Math.abs(iD);

    }

    @Override
    public String toString() {
        return login +
                ";" + pass +
                ";" + iD;
    }

    @Override
    public boolean equals(Object admin){

        if(admin == null){
            return false;
        }
        if(this == admin){
            return true;
        }
        if(admin.getClass() != Admin.class){
            return false;
        }
        Admin tmp = (Admin) admin;

        return login.equals(tmp.login) && (pass.equals(tmp.pass) && iD == tmp.iD);
    }
}
