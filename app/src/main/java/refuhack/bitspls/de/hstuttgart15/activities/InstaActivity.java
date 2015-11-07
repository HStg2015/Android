package refuhack.bitspls.de.hstuttgart15.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.models.Entry;
import refuhack.bitspls.de.hstuttgart15.models.EntryAdapter;
import refuhack.bitspls.de.hstuttgart15.views.EintragHinzufuegenFragment;

public class InstaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Entry> entryList;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Insta);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Anzeige hinzufügen");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Insta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                startActivity(intent);
                //eintragfrag.show(fm, "EintragFrag");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_Insta);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rv = (RecyclerView)findViewById(R.id.rv_insta);
        rv.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(glm);

        initializeData();
        initializeAdapter();
        navigationView.setCheckedItem(R.id.nav_InstaHelp);
    }

    private void initializeData(){
        entryList = new ArrayList<>();
        entryList.add(new Entry("Felix B", "Android Dev", R.drawable.max));
        entryList.add(new Entry("Max Mustermann", "User", R.drawable.max));

    }

    private void initializeAdapter(){
        EntryAdapter adapter = new EntryAdapter(entryList);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Kleinanzeigen) {
            Intent intent = new Intent(InstaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_InstaHelp) {
            Intent intent = new Intent(InstaActivity.this, InstaActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_Inventar) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_Insta);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
