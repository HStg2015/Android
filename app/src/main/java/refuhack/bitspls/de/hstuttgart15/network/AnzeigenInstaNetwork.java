package refuhack.bitspls.de.hstuttgart15.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import refuhack.bitspls.de.hstuttgart15.models.Entry;
import refuhack.bitspls.de.hstuttgart15.models.EntryInsta;
import refuhack.bitspls.de.hstuttgart15.models.EntryInstaStorage;
import refuhack.bitspls.de.hstuttgart15.models.EntryStorage;

/**
 * Created by Lasse on 07.11.2015.
 */
public class AnzeigenInstaNetwork {
    private Context context;

    public AnzeigenInstaNetwork(Context c) {
        this.context = c;
    }

    public void getData(String URL) {
        JsonArrayRequest req = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int length = response.length();
                    EntryInsta tempEntry;
                    JSONObject curr;
                    DateTime dt;
                    ArrayList<EntryInsta> entries = new ArrayList<EntryInsta>();
                    for(int i = 0; i< length; i++){
                        curr = response.getJSONObject(i);
                        String idStr = curr.getString("id");
                        String startTimeStr = curr.getString("start_time");
                        String endTimeStr = curr.getString("end_time");
                        String campStr = curr.getString("camp");

                        int id = Integer.parseInt(idStr);
                        int camp = Integer.parseInt(campStr);
                        DateTime startDate = new DateTime(startTimeStr);
                        DateTime endDate = new DateTime(endTimeStr);
                        tempEntry = new EntryInsta(startDate, endDate,camp,id);
                        entries.add(tempEntry);
                    }
                    if(entries.size() != 0){
                        EntryInstaStorage.getInstance().setList(entries);
                    }

                }catch (JSONException e) {
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

    public void addEintrag(EntryInsta e, String URL) {
        JSONObject entryJson = new JSONObject();
        try {
            entryJson.put("id", e.getEntryId());
            entryJson.put("start_time", e.getTimeStart());
            //entryJson.put("city", e.getZipcode());
            //entryJson.put("telephone", e.getPhoneNr());
            //entryJson.put("email", e.getMail());


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
            VolleyHandler.getInstance(context).addToRequestQueue(req);
        } catch (JSONException Exc) {
            Log.e("AnzeigenNetwork", "ERROR WHILE SENDING ANZEIGE: " + Exc.toString());
        }

    }

 /*   public void uploadAllEntries(){
        ArrayList<Entry> serverEntries = getData("https://morning-waters-8909.herokuapp.com/simple_offer/");
        EntryStorage es = EntryStorage.getInstance();
        ArrayList<Entry> localEntries = es.getList();
        ArrayList<Entry> diff = new ArrayList<Entry>();
        if(serverEntries.size() != 0){
            Entry currServ;
            Entry currLoc;
            for(int i = 0; i< localEntries.size(); i++){
                currLoc = localEntries.get(i);
                if(serverEntries.size()>i){
                    currServ = serverEntries.get(i);
                }else{
                    currServ = null;
                }
                if(currServ == null){
                    diff.add(currLoc);
                }

            }
        }else{
            diff = localEntries;
        }
        for(int j = 0; j<diff.size();j++){
            addEintrag(diff.get(j), "https://morning-waters-8909.herokuapp.com/simple_offer/");
        }
    }*/
}
