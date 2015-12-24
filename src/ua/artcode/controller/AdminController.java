package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminController implements IAdminController {

    private AppDataContainer appDataContainer;
    private String me = "admin";

    private AdminController(AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
    }

    public static AdminController getAdminController(AppDataContainer appDataContainer) {
        return new AdminController(appDataContainer);
    }

    // return null if db have same login
    @Override
    public Client addClient(String name, int phone, String location, String pass ){
        return Registration.addClientO(name, phone, location, pass, appDataContainer);
    }

    @Override
    public Driver addDriver(String name, Car car){
        return Registration.addDriverO(name, car, appDataContainer);
    }

    @Override
    public List<Client> getAllClients() {
        return appDataContainer.getListClients();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return appDataContainer.getListDrivers();
    }

    @Override
    public List<Ticket> getTickets() {
        return appDataContainer.getListTickets();
    }

    @Override
    // return null if didn't find client
    public Client getClientById(long id) {

        for(Client tmp : appDataContainer.getListClients()){

            if(id == tmp.getId()){
                return tmp;
            }

        }

        return null;
    }

    @Override
    public Driver getDriverById(long id) {

        for(Driver tmp : appDataContainer.getListDrivers()){

            if(id == tmp.getId()){
                return tmp;
            }

        }

        return null;
    }

    @Override
    public Driver getFreeDriver(){

        for(Driver tmp : appDataContainer.getListDrivers()){

            if(tmp.getStatus()){
                return tmp;
            }

        }
        return null;
    }

    @Override
    public Ticket getTicketById(long id) {

        for(Ticket tmp : appDataContainer.getListTickets()){

            if(id == tmp.getiDTicket()){
                return tmp;
            }

        }

        return null;
    }

    @Override
    // I think maybe this method have to return Ticket... not boolean? What do you think?
    public boolean setDriverToTicket(long clientId, long driverId) {

        for(Ticket tmp : appDataContainer.getListTickets()){
            if(tmp.getStatus().equals(TicketStatus.NEW)){

                Driver driver = getFreeDriver();

                tmp.setIdDriver(driver.getId());
                driver.takeTicket(tmp.getiDTicket());

                TaxiAppSave.save("tickets.json", appDataContainer.getListTickets());

                return true;
            }
        }

        return false;
    }

    @Override
    public Ticket findTicketByClientId(long id) {

        for(Ticket tmp : appDataContainer.getListTickets()){

            if(id == tmp.getIdClient()){
                return tmp;
            }

        }

        return null;
    }

    @Override
    public String whoAmI(){
        return me;
    }

}
