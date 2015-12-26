package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AdminController;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.ClientController;
import ua.artcode.controller.DriverController;
import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by dexter on 26.12.15.
 */
public class TaxiAppLoaderTest {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    private AppDataContainer appDataContainer;

    private Admin admin = new Admin("superadmin", "superpass", ID.genId());

    private Client client1 = new Client("Max", 111, "New York", "111", ID.genId());
    private Client client2 = new Client("John", 222, "New Jersey", "222", ID.genId());

    private Ticket ticket = new Ticket(0, client1.getId(), "Central Park", "Times square", 17.50, "NEW",
            new Date(), new Date(), new Date(), ID.genId());


    private Driver driver1 = new Driver("Ashton", new Car("BMW", 1234, "pink"), ID.genId(), true, ticket.getiDTicket(), "1234");
    private Driver driver2 = new Driver("Reese", new Car("Lexus", 2345, "green"), ID.genId(), false);

    private DriverController driverController;
    private AdminController adminController;
    private ClientController clientController;

    @Before
    public void setUp() throws Exception {

        admins.add(admin);

        clients.add(client1);
        clients.add(client2);

        drivers.add(driver1);
        drivers.add(driver2);

        tickets.add(ticket);

        appDataContainer = new AppDataContainer(tickets, admins, clients, drivers);

        driverController = new DriverController(driver1, new AppDataContainer(tickets, admins, clients, drivers));
        adminController = AdminController.getAdminController(appDataContainer);
        clientController = new ClientController(client1, appDataContainer);

    }

    @Test
    public void saveTest(){

        TaxiAppSave.save("testTickets.json", appDataContainer.getListTickets());
        TaxiAppSave.save("testAdmins.json", appDataContainer.getListAdmins());
        TaxiAppSave.save("testClients.json", appDataContainer.getListClients());
        TaxiAppSave.save("testDrivers.json", appDataContainer.getListDrivers());

        File ticketsFile = new File("./resources/db/testTickets.json");
        assertTrue(ticketsFile.exists());

        File adminsFile = new File("./resources/db/testAdmins.json");
        assertTrue(adminsFile.exists());

        File clientsFile = new File("./resources/db/testClients.json");
        assertTrue(clientsFile.exists());

        File driversFile = new File("./resources/db/testDrivers.json");
        assertTrue(driversFile.exists());

    }

    @Test
    public void loadListTest(){

        List<Ticket> actualTickets = TaxiAppLoader.<Ticket>loadList("testTickets.json");
        List<Admin> actualAdmins = TaxiAppLoader.<Ticket>loadList("testAdmins.json");
        List<Client> actualClients = TaxiAppLoader.<Ticket>loadList("testClients.json");
        List<Driver> actualDrivers = TaxiAppLoader.<Ticket>loadList("testDrivers.json");

        assertFalse(actualTickets.isEmpty());
        assertTrue(actualTickets.contains(ticket));

        assertFalse(actualAdmins.isEmpty());
        assertTrue(actualAdmins.contains(admin));

        assertFalse(actualClients.isEmpty());
        assertTrue(actualClients.contains(client1));

        assertFalse(actualDrivers.isEmpty());
        assertTrue(actualDrivers.contains(driver1));

    }

}
