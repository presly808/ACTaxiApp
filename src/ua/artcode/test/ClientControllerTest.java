package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.ClientController;
import ua.artcode.model.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by zhabenya on 24.12.15.
 */
public class ClientControllerTest extends TestClass{

    @Before
    public void setUp() throws Exception {

        admins.add(admin);

        clients.add(client1);

        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);

        tickets.add(ticket);

        clientController = new ClientController(client1, new AppDataContainer(tickets, admins, clients, drivers));
    }

    @Test
    public void testCallTaxi() throws Exception {
        long ticketId = clientController.callTaxi("Central Park", "Times square");
        assertNotNull(ticketId);
        assertEquals(ticketId, clientController.getCurrentTicket().getiDTicket());
        assertEquals("Central Park", clientController.getCurrentTicket().getFromLocation());
        assertEquals("Times square", clientController.getCurrentTicket().getToLocation());
        assertEquals(client1.getId(), clientController.getCurrentTicket().getIdClient());
    }

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> actual = clientController.getTickets();
        assertNotNull(actual);
        assertTrue(actual.contains(ticket));
    }
}
