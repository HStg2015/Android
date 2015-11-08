package refuhack.bitspls.de.hstuttgart15.models;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.activities.ItemActivity;


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
    public void onBindViewHolder(final EntryViewHolder entryViewHolder, final int i) {
        Entry entry = eS.getList().get(i);
        entryViewHolder.vName.setText(entry.name);
        entryViewHolder.vDescription.setText(entry.description);
        Picasso.with(context).load(entry.imageUri).into(entryViewHolder.vImage);

        entryViewHolder.vName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Entry entry = eS.getList().get(i);

                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("description", entry.getDescription());
                intent.putExtra("title", entry.getName());
                intent.putExtra("Picture", entry.getImageUri());
                context.startActivity(intent);
            }
        });
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


