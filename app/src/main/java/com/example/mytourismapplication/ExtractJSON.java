package com.example.mytourismapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractJSON extends AsyncTask<URL, Void, String> {

    public List<City> listOfCities = new ArrayList<>();

    JSONArray cities = null;

    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)urls[0].openConnection();
            conn.setRequestMethod("GET");
            InputStream ist = conn.getInputStream();

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            String result = "";
            while((line = br.readLine())!=null)
                result+= line;

            //parsing JSON
            parseJSON(result);

            return  result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void parseJSON(String jsonStr)
    {
        if(jsonStr!=null)
        {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                cities = jsonObject.getJSONArray("Cities");

                for(int i=0;i<cities.length();i++)
                {
                    JSONObject object = cities.getJSONObject(i);

                    String name = object.getString("name");
                    String country = object.getString("country");
                    JSONArray places = object.getJSONArray("places");
                    List<Place> placesList = new ArrayList<>();

                    for(int j=0;j<places.length();j++) {

                        JSONObject placesJSONObject = places.getJSONObject(j);
                        String placeName = placesJSONObject.getString("name");
                        JSONArray transport = placesJSONObject.getJSONArray("transport");
                        List<Transport> transportList = new ArrayList<>();
                        for(int k=0;k<transport.length();k++) {

                            JSONObject jsonObject1 = transport.getJSONObject(k);
                            String station = jsonObject1.getString("station");
                            String transportType = jsonObject1.getString("transporType");

                            Transport transport1 = new Transport(station, transportType);
                            transportList.add(transport1);
                        }

                        Place place = new Place(placeName, transportList);
                        placesList.add(place);
                    }

                    City city = new City(name, country, placesList);
                    listOfCities.add(city);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Log.e("parseJSON", "JSON is null!");
    }
}
