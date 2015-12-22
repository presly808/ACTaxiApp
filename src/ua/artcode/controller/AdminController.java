package ua.artcode.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
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

    @Override
    public Client addClient(String name, int phone, String location, String pass ){

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return client;
    }

    @Override
    public Driver addDriver(String name, Car car){

        Driver driver = new Driver(name, car, ID.genId(), new Boolean("false"));
        appDataContainer.addDriverToData(driver);
        TaxiAppSave.save("drivers.json", appDataContainer.getListDrivers());

        return driver;
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

            if(id == tmp.getiD()){
                return tmp;
            }

        }

        return null;
    }

    @Override
    public Driver getDriverById(long id) {

        for(Driver tmp : appDataContainer.getListDrivers()){

            if(id == tmp.getiDDriver()){
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

        Ticket ticket = new Ticket(driverId, clientId, "Simirenko 7a", "Lomonosova 33/43",
                100, null, null, null, null, ID.genId());
        appDataContainer.addTicketToData(ticket);

        return true;
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
