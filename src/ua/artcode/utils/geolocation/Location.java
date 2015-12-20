package ua.artcode.utils.geolocation;

/**
 * Created by serhii on 20.12.15.
 */
public class Location {

    private String formattedAddress;
    private double lat;
    private double lng;

    public Location(String formattedAddress, double lat, double lng) {
        this.formattedAddress = formattedAddress;
        this.lat = lat;
        this.lng = lng;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
