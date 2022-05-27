package com.example.mytourismapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Homepage extends AppCompatActivity {

    private ImageView createItineraryIV, showItinerariesIV, showAnalyticsIV, showFamousPlacesIV;

    private EditText etUser;
    String allUsers = "";
    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        createItineraryIV = findViewById(R.id.createItineraryIV);
        showItinerariesIV = findViewById(R.id.showItinerariesIV);
        showAnalyticsIV = findViewById(R.id.showAnalyticsIV);
        showFamousPlacesIV = findViewById(R.id.showJsonIV);
        etUser = findViewById(R.id.editTextName);

        ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());

        createItineraryIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users = database.getUsersDAO().getAllUsers();
                database.getUsersDAO().deleteAll();
                allUsers="";
                for (User u : users) {
                    allUsers += " " + u.getName() + " ; ";
                }
                if (etUser.getText().toString().equals("")) {
                    users = database.getUsersDAO().getAllUsers();
                    for (User u : users) {
                        allUsers += " " + u.getName() + " ; ";
                    }
                    etUser.setError("Please fill in the username!");
                    Toast.makeText(Homepage.this, "The existing users are:" + allUsers + "!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), AddItineraryActivity.class);
                    String _username = Objects.requireNonNull(etUser.getText().toString());
                    intent.putExtra("username", _username);
                    startActivity(intent);
                }
            }
        });

        showItinerariesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), ViewDB.class);
                startActivity(intent2);
            }
        });

        showAnalyticsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), BarChartActivity.class);
                ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());
                List<Itinerary> itineraries = database.getItinerariesDAO().getAll();
                intent1.putExtra("list", (ArrayList) itineraries);
                startActivity(intent1);
            }
        });

        showFamousPlacesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FamousPlacesActivity.class);
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

        switch (item.getItemId()) {
            case R.id.option1:
                Intent intent2 = new Intent(this, ViewBusTravels.class);
                startActivity(intent2);
                break;
            case R.id.option2:
                Intent intent1 = new Intent(this, BarChartActivity.class);
                ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());
                List<Itinerary> itineraries = database.getItinerariesDAO().getAll();
                intent1.putExtra("list", (ArrayList) itineraries);
                startActivity(intent1);
                break;

            case R.id.option3:
                updateData();
                break;
        }

        return true;
    }

    private void updateData() {

        ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());
        users = database.getUsersDAO().getAllUsers();
        allUsers="";
        for (User u : users) {
            allUsers += " " + u.getName() + " ; ";
        }
        if (!allUsers.contains(etUser.getText().toString()) || etUser.getText().toString().isEmpty())
            Toast.makeText(this, "There is no user with this username", Toast.LENGTH_LONG).show();
        else {

            AlertDialog.Builder myDialog = new AlertDialog.Builder(Homepage.this);
            LayoutInflater inflater = LayoutInflater.from(Homepage.this);
            View mView = inflater.inflate(R.layout.update_user_dialog, null);

            myDialog.setView(mView);
            final AlertDialog dialog = myDialog.create();

            final TextView usernameTV = mView.findViewById(R.id.textViewUsername);
            final EditText usernameET = mView.findViewById(R.id.update_userNameET);

            usernameTV.setText(etUser.getText().toString());

            Button deleteBtn = mView.findViewById(R.id.deleteBtn);
            Button updateBtn = mView.findViewById(R.id.updateBtn);

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newName = usernameET.getText().toString();
                    if (TextUtils.isEmpty(newName)) {
                        usernameET.setError("Name is required");
                        return;
                    }
                    String existingUsername = etUser.getText().toString();
                    database.getUsersDAO().update(newName, existingUsername);
                    Toast.makeText(getApplicationContext(), "User updated successfuly", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String existingUsername = etUser.getText().toString();
                    database.getUsersDAO().delete(database.getUsersDAO().getByName(existingUsername));
                    Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }
}