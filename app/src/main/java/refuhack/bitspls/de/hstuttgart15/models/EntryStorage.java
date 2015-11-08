package refuhack.bitspls.de.hstuttgart15.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lasse on 07.11.2015.
 */
public class EntryStorage {
    private ArrayList<Entry> entries;
    private static EntryStorage sInstance;

    public static synchronized EntryStorage getInstance() {
        if (sInstance == null) {
            sInstance = new EntryStorage();
        }
        return sInstance;
    }


    public EntryStorage() {
        entries = new ArrayList<>();
    }

    public void appendList(ArrayList<Entry> en) {
        entries.addAll(en);
    }

    public void addEntry(Entry e) {
        entries.add(e);
    }

    public ArrayList<Entry> getList() {
        return entries;
    }

    public void setList(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public Entry getEntry(int id) {
        for (Entry e : entries) {
            if (e.getEntryId() == id)
                return e;
        }
        return null;
    }

    public List<Entry> getEntriesByCategory(int categoryId) {
        ArrayList<Entry> filtered = new ArrayList<>();
        for (Entry e : entries) {
            if (e.getCategoryId() == categoryId)
                filtered.add(e);
        }

        return filtered;
    }
}
