package ua.artcode.model.comparators;

import ua.artcode.exception.TicketIsNotInitInComparator;
import ua.artcode.model.Ticket;

import java.util.Comparator;

/**
 * Created by dexter on 24.12.15.
 */
public class TicketsSortByStatus implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {

        if(o1 == null || o2 == null){
            throw new TicketIsNotInitInComparator();
        }

        return o1.getStatus().ordinal() == o2.getStatus().ordinal() ? 0 :
                o1.getStatus().ordinal() < o2.getStatus().ordinal() ? 1 : 2;
    }
}
