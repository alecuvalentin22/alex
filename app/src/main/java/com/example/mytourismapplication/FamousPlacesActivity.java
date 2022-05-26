package com.example.mytourismapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FamousPlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_places);

        List<City> cityList = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);

        ExtractJSON extractJSON = new ExtractJSON(){

            @Override
            protected void onPostExecute(String s) {

                cityList.addAll(this.listOfCities);

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemenlistview, cityList, getLayoutInflater())
                {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        City city = cityList.get(position);

                        TextView tvName = view.findViewById(R.id.tvName);
                        TextView tvCountry = view.findViewById(R.id.tvCountry);
                        TextView tvPlaces = view.findViewById(R.id.tvPlaces);

                        return view;
                    }
                };
                listView.setAdapter(adapter);
            }
        };
        try {
            extractJSON.execute(new URL("https://pastebin.com/raw/sHgWu4n1"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}