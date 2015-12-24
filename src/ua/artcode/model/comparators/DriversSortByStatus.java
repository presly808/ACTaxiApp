package ua.artcode.model.comparators;

import ua.artcode.exception.TicketIsNotInitInComparator;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.Comparator;

/**
 * Created by dexter on 24.12.15.
 */
public class DriversSortByStatus implements Comparator<Driver>{

    @Override
    public int compare(Driver o1, Driver o2) {

        if(o1 == null || o2 == null){
            throw new TicketIsNotInitInComparator();
        }

        return o1.getStatus() == o2.getStatus() ? 0 :
                o1.getStatus() ? 1 : 2;
    }
}
