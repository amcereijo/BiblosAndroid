package com.biblos.amcereijo.biblos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends ActionBarActivity {

    List<String> ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAll();
        prepareView();
    }

    private void loadAll() {
        ordersList = new DbHelper(this).findAll();
    }

    private void prepareView(){
        if(ordersList != null && ordersList.size() > 0) {
            //show orders
            prepareTitle();
            showOrders();
        }
    }

    private void prepareTitle() {
        TextView titleMain = (TextView)findViewById(R.id.main_layout_title);
        titleMain.setText(R.string.main_text_orders);
        titleMain.setTextAppearance(this,R.style.TextAppearance_AppCompat_Title);
    }

    private void showOrders() {

        ListView ordersView = (ListView)findViewById(R.id.order_list);
        Log.e("MainActivity", "Size: "+ ordersList.size());

        for(int i=0;i<ordersList.size();i++){
            System.out.println("element:[" + i + "] : " + ordersList.get(i));
        }

        List<String> finalS = new ArrayList<>();
        finalS.addAll(ordersList);
        finalS.addAll(ordersList);
        finalS.addAll(ordersList);
        finalS.addAll(ordersList);


        ordersView.setAdapter(new OrderAdapter(this, R.layout.order_element, finalS));
    }

    public void showOrder(View v) {
        String id = (String)v.getTag();
        for(String json : ordersList)
            try {
                JSONObject jsonObject = new JSONObject(json);
                if (id.equals(jsonObject.getString(("id")))) {
                    Intent intent = new Intent(this, ShowOrderActivity.class);
                    intent.setData(Uri.parse("http://localhost?order=" + Base64.encodeToString(json.getBytes(), Base64.DEFAULT)));
                    startActivity(intent);
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

}
