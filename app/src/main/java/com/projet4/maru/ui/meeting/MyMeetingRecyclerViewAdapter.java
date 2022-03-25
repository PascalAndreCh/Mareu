package com.projet4.maru.ui.meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet4.maru.R;
import com.projet4.maru.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

        private final List<Meeting> meetings;

        public MyMeetingRecyclerViewAdapter(ArrayList<Meeting> meetings) {
                this.meetings = meetings;
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
                        SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy hh:mm");

                        meetingText.setText(meeting.getTitle()+" "+fmtOut.format(meeting.getTimeStart()+" "+meeting.getIdRoom()));
                        mails.setText(meeting.getParticipants().getMailAddresses());
                }
        }
}

