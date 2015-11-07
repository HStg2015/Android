package refuhack.bitspls.de.hstuttgart15.models;

import android.net.Uri;

import org.joda.time.DateTime;

/**
 * Created by gin on 07.11.15.
 */
public class EntryInsta {
    protected String timeStart, timeEnd, refugeeCamp;
    protected int entryId;

    public EntryInsta(String name, String description, String phoneNr, String zipcode, String mail) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.refugeeCamp = refugeeCamp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getRefugeeCamp() {
        return refugeeCamp;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }


    public void setRefugeeCamp(String refugeeCamp) {
        this.refugeeCamp = refugeeCamp;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }
}


