package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.ClientController;
import ua.artcode.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by zhabenya on 24.12.15.
 */
public class ClientControllerTest {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    private Admin admin = new Admin("superadmin", "superpass", ID.genId());

    private Client client = new Client("Max", 111, "New York", "111", ID.genId());

    private Driver driver1 = new Driver("Ashton", new Car("BMW", 1234, "pink"), ID.genId(), true);
    private Driver driver2 = new Driver("Reese", new Car("Lexus", 2345, "green"), ID.genId(), true);
    private Driver driver3 = new Driver("Janet", new Car("Golf", 9876, "blue"), ID.genId(), true);

     private Ticket ticket = new Ticket(driver1.getId(), client.getId(), "Central Park", "Times square", 17.50, "NEW",
                new Date(), new Date(), new Date(), ID.genId());

    private ClientController controller;

    @Before
    public void setUp() throws Exception {

        admins.add(admin);

        clients.add(client);

        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);

        tickets.add(ticket);

        controller = new ClientController(client, new AppDataContainer(tickets, admins, clients, drivers));
    }

    @Test
    public void testCallTaxi() throws Exception {
        long ticketId = controller.callTaxi("Central Park", "Times square");
        assertNotNull(ticketId);
        assertEquals(ticketId, controller.getCurrentTicket().getiDTicket());
        assertEquals("Central Park", controller.getCurrentTicket().getFromLocation());
        assertEquals("Times square", controller.getCurrentTicket().getToLocation());
        assertEquals(client.getId(), controller.getCurrentTicket().getIdClient());
    }

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> actual = controller.getTickets();
        assertNotNull(actual);
        assertTrue(actual.contains(ticket));
    }
}