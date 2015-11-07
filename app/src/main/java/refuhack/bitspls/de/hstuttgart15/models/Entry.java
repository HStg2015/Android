package refuhack.bitspls.de.hstuttgart15.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gin on 07.11.15.
 */
public class Entry {
    protected String name, description, phoneNr, zipcode, mail;
    protected Uri imageUri;
    protected int entryId;

    public Entry(String name, String description, String phoneNr, String zipcode, String mail,
                 Uri imageUri) {
        this.name = name;
        this.description = description;
        this.phoneNr = phoneNr;
        this.zipcode = zipcode;
        this.mail = mail;
        this.imageUri = imageUri;
    }

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
}

