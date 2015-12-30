package ua.artcode.controller;

import ua.artcode.exception.BusyDriverExeption;
import ua.artcode.exception.NotFindInDataBaseException;
import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminController implements IAdminController {

    private AppDataContainer appDataContainer;
    //default mean -1. When admin choose ticket this field will init.. and then this field will become -1;
    private long ticketId = -1;

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
    public Driver addDriver(String name, Car car, String pass){
        return Registration.addDriverO(name, car, appDataContainer, pass);
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
    public List<Driver> getFreeDrivers() throws NotFindInDataBaseException {

        List<Driver> freeDrivers = new ArrayList<>();

        for(Driver tmp : appDataContainer.getListDrivers()){

            if(tmp.getStatus()){
                freeDrivers.add(tmp);
            }

        }
        if(freeDrivers.isEmpty()){
            throw  new NotFindInDataBaseException("didn't find free Drivers");
        }
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
    public void setDriverToTicket(long ticketId, long driverId) throws NotFindInDataBaseException, BusyDriverExeption {

        Ticket ticket = getTicketById(ticketId);
        Driver driver = getDriverById(driverId);

        if(driver.takeTicket(ticket.getiDTicket())){
            throw new BusyDriverExeption("Driver has already taken a ticket");
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

    public void deleteDriver(long idDriver) throws NotFindInDataBaseException {

        Driver driver = getDriverById(idDriver);
        appDataContainer.getListDrivers().remove(driver);
        TaxiAppSave.save(appDataContainer);

    }

    public void setTicketId(long ticketId){
        this.ticketId = ticketId;
    }

    public void dropIdTicket(){
        ticketId = -1;
    }
}
