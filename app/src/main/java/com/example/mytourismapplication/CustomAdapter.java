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

public class CustomAdapter extends ArrayAdapter<Car> {

    private Context context;
    private int resource;
    private List<Car> carList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<Car> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.carList = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Car car = carList.get(position);

        if (car!=null)
        {
            TextView tv1 = view.findViewById(R.id.tvBrand);
            tv1.setText(car.getBrand());

            TextView tv2 = view.findViewById(R.id.tvDate);
            tv2.setText(car.getManufacturingDate().toString());

            TextView tv3 = view.findViewById(R.id.tvColour);
            tv3.setText(car.getColour());

            TextView tv4 = view.findViewById(R.id.tvPrice);
            tv4.setText(String.valueOf(car.getPrice()));

            TextView tv5 = view.findViewById(R.id.tvEngineType);
            tv5.setText(car.getEngineType());
        }
        return view;
    }
}
