package refuhack.bitspls.de.hstuttgart15.models;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import refuhack.bitspls.de.hstuttgart15.R;

/**
 * Created by gin on 07.11.15.
 */
public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private EntryStorage eS;
    private Context context;

    public EntryAdapter(Context c) {
        eS = EntryStorage.getInstance();
        context = c;
    }

    @Override
    public int getItemCount() {
        return eS.getList().size();
    }

    @Override
    public void onBindViewHolder(EntryViewHolder entryViewHolder, int i) {
        Entry entry = eS.getList().get(i);
        entryViewHolder.vName.setText(entry.getName());
        entryViewHolder.vDescription.setText(entry.getDescription());
        Picasso.with(context).load(entry.getImageUri()).into(entryViewHolder.vImage);
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


