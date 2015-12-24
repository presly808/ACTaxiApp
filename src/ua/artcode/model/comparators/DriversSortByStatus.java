package ua.artcode.model.comparators;

import ua.artcode.exception.DriverIsNotInitInComparator;
import ua.artcode.model.Driver;

import java.util.Comparator;

/**
 * Created by dexter on 24.12.15.
 */
public class DriversSortByStatus implements Comparator<Driver>{

    @Override
    public int compare(Driver o1, Driver o2) {

        if(o1 == null || o2 == null){
            throw new DriverIsNotInitInComparator("one of Driver's reference was not initialized... ");
        }

        return o1.getStatus() == o2.getStatus() ? 0 :
                o1.getStatus() ? 1 : 2;
    }
}
