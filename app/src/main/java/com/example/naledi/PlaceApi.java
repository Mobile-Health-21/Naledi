package com.example.naledi;


import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PlaceApi {

    private  PlaceApi () {}
    private  static PlaceApi placeApi = new PlaceApi();

    private static String Location;
    private static String Keyword = "public";

    private static final String Apikey = "AIzaSyA7pz2LEKG5CJSHZo7ya5yUsHGYobeI0lo";
    private static final String Radius = "11500";
    private static final String Type = "hospital";
    private static final String BASE_URL =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";


    public static void setLocation(String location) {
        Location = location;
        URL url = buildUrl();
        new GetHospitals().execute(url);
    }


    public static void setKeyword(String keyword) {
        Keyword = keyword;
    }


    //Create url

    protected static URL buildUrl() {

        String fullUrl = BASE_URL + "location=" + Location + "&radius=" + Radius
                + "&type=" + Type + "&keyword" + Keyword + "&key=" + Apikey;

        URL url = null;

        try {
            url = new URL(fullUrl);
        } catch (Exception e) {
            Log.d("Error", "URL error");
        }

        return url;
    }

    // Get data

    protected static String connectToApi(URL url) throws IOException{

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();

            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e){
            Log.d("Connection try block", "Error in the block");
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    public static PlaceApi getInstance() {
        return placeApi;
    }


    public static class GetHospitals extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL completeUrl = urls[0];
            String result = null;
            try {

                result = connectToApi(completeUrl);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Home.setHospitals(s);
        }
    }
}
