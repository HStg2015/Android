package refuhack.bitspls.de.hstuttgart15.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import refuhack.bitspls.de.hstuttgart15.models.Anzeige;
import refuhack.bitspls.de.hstuttgart15.models.Entry;

/**
 * Created by Lasse on 07.11.2015.
 */
public class AnzeigenNetwork {
    private Context context;

    public AnzeigenNetwork(Context c) {
        this.context = c;
    }

    public void getData(String URL) {
        JsonArrayRequest req = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.v("VOLLEYJSON", response.get(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });


        VolleyHandler.getInstance(context).addToRequestQueue(req);
    }

    public void addEintrag(Anzeige a, String URL) {
        JSONObject entryJson = new JSONObject();
        try {
            entryJson.put("title", a.getTitel());
            entryJson.put("description", a.getBeschreibung());
            entryJson.put("city", a.getStadtteil());
            entryJson.put("telephone", a.getTelnr());
            entryJson.put("email", a.getMail());


            JsonObjectRequest req = new JsonObjectRequest(URL, entryJson,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                VolleyLog.v("Response:%n %s", response.toString(4));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            });
        } catch (JSONException e) {
            Log.e("AnzeigenNetwork", "ERROR WHILE SENDING ANZEIGE: " + e.toString());
        }
    }
}
