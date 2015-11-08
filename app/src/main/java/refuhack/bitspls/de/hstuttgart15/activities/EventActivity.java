package refuhack.bitspls.de.hstuttgart15.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import refuhack.bitspls.de.hstuttgart15.R;

public class EventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Event);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_Event);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_Event);
        //setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String titleString = intent.getExtras().getString("RefugeeCamp");
        String descriptionString = intent.getExtras().getString("RefugeeCamp");
        Log.d("Test",""+descriptionString);

        //TextView title = (TextView)findViewById(R.id.nameItem);
        TextView description = (TextView)findViewById(R.id.descriptionItem_Event);
        //TextView title = (TextView)findViewById(R.id.nameItem);
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout_Event);

        description.setText(descriptionString);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callButtonPressed();
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                double faktor = appBarLayout.getTotalScrollRange()/255.;
                if (verticalOffset>=-3||verticalOffset==0) {
                    collapsingToolbarLayout.getBackground().setAlpha(255);
                } else {
                    collapsingToolbarLayout.getBackground().setAlpha((int)(verticalOffset/faktor));
                }
            }
        });


    }

    public void onClick(View v){
        if(v.getId()==R.id.print_btn_Event){
            Log.d("Test","Printing from InstaHelp...");

        }
    }

    private void callButtonPressed() {
        //Todo
    }

    @Override
    public void onBackPressed() {
        //Intent intent = new Intent(ItemActivity.this, MainActivity.class);
        //startActivity(intent);
        finish();
    }
}
