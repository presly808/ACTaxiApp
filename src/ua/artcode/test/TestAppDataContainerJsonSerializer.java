package ua.artcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.controller.AppDataContainer;
import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.io.File;
import java.util.List;

/**
 * Created by serhii on 27.12.15.
 */
public class TestAppDataContainerJsonSerializer extends TestClass{

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
