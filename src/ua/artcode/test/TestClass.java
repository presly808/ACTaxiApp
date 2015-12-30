package ua.artcode.test;

import ua.artcode.controller.AdminController;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.ClientController;
import ua.artcode.controller.DriverController;
import ua.artcode.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dexter on 28.12.15.
 */
public class TestClass {

    protected List<Ticket> ticketsDone = new ArrayList<>();
    protected List<Ticket> ticketsNew = new ArrayList<>();
    protected List<Driver> drivers = new ArrayList<>();
    protected List<Driver> busyDrivers = new ArrayList<>();
    protected List<Client> clients = new ArrayList<>();
    protected List<Admin> admins = new ArrayList<>();

    protected Admin admin = new Admin("superadmin", "superpass", ID.genId("superadmin".hashCode()));

    protected Client client1 = new Client("Max", 111, "New York", "111", ID.genId("Max".hashCode()));
    protected Client client2 = new Client("John", 222, "New Jersey", "222", ID.genId("John".hashCode()));
    protected Client client3 = new Client("Allie", 333, "New Jersey", "333", ID.genId("Alice".hashCode()));
    protected Client client4 = new Client("Ben", 444, "Brooklyn", "444", ID.genId("Ben".hashCode()));

    protected Driver driver1 = new Driver("Ashton", new Car("BMW", 1234, "pink"), ID.genId("Ashton".hashCode()), true);
    protected Driver driver2 = new Driver("Reese", new Car("Lexus", 2345, "green"), ID.genId("Reese".hashCode()), true);
    protected Driver driver3 = new Driver("Janet", new Car("Golf", 9876, "blue"), ID.genId("Janet".hashCode()), true);
    protected Driver driver4 = new Driver("Ashton", new Car("BMW", 1234, "pink"), ID.genId("BMW".hashCode()), false);
    protected Driver driver5 = new Driver("Reese", new Car("Lexus", 2345, "green"), ID.genId("Lexus".hashCode()), false);
    protected Driver driver6 = new Driver("Janet", new Car("Golf", 9876, "blue"), ID.genId("Golf".hashCode()), false);

    protected Ticket ticketDone = new Ticket(driver1.getId(), client1.getId(), "Central Park", "Times square", 17.50, TicketStatus.DONE,
                new Date(), new Date(), new Date(), ID.genId("Central Park".hashCode()));

    protected Ticket ticketNew = new Ticket(driver1.getId(), client1.getId(), "Central Park", "Times square", 17.50, TicketStatus.NEW,
            new Date(), new Date(), new Date(), ID.genId("Central Park".hashCode()));

    protected AdminController adminController;
    protected AdminController overLoadController;
    protected ClientController clientController;
    protected ClientController clientController2;
    protected DriverController driverController1;
    protected DriverController driverController2;

    protected AppDataContainer appDataContainer;

}


















































