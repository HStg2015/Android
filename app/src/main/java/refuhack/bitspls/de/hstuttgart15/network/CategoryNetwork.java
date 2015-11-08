package refuhack.bitspls.de.hstuttgart15.network;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import refuhack.bitspls.de.hstuttgart15.models.Category;
import refuhack.bitspls.de.hstuttgart15.models.CategoryStorage;

/**
 * Created by tniederhausen on 08.11.2015.
 */
public class CategoryNetwork {
    private Context context;

    public CategoryNetwork(Context c) {
        context = c;
    }

    public void getData(String URL) {
        Response.Listener<JSONArray> successHandler = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    onResult(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorHandler = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null)
                    VolleyLog.e("Error: %s",  new String(response.data));
                VolleyLog.e("Error: %s", error.getMessage());
            }
        };

        JsonArrayRequest req = new JsonArrayRequest(URL, successHandler, errorHandler);
        VolleyHandler.getInstance(context).addToRequestQueue(req);
    }

    private void onResult(JSONArray response) throws JSONException {
        ArrayList<Category> categories = new ArrayList<>();
        int length = response.length();
        for (int i = 0; i < length; i++) {
            JSONObject curr = response.getJSONObject(i);
            Category c = new Category();

            c.setId(curr.getInt("id"));
            c.setTitle(curr.getString("title"));

            categories.add(c);
        }

        if (!categories.isEmpty())
            CategoryStorage.getGlobalInstance().importData(categories);
    }
}
