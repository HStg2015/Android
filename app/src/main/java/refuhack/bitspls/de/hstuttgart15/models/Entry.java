package refuhack.bitspls.de.hstuttgart15.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gin on 07.11.15.
 */
public class Entry {
    protected String name;
    protected String description;
    protected int photoId;

    public Entry(String name, String description, int photoId) {
        this.name = name;
        this.description = description;
        this.photoId = photoId;
    }

}
