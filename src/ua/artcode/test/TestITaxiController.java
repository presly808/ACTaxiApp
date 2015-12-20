package ua.artcode.test;

import org.junit.Test;
import ua.artcode.controller.ITaxiController;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestITaxiController {

 private ITaxiController controller; // todo assign implementation when it will be finished

 @Test
 public void testLogin() throws Exception{
  Client client = new Client("Login", 333333, "Home", 300, "12345", 45);
  assertNotNull(controller.login(client.getLogin(),client.getPass()));
 }

 @Test
 public void testGetAllClients() throws Exception {
  Client client1 = new Client("Login1", 333333, "Home", 300, "12345", 23);
  Client client2 = new Client("Login2", 333332, "Home", 300, "12345", 33);

  List<Client> clients = new ArrayList<>();
  clients.add(client1);
  clients.add(client2);
  List<Client> clientsFromController = controller.getAllClients();
  assertEquals(clients, clientsFromController);
 }

 @Test
 public void testGetAllDrivers() throws Exception {
  List<Driver> driversFromController = controller.getAllDrivers();
  List<Driver> drivers = new ArrayList<>();
  assertEquals(drivers , driversFromController);
 }


 @Test
 public void testGetTickets() throws Exception {
  List<Ticket> ticketsFromController = controller.getTickets();
  List<Ticket> tickets = new ArrayList<>();
  tickets.containsAll(ticketsFromController);
  assertEquals(tickets, ticketsFromController);
 }

 @Test
 public void testGetClientById() throws Exception {
  List<Ticket> ticketsFromController = controller.getTickets();

  ticketsFromController.get()
 }
}
