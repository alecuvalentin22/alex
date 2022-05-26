package com.example.mytourismapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class FamousPlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_places);

        ExtractJSON extractJSON = new ExtractJSON(){

            @Override
            protected void onPostExecute(String s) {

                carList.addAll(this.listOfCarsJSON);

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemenlistview, carList, getLayoutInflater())
                {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Car car1 = carList.get(position);

                        TextView tvPrice = view.findViewById(R.id.tvPrice);
                        if(car1.getPrice() > 20000)
                            tvPrice.setTextColor(Color.RED);
                        else
                            tvPrice.setTextColor(Color.GREEN);

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