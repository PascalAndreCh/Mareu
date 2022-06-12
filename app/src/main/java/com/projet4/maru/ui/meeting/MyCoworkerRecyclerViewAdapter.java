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

import java.util.Calendar;
import java.util.List;

public class MyCoworkerRecyclerViewAdapter extends RecyclerView.Adapter<MyCoworkerRecyclerViewAdapter.ViewHolder> {

    private final List<Participant> allPossibleParticipantArrayList;
    private final List<Participant> participantsList;
    private OnCoworkerClickListener listener;
    private Calendar dateStart;
    private Calendar dateEnd;
    private MaReuApiService mApiService = DI.getStartListApiService();
    private long idMeet;



    public MyCoworkerRecyclerViewAdapter(List<Participant> allPossibleParticipantArrayList, List<Participant> participantsList, Calendar dateStart, Calendar dateEnd, long idMeet, OnCoworkerClickListener listener) {
        this.participantsList = participantsList;
        this.listener = listener;
        this.allPossibleParticipantArrayList = allPossibleParticipantArrayList;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.idMeet = idMeet;
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
        Participant participant = allPossibleParticipantArrayList.get(position);
        viewHolder.displayCoworker(participant);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Participant p = allPossibleParticipantArrayList.get(viewHolder.getAdapterPosition());
                if (participantsList.contains(p)) {
                    if (!mApiService.participantIsFree(participant.getId(), dateStart, dateEnd, idMeet)) {
                        viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_off_24);
                    } else {
                        viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_standby_24);
                    }
                    participantsList.remove(p);
                } else {
                    viewHolder.itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_green_24);
                    participantsList.add(p);
                }
                MyCoworkerRecyclerViewAdapter.this.listener.onCoworkerClick(p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allPossibleParticipantArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView initial;
        public final TextView coworkerText;
        public final TextView coworkerTextSuit;
        public final ImageButton itemCoworkerSelectButton;

        public ViewHolder(View view) {
            super(view);
            initial = view.findViewById((R.id.initial));
            coworkerText = view.findViewById(R.id.coworkerText);
            coworkerTextSuit = view.findViewById(R.id.coworkerTextSuit);
            itemCoworkerSelectButton = view.findViewById(R.id.item_coworker_select_button);
        }

        public void displayCoworker(Participant participant) {
            coworkerText.setText(participant.getName());
            coworkerTextSuit.setText(participant.getMailAddresses());
            initial.setText(participant.getName().substring(0,1).toUpperCase());

            if (participantsList.contains(participant)) {
                itemCoworkerSelectButton.setImageResource(R.drawable.ic_baseline_person_green_24);
            } else {
                if (!mApiService.participantIsFree(participant.getId(), dateStart, dateEnd, idMeet)) {
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

