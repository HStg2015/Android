package refuhack.bitspls.de.hstuttgart15.models;

import java.util.ArrayList;

/**
 * Created by Lasse on 07.11.2015.
 */
public class EntryInstaStorage {
    private ArrayList<EntryInsta> entries;
    private static EntryInstaStorage sInstance;

    public static synchronized EntryInstaStorage getInstance(){
        if(sInstance == null){
            sInstance = new EntryInstaStorage();
        }
        return sInstance;
    }


    public EntryInstaStorage(){
        entries = new ArrayList<>();
    }

    public void appendList(ArrayList<EntryInsta> en){
        entries.addAll(en);
    }

    public void addEntry(EntryInsta e){
        entries.add(e);
    }

    public ArrayList<EntryInsta> getList(){
        return entries;
    }

    public void setList(ArrayList<EntryInsta> entries){
       this.entries = entries;
    }
    public EntryInsta getEntry(int id){
            EntryInsta curr;
            for (int i = 0; i < entries.size(); i++) {
                curr = entries.get(i);
                if (curr.getEntryId() == id) {
                    return curr;
                }
            }
        return null;

    }


}
