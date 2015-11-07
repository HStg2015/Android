package refuhack.bitspls.de.hstuttgart15.views;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.models.Anzeige;
import refuhack.bitspls.de.hstuttgart15.models.Entry;

/**
 * Created by Lasse on 06.11.2015.
 */
public class EintragHinzufuegenFragment extends  AppCompatActivity {
    private EditText mEditText;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int RESULT_LOAD_IMG = 2;
    String mCurrentPhotoPath;
    private ImageButton foto, gallerie;
    private EditText titel, beschreibung, telefon, mail;
    private ImageView iVfoto;
    private Uri uri;
    private Toolbar toolbar;

    public EintragHinzufuegenFragment(){
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eintrag_hinzufuegen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarEintrag);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Anzeige hinzufügen");
        myToolbar.setBackgroundColor(Color.parseColor("#C03D34"));
        beschreibung = (EditText) findViewById(R.id.etBeschreibung);
        telefon = (EditText) findViewById(R.id.ettelefon);
        mail = (EditText) findViewById(R.id.etmail);
        titel = (EditText) findViewById(R.id.etTitel);
        foto = (ImageButton) findViewById(R.id.bFoto);
        gallerie = (ImageButton) findViewById(R.id.bGallerie);
        iVfoto = (ImageView) findViewById(R.id.iVkamera);
        gallerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();


            }
        });
        //setStyle(R.style.FullDialog, R.style.FullDialog);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.eintrag_hinzufuegen, menu);
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
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eintrag_hinzufuegen, container);
        //toolbar.setTitle("Anzeige hinzufügen");
        beschreibung = (EditText) view.findViewById(R.id.etBeschreibung);
        telefon = (EditText) view.findViewById(R.id.ettelefon);
        mail = (EditText) view.findViewById(R.id.etmail);
        titel = (EditText) view.findViewById(R.id.etTitel);
        foto = (ImageButton) view.findViewById(R.id.bFoto);
        gallerie = (ImageButton) view.findViewById(R.id.bGallerie);
        iVfoto = (ImageView) view.findViewById(R.id.iVkamera);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();


            }
        });
        return view;
    }

    private void save(){
        String tBeschreibung, tTitel, tTelefon, tMail;
        tBeschreibung = beschreibung.getText().toString();
        tTitel = titel.getText().toString();
        tTelefon = telefon.getText().toString();
        tMail = mail.getText().toString();
        if(tBeschreibung != null){
            if(tTitel != null){
                if(tMail != null){
                    if(tTelefon != null){
                        if(uri != null){
                            Entry tempAnzeige = new Entry(tTitel,tBeschreibung,tTelefon, "stadteil", tMail, uri);
                        }else{
                            //Uri leer
                        }
                    }else{
                        //Telefon leer
                    }
                }else{
                    //Mail leer
                }
            }else{
              //Titel leer
            }
        }else{
            //Beschreibung leer
        }


    }
    private void pickFromGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri tempUri =  Uri.fromFile(photoFile);
                uri = tempUri;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        tempUri);

                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
            }else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }else if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            uri = selectedImage;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
