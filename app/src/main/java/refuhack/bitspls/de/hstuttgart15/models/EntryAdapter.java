package refuhack.bitspls.de.hstuttgart15.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import refuhack.bitspls.de.hstuttgart15.R;

/**
 * Created by gin on 07.11.15.
 */
public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private List<Entry> entryList;

    public EntryAdapter(List<Entry> entryList) {
        this.entryList = entryList;
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    @Override
    public void onBindViewHolder(EntryViewHolder entryViewHolder, int i) {
        Entry entry = entryList.get(i);
        entryViewHolder.vName.setText(entry.name);
        entryViewHolder.vDescription.setText(entry.description);
        entryViewHolder.vImage.setImageURI(entry.imageUri);
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cards, viewGroup, false);

        return new EntryViewHolder(itemView);
    }

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


}


