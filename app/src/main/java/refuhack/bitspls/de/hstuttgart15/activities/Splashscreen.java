package refuhack.bitspls.de.hstuttgart15.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.network.AnzeigenNetwork;

/**
 * Created by Lasse on 08.11.2015.
 */
public class Splashscreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        AnzeigenNetwork anzeigenNetwork = new AnzeigenNetwork(this);
        anzeigenNetwork.getData("https://morning-waters-8909.herokuapp.com/simple_offer/");
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }

                    , SPLASH_TIME_OUT);
        }

    }
