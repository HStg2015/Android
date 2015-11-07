package refuhack.bitspls.de.hstuttgart15.models;

import android.net.Uri;

/**
 * Created by Lasse on 06.11.2015.
 */
public class Anzeige {
    private String titel, beschreibung, telnr, stadtteil, mail;
    private Uri image;
    private int id;
    public Anzeige(String titel, String beschreibung, String telnr, String stadtteil, String mail, Uri image) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.telnr = telnr;
        this.stadtteil = stadtteil;
        this.mail = mail;
        this.image = image;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getTelnr() {
        return telnr;
    }

    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }

    public String getStadtteil() {
        return stadtteil;
    }

    public void setStadtteil(String stadtteil) {
        this.stadtteil = stadtteil;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
