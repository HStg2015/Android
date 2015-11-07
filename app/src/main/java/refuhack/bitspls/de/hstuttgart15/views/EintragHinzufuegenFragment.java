package refuhack.bitspls.de.hstuttgart15.views;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.models.Anzeige;

/**
 * Created by Lasse on 06.11.2015.
 */
public class EintragHinzufuegenFragment extends DialogFragment {
    private EditText mEditText;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int RESULT_LOAD_IMG = 2;
    String mCurrentPhotoPath;
    private Button foto;
    private EditText titel, beschreibung, telefon, mail;
    private ImageView iVfoto;
    private Uri uri;

    public EintragHinzufuegenFragment(){
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(R.style.AppTheme, R.style.FullDIalog);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eintrag_hinzufuegen, container);
        beschreibung = (EditText) view.findViewById(R.id.etBeschreibung);
        telefon = (EditText) view.findViewById(R.id.ettelefon);
        mail = (EditText) view.findViewById(R.id.etmail);
        titel = (EditText) view.findViewById(R.id.etTitel);
        foto = (Button) view.findViewById(R.id.bFoto);
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
                            Anzeige tempAnzeige = new Anzeige(tTitel,tBeschreibung,tTelefon, "stadteil", tMail, uri);
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
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
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
            if (resultCode == getActivity().RESULT_OK) {
            }else if (resultCode == getActivity().RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }else if (requestCode == RESULT_LOAD_IMG && resultCode == getActivity().RESULT_OK && null != data) {
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
