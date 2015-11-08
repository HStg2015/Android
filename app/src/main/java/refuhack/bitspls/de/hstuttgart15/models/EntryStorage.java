package refuhack.bitspls.de.hstuttgart15.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by Lasse on 07.11.2015.
 */
public class EntryStorage {
    private ArrayList<Entry> entries;
    private static EntryStorage sInstance;
    public boolean saveRunning, loadRunning;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static synchronized EntryStorage getInstance(){
        if(sInstance == null){
            sInstance = new EntryStorage();
        }
        return sInstance;
    }
    public boolean load(Context context){
        Realm realm = Realm.getInstance(context.getApplicationContext());
        FileInputStream fis;
        ArrayList<Entry> returnlist = null;

        try {
            String read;
            StringBuffer buffer = new StringBuffer();
            File dir = new File(context.getApplicationContext().getFilesDir(),"data");
            File meta = new File(dir,"data.json");
            fis = new FileInputStream(meta);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            if (fis!=null) {
                while ((read = reader.readLine()) != null) {
                    buffer.append(read + "\n" );
                }
            }
            fis.close();
            String str = buffer.toString();
            Log.v("JSONSTR", str);
            if(str != null){
                Log.v("FILE", str);
                JSONArray jsarr = new JSONArray(str);
                Entry tempEntry;
                JSONObject curr;
                DateTime dt;
                ArrayList<Entry> entries = new ArrayList<Entry>();
                realm.beginTransaction();
                for(int i = 0; i< jsarr.length(); i++){
                    curr = jsarr.getJSONObject(i);
                    if (curr.has("create_time") && curr.has("image")) {
                        dt = new DateTime(curr.getString("create_time"));
                        tempEntry = realm.createObject(Entry.class);
                        tempEntry.setDescription(curr.getString("description"));
                        tempEntry.setName(curr.getString("title"));
                        tempEntry.setPhoneNr(curr.getString("telephone"));
                        tempEntry.setZipcode(curr.getString("city"));
                        tempEntry.setMail(curr.getString("mail"));
                        tempEntry.setDate(dt);
                        tempEntry.setImageUri(Uri.parse(curr.getString("image")));
                        Picasso.with(context).load(Uri.parse(curr.getString("image"))).fetch();
                        entries.add(tempEntry);
                    } else if (curr.has("image") && !curr.has("create_time")) {
                        tempEntry = realm.createObject(Entry.class);
                        tempEntry.setDescription(curr.getString("description"));
                        tempEntry.setName(curr.getString("title"));
                        tempEntry.setPhoneNr(curr.getString("telephone"));
                        tempEntry.setZipcode(curr.getString("city"));
                        tempEntry.setMail(curr.getString("mail"));
                        tempEntry.setImageUri(Uri.parse(curr.getString("image")));

                        Picasso.with(context).load(Uri.parse(curr.getString("image"))).fetch();
                        entries.add(tempEntry);
                    } else {
                        tempEntry = realm.createObject(Entry.class);
                        tempEntry.setDescription(curr.getString("description"));
                        tempEntry.setName(curr.getString("title"));
                        tempEntry.setPhoneNr(curr.getString("telephone"));
                        tempEntry.setZipcode(curr.getString("city"));
                        tempEntry.setMail(curr.getString("mail"));
                        entries.add(tempEntry);
                    }
                }
                realm.commitTransaction();
                if(entries.size() != 0){
                    this.entries = entries;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(returnlist != null && returnlist.size() != 0){
            entries = returnlist;
            return true;
        }
        return false;
    }
 /*   public void save(Context context){
        saveRunning = true;
        if(entries.size() != 0){

            try {
                SharedPreferences sp = context.getApplicationContext().getSharedPreferences("HALLO", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();


                editor.putString("Hey", "NADU");
                for(int i = 0; i< entries.size(); i++)
                    editor.putString("JSON"+i, entries.get(i).getJson().toString());

                System.out.println(sp.getString("JSON1", "HAT NICHT GEKLAPPT"));
                System.out.println(sp.getString("Hey", "HAT NICHT GEKLAPPT"));

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        saveRunning = false;
    }*/
    public EntryStorage(){
        entries = new ArrayList<>();
    }

    public void appendList(ArrayList<Entry> en){
        entries.addAll(en);
    }

    public void addEntry(Entry e){
        entries.add(e);
    }

    public ArrayList<Entry> getList(){
        return entries;
    }

    public void setList(ArrayList<Entry> entries){
       this.entries = entries;
    }
    public Entry getEntry(int id){
            Entry curr;
            for (int i = 0; i < entries.size(); i++) {
                curr = entries.get(i);
                if (curr.getEntryId() == id) {
                    return curr;
                }
            }
        return null;

    }


}
