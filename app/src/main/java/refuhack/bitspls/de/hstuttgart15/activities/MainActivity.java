package refuhack.bitspls.de.hstuttgart15.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import refuhack.bitspls.de.hstuttgart15.models.Entry;
import refuhack.bitspls.de.hstuttgart15.models.EntryAdapter;
import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.network.AnzeigenNetwork;
import refuhack.bitspls.de.hstuttgart15.views.EintragHinzufuegenFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Entry> entryList;
    private RecyclerView rv;

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
                //eintragfrag.show(fm, "EintragFrag");

                try {
                    DateTime dt = new DateTime("2015-11-07T15:01:07.078805Z");


                }catch(Exception e){
                    e.printStackTrace();
                }
                //AnzeigenNetwork an = new AnzeigenNetwork(getApplicationContext());
                //an.getData("https://morning-waters-8909.herokuapp.com/simple_offer/");

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(glm);

        initializeData();
        initializeAdapter();

    }

    private void initializeData(){
        entryList = new ArrayList<>();
        Uri path =
        Uri.parse("android.resource://refuhack.de.bitspls.hstuttgart15" + R.drawable.max);
        entryList.add(new Entry("Felix B", "Android Dev", "1234", "12345", "example@example.com", path));
     /*   entryList.add(new Entry("Felix B", "Android Dev", R.drawable.max));
        entryList.add(new Entry("Max Mustermann", "User", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max));
        entryList.add(new Entry("Max", "Guy", R.drawable.max)); */
    }

    private void initializeAdapter(){
        EntryAdapter adapter = new EntryAdapter(entryList);
        rv.setAdapter(adapter);
    }

    private void actionButtonPressed() {
        Intent intent = new Intent(MainActivity.this, ItemActivty.class);
        startActivity(intent);
        finish();
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
}
