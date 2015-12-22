package ua.artcode.utils.database_gen;

import ua.artcode.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dexter on 21.12.15.
 */
public class DataBaseListsGen {

    public static List<Client> clientsListGen(int size) {

        ArrayList<Client> clients = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            clients.add(new Client("" + i, i, "" + i, "client", i));

        }

        return clients;
    }

    public static List<Admin> adminsListGen(int size) {

        ArrayList<Admin> admins = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            admins.add(new Admin("" + i, "admin", i));

        }

        return admins;
    }

    public static List<Driver> driversListGen(int size) {

        ArrayList<Driver> drivers = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            drivers.add(new Driver("" + i, new Car("" + i, i, "" + i), i));

        }

        return drivers;
    }

    public static List<Ticket> ticketsListGen(int size) {

        ArrayList<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            tickets.add(new Ticket(i, i, "Pecherskiy" + i, "Dniprovskiy" + i,
                    i, TicketStatus.NEW, new Date(), new Date(), new Date(), i));

        }

        return tickets;
    }

}
