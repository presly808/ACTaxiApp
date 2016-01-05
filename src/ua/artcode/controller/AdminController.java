package ua.artcode.controller;

import ua.artcode.exception.BusyDriverException;
import ua.artcode.exception.LoginHasAlreadyUsed;
import ua.artcode.exception.NotFindInDataBaseException;
import ua.artcode.model.*;
import ua.artcode.utils.geolocation.Location;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.List;
import java.util.Vector;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminController implements IAdminController {

    private static final AdminController controller = new AdminController();
    private AppDataContainer appDataContainer;
    //default mean -1. When admin choose ticket this field will init.. and then this field will become -1;
    private long ticketId = -1;

    private AdminController(){
    }

    public static AdminController getAdminController(AppDataContainer appDataContainer) {
        controller.appDataContainer = appDataContainer;
        return controller;
    }

    @Override
    public Client addClient(String name, int phone, String location, String pass ) throws LoginHasAlreadyUsed {
        return Registration.addClientO(name, phone, location, pass, appDataContainer);
    }

    @Override
    public Driver addDriver(String name, Car car, String pass){
        return Registration.addDriverO(name, car, appDataContainer, pass);
    }

    @Override
    public Admin addAdmin(String name, String pass) throws LoginHasAlreadyUsed {
        return Registration.addAdmin(name, pass, appDataContainer);
    }

    @Override
    public Driver removeDriver(long id) throws NotFindInDataBaseException {

        for(Driver tmp : appDataContainer.getListDrivers()){
            if(tmp.getId() == id){
                if(appDataContainer.getListDrivers().remove(tmp)){
                    return tmp;
                }
            }
        }

        throw  new NotFindInDataBaseException("didn't find Driver");
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
    public Client getClientById(long id) throws NotFindInDataBaseException {

        for(Client tmp : appDataContainer.getListClients()){
            if(tmp.getId() == id){
                return tmp;
            }
        }

        throw  new NotFindInDataBaseException("didn't find Client");
    }

    @Override
    public Driver getDriverById(long id) throws NotFindInDataBaseException {

        for(Driver tmp : appDataContainer.getListDrivers()){

            if(id == tmp.getId()){
                return tmp;
            }

        }

        throw  new NotFindInDataBaseException("didn't find Driver");
    }

    @Override
    public Vector<Driver> getFreeDrivers() throws NotFindInDataBaseException {

        Ticket ticket = getTicketById(ticketId);

        Vector<Driver> freeDrivers = new Vector<>();

        for(Driver tmp : appDataContainer.getListDrivers()){

            if(tmp.getStatus()){

                tmp.setDistanceToClient(Location.getDistance(tmp.getCurrentLocation().getFormattedAddress(),
                        ticket.getFromLocation()));
                freeDrivers.add(tmp);
            }

        }

        freeDrivers.sort((Driver a1, Driver a2) -> a1.getDistanceToClient() < a2.getDistanceToClient() ? -1
                : a1.getDistanceToClient() > a2.getDistanceToClient() ? -1 : 0);

        dropIdTicket();

        return freeDrivers;
    }

    @Override
    public Ticket getTicketById(long id) throws NotFindInDataBaseException {

        for(Ticket tmp : appDataContainer.getListTickets()){

            if(id == tmp.getiDTicket()){
                return tmp;
            }

        }

        throw  new NotFindInDataBaseException("didn't find Ticket");
    }

    @Override
    public void setDriverToTicket(long ticketId, long driverId) throws NotFindInDataBaseException, BusyDriverException {

        Ticket ticket = getTicketById(ticketId);
        Driver driver = getDriverById(driverId);

        if(!driver.takeTicket(ticket.getiDTicket())){
            throw new BusyDriverException("Driver has already taken a ticket");
        }
        ticket.setIdDriver(driverId);
        ticket.setStatus(TicketStatus.PROCESSED);
        TaxiAppSave.save(appDataContainer);

    }

    @Override
    public Ticket findTicketByClientId(long id) throws NotFindInDataBaseException {

        for(Ticket tmp : appDataContainer.getListTickets()){

            if(id == tmp.getIdClient()){
                return tmp;
            }

        }

        throw  new NotFindInDataBaseException("didn't find Ticket");
    }

    public void deleteDriver(long idDriver) throws NotFindInDataBaseException, BusyDriverException {

        Driver driver = getDriverById(idDriver);
        if(driver.getIdCurrentTicket() != 0){
            throw new BusyDriverException("The drive is performing his job");
        }
        appDataContainer.getListDrivers().remove(driver);
        TaxiAppSave.save(appDataContainer);

    }

    public void setTicketId(long ticketId){
        this.ticketId = ticketId;
    }

    private void dropIdTicket(){
        ticketId = -1;
    }

    public long getTicketId(){
        return ticketId;
    }
}
