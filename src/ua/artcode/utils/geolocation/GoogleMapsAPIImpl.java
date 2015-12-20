package ua.artcode.utils.geolocation;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by serhii on 20.12.15.
 */
public class GoogleMapsAPIImpl implements GoogleMapsAPI {


    public static final String GET_FORMATTED_QUERY_TEMPLATE = "https://maps.googleapis.com/maps/api/geocode/json?address=%s,%s,%s&key=%s";
    public static final String GET_UNFORMATTED_QUERY_TEMPLATE = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";
    public static final String GOOGLE_API_KEY = "AIzaSyAw1tCKgkKOkkCaun42Em3kCf0R9Oo7tZY";

    @Override
    public Location findLocation(String unformatted) {
        final String preparedQuery = String.format(GET_UNFORMATTED_QUERY_TEMPLATE, prepareQueryReplaceSpaces(unformatted), GOOGLE_API_KEY);

        try {
            String jsonResponse = sendGetRequest(preparedQuery);
            return convert(jsonResponse);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String prepareQueryReplaceSpaces(String unformatted) {
        return unformatted.replaceAll(" ", "+");
    }

    @Override//todo finish all task
    public Location findLocation(String country, String city, String street, String houseNum) {
        final String preparedQuery = String.format(GET_FORMATTED_QUERY_TEMPLATE, prepareQueryReplaceSpaces((houseNum + " " + street)), city, country, GOOGLE_API_KEY);

        try {
            String jsonResponse = sendGetRequest(preparedQuery);
            return convert(jsonResponse);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Location convert(String jsonResponse) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject root = (JSONObject) jsonParser.parse(jsonResponse);

        JSONArray result = (JSONArray) root.get("results");
        JSONObject firstFound = (JSONObject) result.get(0);


        JSONObject geometry = (JSONObject) firstFound.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");

        String addressFormattedName = firstFound.get("formatted_address").toString();
        String lat =  location.get("lat").toString();
        String lng = location.get("lng").toString();

        return new Location(addressFormattedName,
                                    Double.parseDouble(lat), Double.parseDouble(lng));
    }

    private String sendGetRequest(String preparedQuery) throws IOException {
        URL obj = new URL(preparedQuery);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        return getStringContent(con.getInputStream());


    }

    private String getStringContent(InputStream is) throws IOException{
        Scanner sc = new Scanner(is);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()){
            sb.append(sc.nextLine()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public double getDistance(Location pointA, Location pointB) {
        return 0;
    }
}
