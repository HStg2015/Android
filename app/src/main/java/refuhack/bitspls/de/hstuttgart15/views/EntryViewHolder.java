package refuhack.bitspls.de.hstuttgart15.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import refuhack.bitspls.de.hstuttgart15.R;

/**
 * Created by gin on 07.11.15.
 */
public class EntryViewHolder extends RecyclerView.ViewHolder {
    protected TextView vName;
    protected TextView vDescription;
    protected ImageView vImage;

    public EntryViewHolder(View v){
        super(v);
        vName = (TextView) v.findViewById(R.id.person_name);
        vDescription = (TextView) v.findViewById(R.id.person_age);
        vImage = (ImageView) v.findViewById(R.id.person_photo);
    }
}
