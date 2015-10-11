package com.biblos.amcereijo.biblos;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by amcereijo on 16/08/15.
 */
public class OrderAdapter extends BaseAdapter {
    List<String> orders;
    int layout;
    Context context;


    public OrderAdapter(Context context, int textViewResourceId, List<String> objects) {
        //super(context, textViewResourceId, objects);

        this.context = context;
        this.orders = objects;
        this.layout = textViewResourceId;
    }

    @Override
    public int getCount() {
        return this.orders.size();
    }

    @Override
    public String getItem(int position) {
        return this.orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("MainActivity", "GetView: " + position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(layout, parent, false);

        String json = orders.get(position);
        try{
            JSONObject jsonObject = new JSONObject(json);
            TextView idTextView = (TextView)row.findViewById(R.id.order_id);
            idTextView.setText(jsonObject.getString("id"));
            TextView clientTextView = (TextView)row.findViewById(R.id.order_client);
            clientTextView.setText(jsonObject.getString("clientName"));
            TextView dateTextView = (TextView)row.findViewById(R.id.order_date);
            dateTextView.setText(jsonObject.getString("createdAt"));
            Button showButton = (Button)row.findViewById(R.id.order_show);
            showButton.setTag(jsonObject.getString("id"));
            if(position%2 == 0) {
                row.setBackgroundColor(Color.parseColor("#CED8F6"));
            } else {
                row.setBackgroundColor(Color.parseColor("#F5D0A9"));
            }
        }catch(Exception e) {
            Log.e("MainActivity", "Error mostrando el pedido: " + json, e);
        }
        return row;
    }

}
