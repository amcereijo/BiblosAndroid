package com.biblos.amcereijo.biblos;

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
        String htmlText = createHtmlFormJsonData(json);

        setContentView(R.layout.activity_show_order);
        TextView htmlTextView = (TextView)findViewById(R.id.textview);
        htmlTextView.setText(Html.fromHtml(htmlText));
    }

    private String createHtmlFormJsonData(String json) {
        String htmlText = "Upps!! No hemos podido procesar el html. Aquí tienes el json: " + json;
        try {
            JSONObject jsonObject = new JSONObject(json);

            StringBuilder strb = new StringBuilder()
                    .append("<h1><font color=\"blue\">BIBLOS Bar</font></h1>")
                    .append("<big><strong>Cliente: </strong></big>" + jsonObject.get("clientName"))
                    .append("<br/><big><strong>Hora: </strong></big>" + jsonObject.get("createdAt"))
                    .append("<br/><big><strong>Pedido número " +jsonObject.get("id") + ":</strong></big><br/>");

            JSONArray products = jsonObject.getJSONArray("products");
            double totalPrice = 0;
            for(int i=0;i<products.length();i++) {
                JSONObject product = products.getJSONObject(i).getJSONObject("product");
                strb.append("&nbsp;&nbsp;:-) => " + product.get("name") +" x " + products.getJSONObject(i).get("amount") +
                        " (" + product.get("price") + "€ unidad)<br/>" );
                totalPrice += product.getDouble("price");
            }
            strb.append("<br><big><strong>Total:</strong></big> " + totalPrice + "€");
            strb.append("<br><big><strong>Comentarios:</strong></big><br/>&nbsp;&nbsp;" + jsonObject.get("comments"));

            htmlText = strb.toString();

        } catch (JSONException e) {
            Log.e("ShowOrderActivity", "Error creando el html a mostrar", e);
        }
        return htmlText;
    }

    private String getOrderJsonAsString() {
        String order = getIntent().getData().getQueryParameter("order");
        return new String(Base64.decode(order, Base64.DEFAULT));
    }
}
