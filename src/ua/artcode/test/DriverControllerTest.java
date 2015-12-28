package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.DriverController;
import ua.artcode.model.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhabenya on 24.12.15.
 */
public class DriverControllerTest extends TestClass{

    @Before
    public void setUp() throws Exception {
        admins.add(admin);

        clients.add(client1);
        clients.add(client2);

        drivers.add(driver1);
        drivers.add(driver2);

        tickets.add(ticket);

        driverController1 = new DriverController(driver1, new AppDataContainer(tickets, admins, clients, drivers));
        driverController2 = new DriverController(driver2, new AppDataContainer(tickets, admins, clients, drivers));
    }

    @Test
    public void testGetCurrentTicket() throws Exception {
        assertNotNull(driverController1.getCurrentTicket());
        assertEquals(ticket, driverController1.getCurrentTicket());

        assertNull(driverController2.getCurrentTicket());
    }

    @Test
    public void testChangeStatus() throws Exception {
        driverController1.changeStatus();
        assertFalse(driver1.getStatus());

        driverController2.changeStatus();
        assertTrue(driver2.getStatus());
    }

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> actual1 = driverController1.getTickets();
        assertFalse(actual1.isEmpty());
        assertTrue(actual1.contains(ticket));

        List<Ticket> actual2 = driverController2.getTickets();
        assertTrue(actual2.isEmpty());
    }

    @Test
    public void testTakeATicket() throws Exception {
        driverController1.getCurrentTicket();
        Ticket actual1 = driverController1.takeATicket();
        assertEquals(ticket.getiDTicket(), actual1.getiDTicket());
        assertEquals(TicketStatus.DONE, actual1.getStatus());
        assertTrue(driver1.isFree());
    }


}