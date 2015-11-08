package refuhack.bitspls.de.hstuttgart15.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import refuhack.bitspls.de.hstuttgart15.models.Entry;
import refuhack.bitspls.de.hstuttgart15.models.EntryAdapter;
import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.models.EntryStorage;
import refuhack.bitspls.de.hstuttgart15.network.AnzeigenNetwork;
import refuhack.bitspls.de.hstuttgart15.views.EintragHinzufuegenFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Entry> entryList;
    private RecyclerView rv;


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), EintragHinzufuegenFragment.class);
                //startActivity(intent);
                load();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(glm);

        initializeData();
        initializeAdapter();

    }

    private void initializeData() {

        AnzeigenNetwork anzeigenNetwork = new AnzeigenNetwork(this);
        anzeigenNetwork.getData("https://morning-waters-8909.herokuapp.com/simple_offer/");
    }

    private void initializeAdapter() {
        EntryAdapter adapter = new EntryAdapter(this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Kleinanzeigen) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_InstaHelp) {
            Intent intent = new Intent(MainActivity.this, InstaActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    private void save() {
        try {
            SharedPreferences sp = getSharedPreferences("HALLO", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

                /*File dir = new File(context.getApplicationContext().getFilesDir(),"data");
                dir.mkdirs();
                File meta = new File(dir,"data.json");
                meta.createNewFile();
                JSONArray jsArray = new JSONArray();*/
/*            editor.putString("Hey", "NADU");
            editor.putInt("length", EntryStorage.getInstance().getList().size());
            for (int i = 0; i < EntryStorage.getInstance().getList().size(); i++)
                editor.putString("JSON" + i, EntryStorage.getInstance().getList().get(i).getJson().toString());
            editor.commit();
            System.out.println(sp.getString("JSON1", "HAT NICHT GEKLAPPT"));
            System.out.println(sp.getString("Hey", "HAT NICHT GEKLAPPT"));
               /* FileOutputStream outputStream;
                outputStream = context.openFileOutput("data.json", Context.MODE_PRIVATE);
                outputStream.write(str.getBytes());
                outputStream.close();*/
/*        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    private void load() {

        Realm realm = Realm.getInstance(this);
        RealmResults<Entry> query = realm.where(Entry.class)
                .findAll();
        for (Entry p : query) {
            EntryStorage.getInstance().addEntry(p);
        }
//        SharedPreferences sp = getSharedPreferences("HALLO", MODE_PRIVATE);
//        int length = sp.getInt("length", 0);
//        ArrayList<Entry> temp;
//        String str;
//        JSONObject curr;
//        JSONArray arr = new JSONArray();
//        try{
//        for(int i =0; i< length; i++) {
//            str = sp.getString("JSON" + i, null);
//            curr = new JSONObject(str);
//            if(curr != null) {
//                arr.put(curr);
//            }
//        }
//        try {
//            File dir = new File(getFilesDir(),"data");
//            dir.mkdirs();
//            File meta = new File(dir,"data.json");
//            meta.createNewFile();
//            JSONArray jsArray = new JSONArray();
//            String str = "asdasdasd";
//            FileOutputStream outputStream;
//            outputStream = openFileOutput("data.json", Context.MODE_PRIVATE);
//            outputStream.write(str.getBytes());
//            outputStream.close();
//
//            String read;
//            StringBuffer buffer = new StringBuffer();
//            File dir2 = new File(getFilesDir(), "data");
//            File meta2 = new File(dir2, "data.json");
//            FileInputStream fis = new FileInputStream(meta2);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//            if (fis != null) {
//                int i = fis.read();
//                while ((read = reader.readLine()) != null) {
//                    buffer.append(read + "\n");
//                }
//            }
//            fis.close();
//            String str2 = buffer.toString();
//            Log.v("JSONSTR", str2);
//            DateTime dt;
//            Entry tempEntry;
//            ArrayList<Entry> entries = new ArrayList<Entry>();
//            for (int i = 0; i < arr.length(); i++) {
//                curr = arr.getJSONObject(i);
//                if (curr.has("create_time") && curr.has("image")) {
//                    dt = new DateTime(curr.getString("create_time"));
//                    tempEntry = new Entry(curr.getString("title"), curr.getString("description"),
//                            curr.getString("telephone"), curr.getString("city"), curr.getString("email"),
//                            Uri.parse(curr.getString("image")), dt);
//                    Picasso.with(this).load(Uri.parse(curr.getString("image"))).fetch();
//                    entries.add(tempEntry);
//                } else if (curr.has("image") && !curr.has("create_time")) {
//                    tempEntry = new Entry(curr.getString("title"), curr.getString("description"),
//                            curr.getString("telephone"), curr.getString("city"), curr.getString("email"), Uri.parse(curr.getString("image")));
//                    Picasso.with(this).load(Uri.parse(curr.getString("image"))).fetch();
//                    entries.add(tempEntry);
//                } else {
//                    tempEntry = new Entry(curr.getString("title"), curr.getString("description"),
//                            curr.getString("telephone"), curr.getString("city"), curr.getString("email"));
//                    entries.add(tempEntry);
//                }
//            }
//            if (entries.size() != 0) {
//                EntryStorage.getInstance().setList(entries);
//            }
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }

    }
}
