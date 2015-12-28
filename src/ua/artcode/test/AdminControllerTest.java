package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AdminController;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.model.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhabenya on 23.12.15.
 */
public class AdminControllerTest extends TestClass{

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

        busyDrivers.add(driver4);
        busyDrivers.add(driver5);
        busyDrivers.add(driver6);

        tickets.add(ticket);

        adminController = AdminController.getAdminController(new AppDataContainer(tickets, admins, clients, drivers));
        overLoadController = AdminController.getAdminController(new AppDataContainer(tickets, admins, clients, busyDrivers));
    }

    @Test
    public void testGetAllClients() throws Exception {
        List<Client> actual = adminController.getAllClients();
        assertTrue(actual.size() == clients.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(clients.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetAllDrivers() throws Exception {
        List<Driver> actual = adminController.getAllDrivers();
        assertTrue(actual.size() == drivers.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(drivers.get(i), actual.get(i));
        }
    }

    @Test
    public void testAddClient() throws Exception {
        Client actual = adminController.addClient("i", 111, "1", "11");
        assertNotNull(actual);
        assertEquals("i", actual.getLogin());
    }

    @Test
    public void testAddDriver() throws Exception {
        Driver actual = adminController.addDriver("driver", new Car("Jeep", 1111, "black"),"driverPass");
        assertNotNull(actual);
        assertEquals("driver", actual.getLogin());
        assertFalse(actual.isFree());
    }

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> actual = adminController.getTickets();
        assertEquals(tickets.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(tickets.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetClientById() throws Exception {
        Client actual = adminController.getClientById(client1.getId());
        assertEquals(client1, actual);

        Client actual2 = adminController.getClientById(client1.getId() + 1000);
        assertNull(actual2);
    }

    @Test
    public void testGetDriverById() throws Exception {
        Driver actual = adminController.getDriverById(driver1.getId());
        assertEquals(driver1, actual);

        Driver actual2 = adminController.getDriverById(driver1.getId() + 1000);
        assertNull(actual2);
    }

    @Test
    public void testGetTicketById() throws Exception {
        Ticket actual = adminController.getTicketById(ticket.getiDTicket());
        assertEquals(ticket, actual);
    }

    @Test
    public void testSetDriverToTicket() throws Exception {
        assertFalse(overLoadController.setDriverToTicket(client1.getId(), driver1.getId()));

        assertTrue(adminController.setDriverToTicket(client2.getId(), driver2.getId()));
    }

    @Test
    public void testFindTicketByClientId() throws Exception {
        assertEquals(ticket, adminController.findTicketByClientId(client1.getId()));
    }
}