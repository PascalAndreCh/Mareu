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

    private final List<Participant> coworkers;
    private final List<Participant> participants;
    private OnCoworkerClickListener listener;


    public MyCoworkerRecyclerViewAdapter(List<Participant> coworkers, List<Participant> participants, OnCoworkerClickListener listener) {
        this.participants = participants;
        this.listener = listener;
        this.coworkers = coworkers;
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
        Participant participant = coworkers.get(position);
        viewHolder.displayCoworker(participant);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Participant p = coworkers.get(viewHolder.getAdapterPosition());
                if (participants.contains(p)) {
                    viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_standby_24);
                    participants.remove(p);
                } else {
                    viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_green_24);
                    participants.add(p);
                }
                MyCoworkerRecyclerViewAdapter.this.listener.onCoworkerClick(p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coworkers.size();
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
            if (participants.contains(participant)) {
                itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_green_24);
            } else {
                itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_standby_24);
            }
//            itemCoworkerSelectButton.setImageIcon(ic_baseline_person_green_24);

//           if (participantIsFree(coworker.getId(), dateStart, dateEnd)){

//            }
        }

    }


    public interface OnCoworkerClickListener {
        void onCoworkerClick(Participant participant);

    }

}

