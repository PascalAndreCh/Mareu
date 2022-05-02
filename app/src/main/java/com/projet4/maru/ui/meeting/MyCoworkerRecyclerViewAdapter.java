package com.projet4.maru.ui.meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.projet4.maru.R;
import com.projet4.maru.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class MyCoworkerRecyclerViewAdapter extends RecyclerView.Adapter<MyCoworkerRecyclerViewAdapter.ViewHolder> {


    private final List<Participant> participants;
    private OnCoworkerClickListener listener;


    public MyCoworkerRecyclerViewAdapter(List<Participant> participants , OnCoworkerClickListener listener) {
        // TODO passer liste Coworker
        this.participants = participants;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_coworker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Participant participant = participants.get(position);
        viewHolder.displayCoworker(participant);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCoworkerRecyclerViewAdapter.this.listener.onCoworkerClick(participants.get(viewHolder.getAdapterPosition()));
            }
        });
        // TODO
//        viewHolder. image à changer
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView coworkerText;
        public final TextView coworkerTextSuit;
        public final ImageButton itemCoworkerSelectButton;

        public ViewHolder(View view) {
            super(view);
            coworkerText = view.findViewById(R.id.coworkerText);
            coworkerTextSuit = view.findViewById(R.id.coworkerTextSuit);
            itemCoworkerSelectButton = view.findViewById(R.id.item_coworker_select_button);
        }

        public void displayCoworker(Participant participant) {
            coworkerText.setText(participant.getName());
            coworkerTextSuit.setText(participant.getMailAddresses());

//            itemCoworkerSelectButton.setImageIcon(ic_baseline_person_green_24);

//           if (participantIsFree(coworker.getId(), dateStart, dateEnd)){

//            }
        }

    }



  public interface OnCoworkerClickListener {
        void onCoworkerClick(Participant participant);

  }

}

