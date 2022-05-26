package com.example.mytourismapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<City> {

    private Context context;
    private int resource;
    private List<City> cityList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<City> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.cityList = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        City city = cityList.get(position);

        if (city!=null)
        {
            TextView tv1 = view.findViewById(R.id.tvName);
            tv1.setText(city.getName());

            TextView tv2 = view.findViewById(R.id.tvCountry);
            tv2.setText(city.getCountry());

            TextView tv3 = view.findViewById(R.id.tvPlaces);
            tv3.setText(city.getPlaces().toString());
        }
        return view;
    }
}
