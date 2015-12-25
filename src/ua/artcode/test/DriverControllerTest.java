package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.DriverController;
import ua.artcode.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhabenya on 24.12.15.
 */
public class DriverControllerTest {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    private Admin admin = new Admin("superadmin", "superpass", ID.genId());

    private Client client1 = new Client("Max", 111, "New York", "111", ID.genId());
    private Client client2 = new Client("John", 222, "New Jersey", "222", ID.genId());

    private Ticket ticket = new Ticket(0, client1.getId(), "Central Park", "Times square", 17.50, "NEW",
            new Date(), new Date(), new Date(), ID.genId());


    private Driver driver1 = new Driver("Ashton", new Car("BMW", 1234, "pink"), ID.genId(), true, ticket.getiDTicket(), "1234");
    private Driver driver2 = new Driver("Reese", new Car("Lexus", 2345, "green"), ID.genId(), false);

    private DriverController controller1;
    private DriverController controller2;

    @Before
    public void setUp() throws Exception {
        admins.add(admin);

        clients.add(client1);
        clients.add(client2);

        drivers.add(driver1);
        drivers.add(driver2);

        tickets.add(ticket);

        controller1 = new DriverController(driver1, new AppDataContainer(tickets, admins, clients, drivers));
        controller2 = new DriverController(driver2, new AppDataContainer(tickets, admins, clients, drivers));
    }

    @Test
    public void testGetCurrentTicket() throws Exception {
        assertNotNull(controller1.getCurrentTicket());
        assertEquals(ticket, controller1.getCurrentTicket());

        assertNull(controller2.getCurrentTicket());
    }

    @Test
    public void testChangeStatus() throws Exception {
        controller1.changeStatus();
        assertFalse(driver1.getStatus());

        controller2.changeStatus();
        assertTrue(driver2.getStatus());
    }

    @Test
    public void testGetTickets() throws Exception {
        List<Ticket> actual1 = controller1.getTickets();
        assertFalse(actual1.isEmpty());
        assertTrue(actual1.contains(ticket));

        List<Ticket> actual2 = controller2.getTickets();
        assertTrue(actual2.isEmpty());
    }

    @Test
    public void testTakeATicket() throws Exception {
        controller1.getCurrentTicket();
        Ticket actual1 = controller1.takeATicket();
        assertEquals(ticket.getiDTicket(), actual1.getiDTicket());
        assertEquals(TicketStatus.DONE, actual1.getStatus());
        assertTrue(driver1.isFree());
    }


}