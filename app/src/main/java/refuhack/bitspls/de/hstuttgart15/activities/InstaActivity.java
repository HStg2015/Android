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

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.models.EntryInstaAdapter;
import refuhack.bitspls.de.hstuttgart15.network.AnzeigenInstaNetwork;
import refuhack.bitspls.de.hstuttgart15.views.EintragHinzufuegenFragment;

public class InstaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvinsta;

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
                Intent intent = new Intent(view.getContext(), EintragHinzufuegenFragment.class);
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

        rvinsta = (RecyclerView)findViewById(R.id.rv_insta);
        rvinsta.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rvinsta.setLayoutManager(glm);

        initializeData();
        initializeAdapter();
        navigationView.setCheckedItem(R.id.nav_InstaHelp);
    }

    private void initializeData() {
        AnzeigenInstaNetwork anzeigenInstaNetwork = new AnzeigenInstaNetwork(this);
        anzeigenInstaNetwork.getData("https://morning-waters-8909.herokuapp.com/help_time_search/");
    }


    private void initializeAdapter(){
        EntryInstaAdapter adapter = new EntryInstaAdapter(this);
        rvinsta.setAdapter(adapter);
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
