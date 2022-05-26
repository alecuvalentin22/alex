package com.example.mytourismapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Homepage extends AppCompatActivity {

    private ImageView createItineraryIV, showItinerariesIV, showAnalyticsIV, showFamousPlacesIV;

    private EditText etUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        createItineraryIV = findViewById(R.id.createItineraryIV);
        showItinerariesIV = findViewById(R.id.showItinerariesIV);
        showAnalyticsIV = findViewById(R.id.showAnalyticsIV);
        showFamousPlacesIV = findViewById(R.id.showJsonIV);
        etUser = findViewById(R.id.editTextName);

        createItineraryIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItineraryActivity.class);
                String _username = Objects.requireNonNull(etUser.getText().toString());
                intent.putExtra("username", _username);
                startActivity(intent);
            }
        });

        showItinerariesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItineraryActivity.class);
                startActivity(intent);
            }
        });

        showAnalyticsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItineraryActivity.class);
                startActivity(intent);
            }
        });

        showFamousPlacesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItineraryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.option1:
                Intent intent2 = new Intent(this, ViewDB.class);
                startActivity(intent2);
                break;
//            case R.id.option2:
//
//                ExtractXML extractXML = new ExtractXML()
//                {
//                    @Override
//                    protected void onPostExecute(InputStream inputStream) {
//
//                        carList.addAll(this.listOfCars);
//                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemenlistview, carList, getLayoutInflater())
//                        {
//                            @NonNull
//                            @Override
//                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                                View view = super.getView(position, convertView, parent);
//
//                                Car car1 = carList.get(position);
//
//                                TextView tvPrice = view.findViewById(R.id.tvPrice);
//                                if(car1.getPrice() > 20000)
//                                    tvPrice.setTextColor(Color.RED);
//                                else
//                                    tvPrice.setTextColor(Color.GREEN);
//
//                                return view;
//                            }
//                        };
//                        listView.setAdapter(adapter);
//                    }
//                };
//                try {
//                    extractXML.execute(new URL("https://pastebin.com/raw/iVR4a0FB"));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case R.id.option3:
//
//                ExtractJSON extractJSON = new ExtractJSON(){
//
//                    @Override
//                    protected void onPostExecute(String s) {
//
//                        carList.addAll(this.listOfCarsJSON);
//
//                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemenlistview, carList, getLayoutInflater())
//                        {
//                            @NonNull
//                            @Override
//                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                                View view = super.getView(position, convertView, parent);
//
//                                Car car1 = carList.get(position);
//
//                                TextView tvPrice = view.findViewById(R.id.tvPrice);
//                                if(car1.getPrice() > 20000)
//                                    tvPrice.setTextColor(Color.RED);
//                                else
//                                    tvPrice.setTextColor(Color.GREEN);
//
//                                return view;
//                            }
//                        };
//                        listView.setAdapter(adapter);
//                    }
//                };
//                try {
//                    extractJSON.execute(new URL("https://pastebin.com/raw/sgne6vdm"));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case R.id.option4:
//
//                Intent intent2 = new Intent(this, ViewBDActivity.class);
//                startActivity(intent2);
//
//                break;
//            case R.id.option5:
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-1097-default-rtdb.firebaseio.com/");
//                DatabaseReference myRef = database.getReference("mad-1097-default-rtdb");
//                myRef.keepSynced(true);
//
//                ValueEventListener listener = new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        if(snapshot.exists())
//                        {
//                            carList.clear();
//                            for(DataSnapshot dn: snapshot.getChildren())
//                            {
//                                Car car = dn.getValue(Car.class);
//                                carList.add(car);
//                            }
//                        }
//                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemenlistview, carList, getLayoutInflater())
//                        {
//                            @NonNull
//                            @Override
//                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                                View view = super.getView(position, convertView, parent);
//
//                                Car car1 = carList.get(position);
//
//                                TextView tvPrice = view.findViewById(R.id.tvPrice);
//                                if(car1.getPrice() > 20000)
//                                    tvPrice.setTextColor(Color.RED);
//                                else
//                                    tvPrice.setTextColor(Color.GREEN);
//
//                                return view;
//                            }
//                        };
//                        listView.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                };
//                myRef.child("mad-1097-default-rtdb").addValueEventListener(listener);
//
//                break;
//
//            case R.id.option6:
//
//                Intent intent1 = new Intent(this, BarChartActivity.class);
//                intent1.putExtra("list", (ArrayList)carList);
//                startActivity(intent1);
//
//                break;
//
//
//            case R.id.option7:
//
//                Intent intent3 = new Intent(this, MapsActivity.class);
//                startActivity(intent3);
//
//                break;
//
//            case R.id.option8:
//                Intent intent4 = new Intent(this, SettingsActivity.class);
//                startActivity(intent4);
//                break;
        }

        return true;
    }
}