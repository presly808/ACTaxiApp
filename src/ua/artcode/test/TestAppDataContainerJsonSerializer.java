package ua.artcode.test;

import org.junit.Assert;
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

/**
 * Created by serhii on 27.12.15.
 */
public class TestAppDataContainerJsonSerializer {

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


    @Before
    public void setUp() throws Exception {

        admins.add(admin);

        clients.add(client1);
        clients.add(client2);

        drivers.add(driver1);
        drivers.add(driver2);

        tickets.add(ticket);

        appDataContainer = new AppDataContainer(tickets, admins, clients, drivers);

    }

    @Test
    public void testSave(){
        String nameFile = "all.json";
        TaxiAppSave.save(appDataContainer);
        Assert.assertTrue(new File("./resources/db/" + nameFile).exists());
    }


    @Test
    public void testLoad(){
        String nameFile = "all.json";
        AppDataContainer container = TaxiAppLoader.loadContainer(nameFile);
        List<Client> clients = container.getListClients();
        clients.add(new Client("Log2", 2323, "Kiev", "2342", 2323));
        Assert.assertNotNull(container);
    }



}
