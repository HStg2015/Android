package refuhack.bitspls.de.hstuttgart15.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lasse on 07.11.2015.
 */
public class EntryStorage {
    private ArrayList<Entry> entries;
    private static EntryStorage sInstance;

    public static synchronized EntryStorage getInstance(){
        if(sInstance == null){
            sInstance = new EntryStorage();
        }
        return sInstance;
    }


    public EntryStorage(){
        entries = new ArrayList<>();
    }

    public void appendList(ArrayList<Entry> en){
        entries.addAll(en);
    }

    public void addEntry(Entry e){
        entries.add(e);
    }

    public ArrayList<Entry> getList(){
        return entries;
    }

    public void setList(ArrayList<Entry> entries){
       this.entries = entries;
    }
    public Entry getEntry(int id){
            Entry curr;
            for (int i = 0; i < entries.size(); i++) {
                curr = entries.get(i);
                if (curr.getEntryId() == id) {
                    return curr;
                }
            }
        return null;

    }


}
