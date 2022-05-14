package com.projet4.maru.ui.meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet4.maru.R;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.MaReuApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyCoworkerRecyclerViewAdapter extends RecyclerView.Adapter<MyCoworkerRecyclerViewAdapter.ViewHolder> {

    private final List<Participant> coworkers;
    private final List<Participant> participants;
    private OnCoworkerClickListener listener;
    private Calendar dateStart;
    private Calendar dateEnd;
    private MaReuApiService mApiService = DI.getStartListApiService();
    private MaReuApiService service;




    public MyCoworkerRecyclerViewAdapter(List<Participant> coworkers, List<Participant> participants, Calendar dateStart, Calendar dateEnd, OnCoworkerClickListener listener) {
        this.participants = participants;
        this.listener = listener;
        this.coworkers = coworkers;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
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
                    if (!mApiService.participantIsFree(participant.getId(), dateStart, dateEnd)) {
                        viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_off_24);
                    } else {
                        viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_standby_24);
                    }
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
                if (!mApiService.participantIsFree(participant.getId(), dateStart, dateEnd)) {
                    itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_off_24);
                } else {
                    itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_standby_24);
                }
            }
        }

    }


    public interface OnCoworkerClickListener {
        void onCoworkerClick(Participant participant);

    }

}

