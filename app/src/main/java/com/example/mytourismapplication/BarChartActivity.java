package com.example.mytourismapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartActivity extends AppCompatActivity {

    ArrayList<Itinerary> list;
    LinearLayout layout;
    Map<String, Integer> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        //Intent intent = getIntent();

        //list = (ArrayList<Itinerary>)intent.getSerializableExtra("list");

        ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());
        List<Itinerary> itineraries = database.getItinerariesDAO().getAll();

        source = getSource(itineraries);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new BarChartView(getApplicationContext(), source));
    }

    private Map<String, Integer> getSource(List<Itinerary> itineraries)
    {
        if(itineraries==null || itineraries.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String, Integer> results = new HashMap<>();
            for(Itinerary itinerary: itineraries)
                if(results.containsKey(itinerary.getTransportMethod()))
                    results.put(itinerary.getTransportMethod(), results.get(itinerary.getTransportMethod())+1);
                else
                    results.put(itinerary.getTransportMethod(), 1);
            return results;
        }
    }
}