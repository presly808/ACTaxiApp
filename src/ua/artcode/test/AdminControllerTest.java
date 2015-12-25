package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AdminController;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhabenya on 23.12.15.
 */
public class AdminControllerTest {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    private Admin admin = new Admin("superadmin", "superpass", ID.genId());

    private Client client1 = new Client("Max", 111, "New York", "111", ID.genId());
    private Client client2 = new Client("John", 222, "New Jersey", "222", ID.genId());
    private Client client3 = new Client("Allie", 333, "New Jersey", "333", ID.genId());
    private Client client4 = new Client("Ben", 444, "Brooklyn", "444", ID.genId());

    private Driver driver1 = new Driver("Ashton", new Car("BMW", 1234, "pink"), ID.genId(), true);
    private Driver driver2 = new Driver("Reese", new Car("Lexus", 2345, "green"), ID.genId(), true);
    private Driver driver3 = new Driver("Janet", new Car("Golf", 9876, "blue"), ID.genId(), true);

    private Ticket ticket = new Ticket(driver1.getId(), client1.getId(), "Central Park", "Times square", 17.50, "NEW",
                new Date(), new Date(), new Date(), ID.genId());

    private AdminController controller;

    @Before
    public void setUp() throws Exception {
        admins.add(admin);

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);

        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);

        tickets.add(ticket);

        controller = AdminController.getAdminController(new AppDataContainer(tickets, admins, clients, drivers));
    }

    @Test
    public void testGetAllClients() throws Exception {
        List<Client> actual = controller.getAllClients();
        assertTrue(actual.size() == clients.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(clients.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetAllDrivers() throws Exception {
        List<Driver> actual = controller.getAllDrivers();
        assertTrue(actual.size() == drivers.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(drivers.get(i), actual.get(i));
        }
    }

    @Test
    public void testAddClient() throws Exception {
        Client actual = controller.addClient("i", 111, "1", "11");
        assertNotNull(actual);
        assertEquals("i", actual.getLogin());
    }

    @Test
    public void testAddDriver() throws Exception {
        Driver actual = controller.addDriver("driver", new Car("Jeep", 1111, "black"),"driverPass");
        assertNotNull(actual);
        assertEquals("driver", actual.getLogin());
        assertFalse(actual.isFree());
    }

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> actual = controller.getTickets();
        assertEquals(tickets.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(tickets.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetClientById() throws Exception {
        Client actual = controller.getClientById(client1.getId());
        assertEquals(client1, actual);

        Client actual2 = controller.getClientById(client1.getId() + 1000);
        assertNull(actual2);
    }

    @Test
    public void testGetDriverById() throws Exception {
        Driver actual = controller.getDriverById(driver1.getId());
        assertEquals(driver1, actual);

        Driver actual2 = controller.getDriverById(driver1.getId() + 1000);
        assertNull(actual2);
    }

    @Test
    public void testGetTicketById() throws Exception {
        Ticket actual = controller.getTicketById(ticket.getiDTicket());
        assertEquals(ticket, actual);
    }

    @Test
    public void testSetDriverToTicket() throws Exception {
        assertFalse(controller.setDriverToTicket(client1.getId(), driver1.getId()));

        assertTrue(controller.setDriverToTicket(client2.getId(), driver2.getId()));
    }

    @Test
    public void testFindTicketByClientId() throws Exception {
        assertEquals(ticket, controller.findTicketByClientId(client1.getId()));
    }
}