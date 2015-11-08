package refuhack.bitspls.de.hstuttgart15.models;

import android.net.Uri;
import android.util.Log;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gin on 07.11.15.
 */
public class Entry extends RealmObject {
    private String name, description, phoneNr, zipcode, mail, textUri, textDate;
    private int entryId;

    public String getTextUri() {
        return textUri;
    }

    public void setTextUri(String textUri) {
        this.textUri = textUri;
    }

    @Ignore
    private Uri imageUri;

    @Ignore
    private DateTime date;




    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getPhoneNr() { return phoneNr; }

    public String getZipcode() { return zipcode; }

    public String getMail() { return mail; }

    public int getEntryId() { return entryId; }

    public Uri getImageUri() {

        if(textUri != null){
            return Uri.parse(textUri);
        }
        return null;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setPhoneNr(String phoneNr) { this.phoneNr = phoneNr; }

    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public void setMail(String mail) { this.mail = mail; }

    public void setEntryId(int entryId) { this.entryId = entryId; }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
        setTextUri(imageUri.toString());
    }

    public DateTime getDate() {
        if(textDate != null)
            return DateTime.parse(textDate);

        return null;
    }

    public void setDate(DateTime date) {
        this.date = date;
        setTextDate(date.toString());
    }
}

