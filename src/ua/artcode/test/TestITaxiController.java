package ua.artcode.test;

import org.junit.Test;
import ua.artcode.controller.ITaxiController;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by serhii on 20.12.15.
 */
public class TestITaxiController {

 @Test
 public void testLogin() throws Exception{
  Client client = new Client("Login", 333333, "Home", 300, "12345", 34);
  assertTrue(true);

 }

 @Test
 public void testGetAllClients(ITaxiController iTaxiController) throws Exception {
  Client client1 = new Client("Login1", 333333, "Home", 300, "12345", 23);
  Client client2 = new Client("Login2", 333332, "Home", 300, "12345", 23);

  List<Client> clients = new ArrayList<>();
  clients.add(client1);
  clients.add(client2);
  List<Client> clientsFromController = iTaxiController.getAllClients();
  assertEquals(clients, clientsFromController);
 }

 @Test
 public void testGetAllDrivers(ITaxiController iTaxiController) throws Exception {
  List<Driver> driversFromController = iTaxiController.getAllDrivers();
  List<Driver> drivers = new ArrayList<>();
  assertEquals(drivers , driversFromController);
 }


 @Test
 public void testGetTickets(ITaxiController iTaxiController) throws Exception {
  List<Ticket> ticketsFromController = iTaxiController.getTickets();
  List<Ticket> tickets = new ArrayList<>();
  tickets.containsAll(ticketsFromController);
  assertEquals(tickets, ticketsFromController);
 }

 @Test
 public void testGetClientById(ITaxiController iTaxiController) throws Exception {
  List<Ticket> ticketsFromController = iTaxiController.getTickets();

  ticketsFromController.get(1);
 }
}
