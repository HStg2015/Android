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

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String titleString = intent.getExtras().getString("title");
        String descriptionString = intent.getExtras().getString("description");
        String pictureUri = intent.getExtras().getString("Picture");

        TextView title = (TextView)findViewById(R.id.nameItem);
        TextView description = (TextView)findViewById(R.id.descriptionItem);
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        description.setText(descriptionString);
        title.setText(titleString);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
