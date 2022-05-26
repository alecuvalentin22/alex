package com.example.mytourismapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewDB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView sv = new
                HorizontalScrollView(this);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        ItinerariesDB database = ItinerariesDB.getInstance(getApplicationContext());
        List<Itinerary> fxRates = database.getItinerariesDAO().getAll();

        ArrayAdapter<Itinerary> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fxRates);

        ListView lv = new ListView(this);
        //asociere adaptor
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                Itinerary fxRate = fxRates.get(position);

                ArrayAdapter adapter = (ArrayAdapter) lv.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(ViewDB.this)
                        .setTitle("Delete confirmation")
                        .setMessage("Are you sure?")
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Nothing deleted!", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fxRates.remove(fxRate);

                                ItinerariesDB fxRatesDB = ItinerariesDB.getInstance(getApplicationContext());
                                fxRatesDB.getItinerariesDAO().delete(fxRate);

                                adapter.notifyDataSetChanged();

                                Toast.makeText(getApplicationContext(), "The record has been deleted!", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        });

        TextView tv1 = new TextView(this);
        tv1.setText("List of itineraries from the database:"+"\n");


        ll.addView(tv1);
        ll.addView(lv);
        sv.addView(ll);
        setContentView(sv);
    }
}