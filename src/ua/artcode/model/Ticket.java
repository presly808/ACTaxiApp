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
                  double price, TicketStatus status, Date arrivalTaxiTime, Date requestTime,
                  Date arrivalDestinationTime, long iDTicket) {

        this.idDriver = Math.abs(idDriver);
        this.idClient = Math.abs(idClient);
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.price = price;
        this.arrivalTaxiTime = arrivalTaxiTime;
        this.requestTime = requestTime;
        this.arrivalDestinationTime = arrivalDestinationTime;
        this.iDTicket = Math.abs(iDTicket);
        this.status = status;

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

    public String getToLocation() {
        return toLocation;
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

    public void setIdDriver(long idDriver) {
        this.idDriver = idDriver;
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


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idDriver ^ (idDriver >>> 32));
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        result = 31 * result + (fromLocation != null ? fromLocation.hashCode() : 0);
        result = 31 * result + (toLocation != null ? toLocation.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (arrivalTaxiTime != null ? arrivalTaxiTime.hashCode() : 0);
        result = 31 * result + (requestTime != null ? requestTime.hashCode() : 0);
        result = 31 * result + (arrivalDestinationTime != null ? arrivalDestinationTime.hashCode() : 0);
        result = 31 * result + (int) (iDTicket ^ (iDTicket >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object ticket) {
        if (this == ticket) return true;
        if (ticket == null || getClass() != ticket.getClass()) return false;

        Ticket tmp = (Ticket) ticket;

        if (idDriver != tmp.idDriver) return false;
        if (idClient != tmp.idClient) return false;
        if (Double.compare(tmp.price, price) != 0) return false;
        if (iDTicket != tmp.iDTicket) return false;
        if (fromLocation != null ? !fromLocation.equals(tmp.fromLocation) : tmp.fromLocation != null)
            return false;
        if (toLocation != null ? !toLocation.equals(tmp.toLocation) : tmp.toLocation != null) return false;
        if (status != tmp.status) return false;
        if (arrivalTaxiTime != null ? !arrivalTaxiTime.equals(tmp.arrivalTaxiTime) : tmp.arrivalTaxiTime != null)
            return false;
        if (requestTime != null ? !requestTime.equals(tmp.requestTime) : tmp.requestTime != null) return false;

        return !(arrivalDestinationTime != null ? !arrivalDestinationTime.equals(tmp.arrivalDestinationTime) : tmp.arrivalDestinationTime != null);

    }
}
