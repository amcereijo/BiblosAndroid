package com.biblos.amcereijo.biblos;

import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowOrder {
    public static void showOrder(Activity view, String json) {
        String htmlText = createHtmlFormJsonData(json);
        view.setContentView(R.layout.activity_show_order);
        TextView htmlTextView = (TextView)view.findViewById(R.id.textview);
        htmlTextView.setText(Html.fromHtml(htmlText));
    }

    private static String createHtmlFormJsonData(String json) {
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
}
