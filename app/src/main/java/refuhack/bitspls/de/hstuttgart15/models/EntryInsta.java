package refuhack.bitspls.de.hstuttgart15.models;

import android.net.Uri;

import org.joda.time.DateTime;

/**
 * Created by gin on 07.11.15.
 */
public class EntryInsta {
    private DateTime startDate, endDate;
    private int campID, id;

    public EntryInsta(DateTime startDate, DateTime endDate, int campID, int id) {
       this.startDate = startDate;
        this.endDate = endDate;
        this.campID = campID;
        this.id = id;
    }

    public DateTime getTimeStart() {
        return startDate;
    }

    public DateTime getTimeEnd() {
        return endDate;
    }

    public int getRefugeeCamp() {
        return campID;
    }

    public int getEntryId() {
        return id;
    }

    public void setTimeStart(DateTime timeStart) {
        this.startDate = timeStart;
    }

    public void setTimeEnd(DateTime timeEnd) {
        this.endDate = timeEnd;
    }


    public void setRefugeeCamp(int refugeeCamp) {
        this.campID = refugeeCamp;
    }

    public void setEntryId(int entryId) {
        this.id = entryId;
    }
}


