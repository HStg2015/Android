package refuhack.bitspls.de.hstuttgart15.models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import refuhack.bitspls.de.hstuttgart15.R;
import refuhack.bitspls.de.hstuttgart15.activities.EventActivity;
import refuhack.bitspls.de.hstuttgart15.activities.ItemActivity;

/**
 * Created by gin on 07.11.15.
 */
public class EntryInstaAdapter extends RecyclerView.Adapter<EntryInstaAdapter.EntryViewHolder> {

    private EntryInstaStorage eS;
    private Context context;

    public EntryInstaAdapter(Context c) {
        eS = EntryInstaStorage.getInstance();
        context = c;
    }

    @Override
    public int getItemCount() {
        return eS.getList().size();
    }

    @Override
    public void onBindViewHolder(EntryViewHolder entryViewHolder, final int i) {
        System.out.println("Nearly");
        EntryInsta entry = eS.getList().get(i);
        entryViewHolder.vName.setText("Flüchtlings Organisation "+entry.getEntryId());

        entryViewHolder.vName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryInsta entry = eS.getList().get(i);

                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("RefugeeCamp", entry.getRefugeeCamp());
                Log.d("Test","Getter: "+entry.getRefugeeCamp());
                intent.putExtra("TimeStart", entry.getTimeStart());
                intent.putExtra("TimeEnd", entry.getTimeEnd());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards, viewGroup, false);

        return new EntryViewHolder(itemView);
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        private TextView vName;
        private TextView vDescription;

        public EntryViewHolder(View v){
            super(v);
            vName = (TextView) v.findViewById(R.id.person_name);
            vDescription = (TextView) v.findViewById(R.id.person_age);
        }
    }


}


