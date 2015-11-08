package refuhack.bitspls.de.hstuttgart15.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tniederhausen on 08.11.2015.
 */
public class CategoryStorage {
    private Map<Integer, Category> categories = new HashMap<>();

    // Singleton boilerplate...
    static volatile CategoryStorage instance = null;
    public static synchronized CategoryStorage getGlobalInstance() {
        if (instance == null)
            instance = new CategoryStorage();

        return instance;
    }

    /**
     * Overwrite the stored categories
     * @param categories
     */
    public void importData(Collection<Category> categories) {
        this.categories.clear();
        for (Category c : categories)
            this.categories.put(c.getId(), c);
    }

    public Category getById(int id) {
        return categories.get(id);
    }

    public int getCount(){return categories.size();}

    public ArrayList<String> getArrayList(){
        ArrayList<String> al = new ArrayList<String>();
        for(int i = 1; i== categories.size(); i++){
            al.add(categories.get(i).getTitle());
        }
        return al;
    }
}
