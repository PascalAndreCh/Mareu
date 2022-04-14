package com.projet4.maru.ui.meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.projet4.maru.R;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.MaReuApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MyCoworkerRecyclerViewAdapter extends RecyclerView.Adapter<MyCoworkerRecyclerViewAdapter.ViewHolder> {


    private final List<Coworker> coworkers;


    public MyCoworkerRecyclerViewAdapter(ArrayList<Coworker> coworkers) {
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
        Coworker coworker = coworkers.get(position);
        viewHolder.displayCoworker(coworker);
    }

    @Override
    public int getItemCount() {
        return coworkers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView coworkerText;
        public final TextView coworkerTextSuit;

        public ViewHolder(View view) {
            super(view);
            coworkerText = view.findViewById(R.id.coworkerText);
            coworkerTextSuit = view.findViewById(R.id.coworkerTextSuit);
        }

        public void displayCoworker(Coworker coworker) {
            coworkerText.setText(coworker.getName()+" "+coworker.getFunction());
            coworkerTextSuit.setText(coworker.getDepartment()+" "+coworker.getMailAddresses());
//           if (participantIsFree(coworker.getId(), dateStart, dateEnd)){

//            }
        }


    }

//    public boolean participantIsFree(long idParticipant, Calendar mMeetingDateStart, Calendar mMeetingDateEnd) {
//        for (Meeting meeting : mMeeting) {
//            ArrayList<Participant> participants = (ArrayList<Participant>) meeting.getParticipants();
//            for (Participant participant : participants) {
//                if (idParticipant == participant.getId()) {
//                    if (mMeetingDateEnd.before(meeting.getTimeStart()) || mMeetingDateEnd.equals(meeting.getTimeStart())) {
//                        continue;
//                   } else if (mMeetingDateStart.after(meeting.getTimeEnd()) || mMeetingDateStart.equals(meeting.getTimeEnd())) {
//                        continue;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
}
