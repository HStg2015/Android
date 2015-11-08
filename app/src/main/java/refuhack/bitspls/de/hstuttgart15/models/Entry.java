package refuhack.bitspls.de.hstuttgart15.models;

import android.net.Uri;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gin on 07.11.15.
 */
public class Entry {
    protected String name, description, phoneNr, zipcode, mail;
    protected Uri imageUri;
    protected int entryId;
    protected DateTime date;
    protected int categoryId;

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getPhoneNr() { return phoneNr; }

    public String getZipcode() { return zipcode; }

    public String getMail() { return mail; }

    public int getEntryId() { return entryId; }

    public Uri getImageUri() { return imageUri; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setPhoneNr(String phoneNr) { this.phoneNr = phoneNr; }

    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public void setMail(String mail) { this.mail = mail; }

    public void setEntryId(int entryId) { this.entryId = entryId; }

    public void setImageUri(Uri imageUri) { this.imageUri = imageUri; }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

