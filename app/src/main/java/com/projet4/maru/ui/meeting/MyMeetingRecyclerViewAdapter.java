package com.projet4.maru.ui.meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.projet4.maru.R;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Room;
import com.projet4.maru.service.DummyMaReuGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> meetings;
    private final Meeting meeting;
    private List<Room> rooms = DummyMaReuGenerator.generateRooms();
    private MyMeetingRecyclerViewAdapter.OnMeetingClickListener listener;


    public MyMeetingRecyclerViewAdapter(ArrayList<Meeting> meetings, Meeting meeting, OnMeetingClickListener listener) {
        this.meetings = meetings;
        this.listener = listener;
        this.meeting = meeting;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Meeting meeting = meetings.get(position);
        viewHolder.displayMeeting(meeting);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMeetingRecyclerViewAdapter.this.listener.onMeetingClick(meetings.get(viewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView meetingText;
        public final TextView mails;

        public ViewHolder(View view) {
            super(view);
            meetingText = view.findViewById(R.id.meetingText);
            mails = view.findViewById(R.id.mails);
        }



        public void displayMeeting(Meeting meeting) {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            SimpleDateFormat fmtEnd = new SimpleDateFormat("HH:mm");
            List<Room> rooms = DummyMaReuGenerator.generateRooms();
            String salle = "";
        for (Room room : rooms) {
            if (room.getIdRoom()==meeting.getIdRoom()) {
                salle = room.getNameRoom();
                break;
            }
        }
            meetingText.setText(meeting.getTitle()+" "+fmtOut.format(meeting.getTimeStart().getTime())+" "+fmtEnd.format(meeting.getTimeEnd().getTime())+" "+salle);
            String nom ="";
            int k = meeting.getParticipants().size();
            for (int i=0; i < k; i++) {
                nom = nom+meeting.getParticipants().get(i).getMailAddresses()+" ; ";
            }
            mails.setText(nom);
        }
    }

    public interface OnMeetingClickListener {
        void onMeetingClick(Meeting meeting);
    }

}

