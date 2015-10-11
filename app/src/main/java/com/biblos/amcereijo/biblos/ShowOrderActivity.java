package com.biblos.amcereijo.biblos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowOrderActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.biblos_orders_title);

        String json = getOrderJsonAsString();
        saveOrder(json);

        ShowOrder.showOrder(this, json);
    }

    private void saveOrder(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.save(jsonObject.getString("id"), json);
        } catch (Exception e){
            Log.e("ShowOrderActivity", "No se ha podido almacenar el json:" +json, e);
        }
    }


    private String getOrderJsonAsString() {
        String order = getIntent().getData().getQueryParameter("order");
        return new String(Base64.decode(order, Base64.DEFAULT));
    }
}
