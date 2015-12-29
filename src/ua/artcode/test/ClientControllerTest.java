package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.ClientController;
import ua.artcode.exception.NoTicketsException;
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

        ticketsDone.add(ticketDone);
        ticketsNew.add(ticketNew);

        clientController = new ClientController(client1, new AppDataContainer(ticketsDone, admins, clients, drivers));
        clientController2 = new ClientController(client1, new AppDataContainer(ticketsNew, admins, clients, drivers));
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
        assertTrue(actual.contains(ticketDone));
    }

    @Test
    public void testGetCurrentTaxi() throws Exception{

        Ticket ticket = clientController2.getCurrentTicket();
        assertNotNull(ticket);
        assertEquals("Central Park", clientController2.getCurrentTicket().getFromLocation());
        assertEquals("Times square", clientController2.getCurrentTicket().getToLocation());
        assertEquals(client1.getId(), clientController2.getCurrentTicket().getIdClient());

    }

    @Test
    public void testRejectTaxi() throws Exception{

        clientController2.rejectTaxi();
        try{
            Ticket ticket = clientController2.getCurrentTicket();
            assertEquals(null , ticket);
        }catch (NoTicketsException e){
            //everything OK
        }

    }
}
