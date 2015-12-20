package ua.artcode.model;

import java.util.Date;


public class Ticket {

    private int idDriver;
    private int idClient;
    private String fromLocation;
    private String toLocation;
    private double price;
    private TicketStatus status;
    private Date arrivalTaxiTime;
    private Date requestTime;
    private Date arrivalDestinationTime;
    private ID iDTicket = new ID();

    public Ticket(int idDriver, int idClient, String fromLocation, String toLocation,
                  double price, TicketStatus status, Date arrivalTaxiTime, Date requestTime, Date arrivalDestinationTime) {

        this.idDriver = idDriver;
        this.idClient = idClient;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
        this.status = status;
        this.arrivalTaxiTime = arrivalTaxiTime;
        this.requestTime = requestTime;
        this.arrivalDestinationTime = arrivalDestinationTime;

    }

    public int getIdDriver() {
        return idDriver;
    }

    public int getIdClient() {
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
        return iDTicket.getID();
    }
}
