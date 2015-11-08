package refuhack.bitspls.de.hstuttgart15.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import refuhack.bitspls.de.hstuttgart15.models.Anzeige;
import refuhack.bitspls.de.hstuttgart15.models.Entry;
import refuhack.bitspls.de.hstuttgart15.models.EntryStorage;

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
                    int length = response.length();
                    Entry tempEntry;
                    JSONObject curr;
                    DateTime dt;
                    ArrayList<Entry> entries = new ArrayList<Entry>();
                    for(int i = 0; i< length; i++){
                        curr = response.getJSONObject(i);

                        Entry e = new Entry();

                        e.setCategoryId(curr.getInt("category"));
                        e.setName(curr.getString("title"));
                        e.setDescription(curr.getString("description"));

                        // TODO: Why is create_time optional?
                        if (curr.has("create_time"))
                            e.setDate(new DateTime(curr.getString("create_time")));

                        if (curr.has("image")) {
                            e.setImageUri(Uri.parse(curr.getString("image")));
                            Picasso.with(context).load(e.getImageUri()).fetch();
                        }

                        e.setZipcode(curr.getString("city"));
                        e.setMail(curr.getString("email"));
                        e.setPhoneNr(curr.getString("telephone"));

                        entries.add(e);
                    }

                    if (!entries.isEmpty())
                        EntryStorage.getInstance().setList(entries);

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null)
                    VolleyLog.e("Error: %s",  new String(response.data));
                VolleyLog.e("Error: %s", error.getMessage());
            }
        });

        VolleyHandler.getInstance(context).addToRequestQueue(req);
    }

    public void addEintrag(Entry e, String URL) {
        JSONObject entryJson = new JSONObject();
        try {
            /*entryJson.put("category", e.getCategoryId());
            entryJson.put("title", e.getName());
            entryJson.put("description", e.getDescription());
            entryJson.put("city", e.getZipcode().toString());
            entryJson.put("image", "");
            entryJson.put("telephone", e.getPhoneNr());
            entryJson.put("email", e.getMail());
            entryJson.put("category", e.getCategoryId());*/
            //entryJson.put("create_time", e.getDate().toString());

            File file = new File(e.getImageUri().getPath());

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("image", file.getName(),
                            RequestBody.create(MediaType.parse("image/png"), file))
                    .addFormDataPart("category", String.valueOf(e.getCategoryId()))
                    .addFormDataPart("title", e.getName())
                    .addFormDataPart("description", e.getDescription())
                    .addFormDataPart("city", e.getZipcode())
                    .addFormDataPart("telephone", e.getPhoneNr())
                    .addFormDataPart("email", e.getMail())
                    .build();

            Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();
        OkHttpClient client = new OkHttpClient();
        com.squareup.okhttp.Response response = client.newCall(request).execute();

            /*JsonObjectRequest req = new JsonObjectRequest(URL, entryJson,
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
                    NetworkResponse response = error.networkResponse;
                    if (response != null && response.data != null)
                        VolleyLog.e("Error: %s",  new String(response.data));
                    VolleyLog.e("Error: %s", error.getMessage());
                }
            });
            VolleyHandler.getInstance(context).addToRequestQueue(req);*/
        } catch (IOException Exc) {
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
