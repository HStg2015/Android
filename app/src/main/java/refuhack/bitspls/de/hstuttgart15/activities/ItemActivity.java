package refuhack.bitspls.de.hstuttgart15.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.models.Entry;
import refuhack.bitspls.de.hstuttgart15.network.VolleyHandler;

public class ItemActivity extends AppCompatActivity {
    private String titleString, descriptionString, phoneNumberString, zipCodeString, mailString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        titleString = intent.getExtras().getString("title");
        descriptionString = intent.getExtras().getString("description");
        Uri pictureUri = (Uri)intent.getExtras().get("Picture");
        phoneNumberString = intent.getExtras().getString("Phonenumber");
        zipCodeString = intent.getExtras().getString("Zipcode");
        mailString = intent.getExtras().getString("Mail");

        //TextView title = (TextView)findViewById(R.id.nameItem);
        TextView description = (TextView)findViewById(R.id.descriptionItem);
        TextView phone = (TextView)findViewById(R.id.phoneNumber);
        TextView zipcode = (TextView)findViewById(R.id.zipcode);
        TextView mail = (TextView)findViewById(R.id.mail);

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                ctl.setBackground(bitmapDrawable);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                //empty
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                //empty
            }
        };

        Picasso.with(this).load(pictureUri).into(target);

        setTitle(titleString);

        description.setText(descriptionString);
        phone.setText(phoneNumberString);
        zipcode.setText(zipCodeString);
        mail.setText(mailString);
        //title.setText(titleString);

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
        String pNumber = getIntent().getExtras().getString("Phonenumber");
        System.out.println("Calling Number: "+pNumber);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+pNumber));

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //Intent intent = new Intent(ItemActivity.this, MainActivity.class);
        //startActivity(intent);
        finish();
    }

    public void onClick(View v){
        if(v.getId()==R.id.print_btn){
            doWebViewPrint();
        }
    }
    private WebView mWebView;

    private void doWebViewPrint() {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("MainActivity", "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        // Generate an HTML document on the fly:
        String html = "<html><body><h3>"+titleString+"</h3> <br/> <h4>" + descriptionString+
                "</h4><br/><br/><p>Stadtteil: "+zipCodeString+"</p><br/><p>Telefonnummer: "
                +phoneNumberString+"</p><br/><p>Mail-Adresse: "+ mailString+" <br/><br/><br/></body></html>";
        webView.loadDataWithBaseURL(null, html, "text/HTML", "UTF-8", null);

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
        mWebView = webView;
    }

    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        // Create a print job with name and adapter instance
        String jobName = getString(R.string.app_name) + " Document";
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());
    }
}
