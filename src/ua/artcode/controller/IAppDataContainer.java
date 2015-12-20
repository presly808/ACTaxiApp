package ua.artcode.controller;

import ua.artcode.model.Client;
import ua.artcode.model.Driver;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public interface IAppDataContainer {

    void addClientToData(Client client);
    void addDriverToData(Driver driver);
    List getListClients();
    List getListDrivers();


}
