package ua.artcode.test;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.controller.ControllerFactory;
import ua.artcode.controller.ITaxiController;
import ua.artcode.controller.Login;
import ua.artcode.model.Client;
import ua.artcode.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class TestITaxiController {

    private ITaxiController controller; // todo assign implementation when it will be finished


    @Before
    public void setUp() throws Exception {
        AppDataContainer appDataContainer = new AppDataContainer();
        controller = ControllerFactory.getAdminController(appDataContainer);
//        controller.getAllClients().add(new Client("Login1", 111111, "Home1", "12345", 1));
//        controller.getAllClients().add(new Client("Login2", 222222, "Home2", "12345", 2));
//        controller.getAllClients().add(new Client("Login3", 333333, "Home3", "12345", 3));
//        controller.getAllDrivers().add(new Driver("Driver1", new Car(), 1));
//        controller.getAllDrivers().add(new Driver("Driver2", new Car(), 2));
//        controller.getAllDrivers().add(new Driver("Driver3", new Car(), 3));

    }

    //// TODO: 20.12.15
// @Test
// public void testLogin() throws Exception{
//  Client client = new Client("Login", 333333, "Home", 300, "12345", 45);
//  assertNotNull(controller.login(client.getLogin(), client.getPass()));
// }

    /* @Test
 public void testLogin() throws Exception{
  Client client = new Client("Login", 333333, "Home", 300, "12345", 45);
  assertNotNull(controller.login(client.getLogin(), client.getPass()));
 }*/

    @Test
    public void testGetAllClients() throws Exception {
        Client client1 = new Client("Login1", 333333, "Home", "12345", 23);
        Client client2 = new Client("Login2", 333332, "Home", "12345", 33);

        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
//      List<Client> clientsFromController = controller.getAllClients();
//        assertEquals(clients, clientsFromController);
    }

    @Test
    public void testGetAllDrivers() throws Exception {
//        List<Driver> driversFromController = controller.getAllDrivers();
//        List<Driver> drivers = new ArrayList<>();
//        assertEquals(drivers, driversFromController);
    }


    @Test
    public void testGetTickets() throws Exception {
//        List<Ticket> ticketsFromController = controller.getTickets();
        List<Ticket> tickets = new ArrayList<>();
//        assertEquals(tickets, ticketsFromController);
    }

    @Test
    public void testGetClientById() throws Exception {
//        assertNotNull(controller.getClientById(controller.getAllClients().get(1).getiD()));
//        assertEquals(controller.getClientById(controller.getAllClients().get(1).getiD()).getiD(), 1);
    }

    @Test
    public void testGetDriverBtId() throws Exception {
//        assertNotNull(controller.getDriverById(controller.getAllDrivers().get(1).getiDDriver()));
//        assertEquals(controller.getDriverById(controller.getAllDrivers().get(1).getiDDriver()).getiDDriver(), 1);
    }

    @Test
    public void testGetTicketById() throws Exception {
//        assertNotNull(controller.getTicketById(controller.getTickets().get(1).getiDTicket()));

    }

    @Test
    public void testSetDriverToTicket() throws Exception {


    }

    @Test
    public void testFindTicketByClientId() throws Exception {


    }

    @Test
    public void testLogin() throws Exception {
        Login login = new Login();
        Client client = login.addClient("John", 9992233, "Kiev", "password");
        ITaxiController controller = login.login(client.getLogin(), client.getPass());
        assertNotNull(controller);


    }
}
