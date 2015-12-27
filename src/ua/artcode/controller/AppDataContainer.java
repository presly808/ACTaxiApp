package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.database_gen.DataBaseListsGen;
import ua.artcode.utils.serialization.TaxiAppLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AppDataContainer implements Serializable, IAppDataContainer {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> driver = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    public AppDataContainer(){
    }

    public AppDataContainer(AppDataContainer appDataContainer){

        this.tickets = appDataContainer.tickets;
        this.driver = appDataContainer.driver;
        this.clients = appDataContainer.clients;
        this.admins = appDataContainer.admins;

    }

    // Constructor for tests
    public AppDataContainer(List<Ticket> ticketsList, List<Admin> adminsList,
                            List<Client> clientsList, List<Driver> driversList){

        clients = clientsList;
        admins = adminsList;
        tickets = ticketsList;
        driver = driversList;

    }

    @Override
    public void addClientToData(Client client){

        this.clients.add(client);

    }

    @Override
    public void addDriverToData(Driver driver){

        this.driver.add(driver);

    }

    @Override
    public void addTicketToData(Ticket ticket){

        this.tickets.add(ticket);

    }

    @Override
    public void addAdminToData(Admin admin){

        this.admins.add(admin);

    }

    @Override
    public List<Client> getListClients(){
        return clients;
    }

    @Override
    public List<Driver> getListDrivers() {
        return driver;
    }

    @Override
    public List<Admin> getListAdmins() {
        return admins;
    }

    @Override
    public List<Ticket> getListTickets(){
        return tickets;
    }

    @Override
    public Person searchIPerson(String login, String pass){

        Person tmp = getAdmin(login);
        if (tmp != null){

            if(pass.equals(tmp.getPass())){
                return tmp;
            } //else message "unknown pass"
        }
        tmp = getClient(login);
        if (tmp != null){

            if(pass.equals(tmp.getPass())){
                return tmp;
            } //else message "unknown pass"
        }

        // message "unknown login"
        return null;
    }

    @Override
    public Person getClient(String login) {
        for(Person tmp : clients){
            if(login.equals(tmp.getLogin())){
                return tmp;
            }
        }
        return null;
    }

    @Override
    public Person getAdmin(String login) {
        for(Person tmp : admins){
            if(login.equals(tmp.getLogin())){
                return tmp;
            }
        }
        return null;
    }

}
