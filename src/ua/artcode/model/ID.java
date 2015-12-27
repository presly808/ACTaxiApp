package ua.artcode.model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Date;


/**
 * Created by dexter on 20.12.15.
 */
public class ID {

    private long iD;

    public ID() {
        Date date = new Date();
        this.iD = (date.getTime());
    }

    public long getId() {
        return iD;
    }

    public static long genId(int num){
        return new ID().getId() ^ num;
    }
}
