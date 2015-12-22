package ua.artcode.model;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Date;


public class Ticket implements Serializable{

    private long idDriver;
    private long idClient;
    private String fromLocation;
    private String toLocation;
    private double price;
    private TicketStatus status;
    private Date arrivalTaxiTime;
    private Date requestTime;
    private Date arrivalDestinationTime;
    private long iDTicket;

    public Ticket(long idDriver, long idClient, String fromLocation, String toLocation,
                  double price, String status, Date arrivalTaxiTime, Date requestTime,
                  Date arrivalDestinationTime, long iDTicket) {

        this.idDriver = idDriver;
        this.idClient = idClient;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
        this.arrivalTaxiTime = arrivalTaxiTime;
        this.requestTime = requestTime;
        this.arrivalDestinationTime = arrivalDestinationTime;
        this.iDTicket = iDTicket;

        if(status.equals("NEW")){
            this.status = TicketStatus.NEW;
        } else if(status.equals("DONE")){
            this.status = TicketStatus.DONE;
        } else if(status.equals("IN_PROGRESS")){
            this.status = TicketStatus.IN_PROGRESS;
        } else if(status.equals("REJECTED")){
            this.status = TicketStatus.REJECTED;
        }

    }

    public long getIdDriver() {
        return idDriver;
    }

    public long getIdClient() {
        return idClient;
    }

    public String getFromLocation() {
        return fromLocation;
    }


    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Date getArrivalTaxiTime() {
        return arrivalTaxiTime;
    }

    public void setArrivalTaxiTime(Date arrivalTaxiTime) {
        this.arrivalTaxiTime = arrivalTaxiTime;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getArrivalDestinationTime() {
        return arrivalDestinationTime;
    }

    public void setArrivalDestinationTime(Date arrivalDestinationTime) {
        this.arrivalDestinationTime = arrivalDestinationTime;
    }

    public long getiDTicket() {
        return iDTicket;
    }

    @Override
    public String toString() {
        return idDriver +
                ";" + idClient +
                ";" + fromLocation +
                ";" + toLocation +
                ";" + price +
                ";" + status +
                ";" + arrivalTaxiTime.getTime() +
                ";" + requestTime.getTime() +
                ";" + arrivalDestinationTime.getTime() +
                ";" + iDTicket;
    }
}
