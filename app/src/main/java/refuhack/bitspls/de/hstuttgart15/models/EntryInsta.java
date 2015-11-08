package refuhack.bitspls.de.hstuttgart15.models;

import org.joda.time.DateTime;

/**
 * Created by gin on 07.11.15.
 */
public class EntryInsta {
    private DateTime startDate, endDate;
    private int id;
    private String campInfo;

    public EntryInsta(DateTime startDate, DateTime endDate, String campInfo, int id) {
       this.startDate = startDate;
        this.endDate = endDate;
        this.campInfo = campInfo;
        this.id = id;
    }

    public DateTime getTimeStart() {
        return startDate;
    }

    public DateTime getTimeEnd() {
        return endDate;
    }

    public String getRefugeeCamp() {
        return campInfo;
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

    public void setRefugeeCamp(String refugeeCamp) {
        this.campInfo = refugeeCamp;
    }

    public void setEntryId(int entryId) {
        this.id = entryId;
    }
}


