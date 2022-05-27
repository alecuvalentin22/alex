package com.example.mytourismapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddItineraryActivity extends AppCompatActivity {

    private CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11;
    private Button saveBtn;

    private TextView textViewProgress;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private DatePicker datePicker;

    private EditText etItineraryName;


    private List<String> locations = new ArrayList<>();
    private Switch switchRecomm;
    boolean wantsRecommend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_itinerary);



        Spinner spinnerTransport = findViewById(R.id.spinnerTransport);
        String[] transportArray = {"Tram", "Bus", "Subway"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, transportArray);
        spinnerTransport.setAdapter(adapterSpinner);


        textViewProgress = findViewById(R.id.textViewProgress);
        progressBar = findViewById(R.id.progressBar);
        seekBar = findViewById(R.id.seekBar);
        cb1 = findViewById(R.id.cbLoc1);
        cb2 = findViewById(R.id.cbLoc2);
        cb3 = findViewById(R.id.cbLoc3);
        cb4 = findViewById(R.id.cbLoc4);
        cb5 = findViewById(R.id.cbLoc5);
        cb6 = findViewById(R.id.cbLoc6);
        cb7 = findViewById(R.id.cbLoc7);
        cb8 = findViewById(R.id.cbLoc8);
        cb9 = findViewById(R.id.cbLoc9);
        cb10 = findViewById(R.id.cbLoc10);
        cb11= findViewById(R.id.cbLoc11);
        switchRecomm = findViewById(R.id.switchRecomm);
        datePicker = findViewById(R.id.datePickerItinerary);
        etItineraryName = findViewById(R.id.editTextItineraryName);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textViewProgress.setText("" + progress +"%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        saveBtn = findViewById(R.id.buttonSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked()){
                    locations.add(cb1.getText().toString().trim());
                }
                if(cb2.isChecked()){
                    locations.add(cb2.getText().toString().trim());
                }
                if(cb3.isChecked()){
                    locations.add(cb3.getText().toString().trim());
                }
                if(cb4.isChecked()){
                    locations.add(cb4.getText().toString().trim());
                }
                if(cb5.isChecked()){
                    locations.add(cb5.getText().toString().trim());
                }
                if(cb6.isChecked()){
                    locations.add(cb6.getText().toString().trim());
                }
                if(cb7.isChecked()){
                    locations.add(cb7.getText().toString().trim());
                }
                if(cb8.isChecked()){
                    locations.add(cb8.getText().toString().trim());
                }
                if(cb9.isChecked()){
                    locations.add(cb9.getText().toString().trim());
                }
                if(cb10.isChecked()){
                    locations.add(cb10.getText().toString().trim());
                }
                if(cb11.isChecked()) {
                    locations.add(cb11.getText().toString().trim());
                }

                switchRecomm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            wantsRecommend = true;
                        } else{
                            wantsRecommend = false;
                        }
                    }
                });

                String transport = spinnerTransport.getSelectedItem().toString();
                int excitement = seekBar.getProgress();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                String _date = day+"/"+month+"/"+year;
                String itineraryName ="";
                if(!etItineraryName.getText().toString().isEmpty()){
                    itineraryName = etItineraryName.getText().toString();
                }


                String locationsString="";

                for(String s : locations){
                    locationsString = locationsString + s + ";";
                }
                Itinerary itinerary = new Itinerary(locationsString, transport, wantsRecommend, _date, excitement, itineraryName);

                String _username = getIntent().getStringExtra("username");

                ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());
                database.getItinerariesDAO().insert(itinerary);
                User user = new User(_username);
                database.getUsersDAO().insert(user);
                Toast.makeText(getApplicationContext(), "Successfully saved in Room DB for user " +user.getName(), Toast.LENGTH_LONG).show();


            }
        });


    }
}