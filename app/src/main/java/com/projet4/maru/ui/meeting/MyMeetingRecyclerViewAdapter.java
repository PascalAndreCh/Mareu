package com.projet4.maru.ui.meeting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.projet4.maru.R;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Room;
import com.projet4.maru.service.DummyMaReuGenerator;
import com.projet4.maru.service.MaReuApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Meeting> mMeetingArrayList;
    private final Meeting meeting;
    private List<Room> rooms = DummyMaReuGenerator.generateRooms();
    private MyMeetingRecyclerViewAdapter.OnMeetingClickListener listener;
    private static MaReuApiService mApiService;

    public MyMeetingRecyclerViewAdapter(ArrayList<Meeting> mMeetingArrayList, Meeting meeting, OnMeetingClickListener listener) {
        this.mMeetingArrayList= mMeetingArrayList;
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
        Meeting meeting = mMeetingArrayList.get(position);
        viewHolder.displayMeeting(meeting);

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myPopup = new AlertDialog.Builder(view.getContext());
                myPopup.setMessage("Voulez-vous vraiment supprimer ce meeting ? \n \n" + meeting.getTitle());
                myPopup.setTitle("****** ATTENTION ******");
                myPopup.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    mMeetingArrayList.remove(meeting);
                    }
                });
                myPopup.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "ABANDON", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
        });


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMeetingRecyclerViewAdapter.this.listener.onMeetingClick(mMeetingArrayList.get(viewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetingArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView meetingText;
        public final TextView mails;
        public final ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            meetingText = view.findViewById(R.id.meetingText);
            mails = view.findViewById(R.id.mails);
            deleteButton = view.findViewById(R.id.item_list_delete_button);
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

