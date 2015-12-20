package ua.artcode.test;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.utils.geolocation.GoogleMapsAPI;
import ua.artcode.utils.geolocation.GoogleMapsAPIImpl;
import ua.artcode.utils.geolocation.Location;

public class TestGoogleApi {


    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();

    @Test
    public void findLocation(){
        Location location = googleMapsAPI.findLocation("Україна", "Київ", "Бульва Лесі Українки", "5");
        Assert.assertNotNull(location);
    }


}
