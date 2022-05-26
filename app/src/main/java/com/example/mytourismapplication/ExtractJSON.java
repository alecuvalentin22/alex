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

    public List<Car> listOfCarsJSON = new ArrayList<>();

    JSONArray cars = null;

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
                cars = jsonObject.getJSONArray("cars");

                for(int i=0;i<cars.length();i++)
                {
                    JSONObject object = cars.getJSONObject(i);

                    String brand = object.getString("brand");
                    Date manufacturingDate = new Date(object.getString("manufacturingDate"));
                    String colour = object.getString("colour");
                    int price = Integer.parseInt(object.getString("price"));
                    String engineType = object.getString("engineType");

                    Car car = new Car(brand, manufacturingDate, colour, price, engineType);
                    listOfCarsJSON.add(car);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Log.e("parseJSON", "JSON is null!");
    }
}
