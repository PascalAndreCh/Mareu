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
import com.projet4.maru.model.Room;

import java.util.List;

public class MyRoomRecyclerViewAdapter extends RecyclerView.Adapter<MyRoomRecyclerViewAdapter.ViewHolder> {


    private final List<Room> rooms;
    private final Long idRoom;
    private final int nbPeople2;
    private OnRoomClickListener listener;


    public MyRoomRecyclerViewAdapter(List<Room> rooms, Long idRoom, int nbPeople2, OnRoomClickListener listener) {
        this.rooms = rooms;
        this.listener = listener;
        this.idRoom = idRoom;
        this.nbPeople2 = nbPeople2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Room room = rooms.get(position);
        viewHolder.displayRoom(room);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRoomRecyclerViewAdapter.this.listener.onRoomClick(rooms.get(viewHolder.getAdapterPosition()));
            }
        });

        // TODO
//        viewHolder. image à changer
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView roomText;
        public final TextView nbPeople;
        public final ImageButton itemRoomSelectButton;

        public ViewHolder(View view) {
            super(view);
            roomText = view.findViewById(R.id.roomText);
            nbPeople = view.findViewById(R.id.nbPeople);
            itemRoomSelectButton = view.findViewById(R.id.item_room_select_button);
        }

        public void displayRoom(Room room) {
            roomText.setText("Salle " + room.getNumberRoom() + " " + room.getNameRoom() + " Etage : " + room.getStageRoom());
            nbPeople.setText(Integer.toString(room.getMaximumParticipantRoom()));
            if (idRoom == 0) {
                if (nbPeople2 != 0 && nbPeople2 > room.getMaximumParticipantRoom()) {
                    itemRoomSelectButton.setImageResource(R.drawable.ic_baseline_no_meeting_room_24);
                } else {
                    itemRoomSelectButton.setImageResource(R.drawable.ic_baseline_meeting_room_standby_24);
                }
            } else {
                if (nbPeople2 != 0 && nbPeople2 > room.getMaximumParticipantRoom()) {
                    itemRoomSelectButton.setImageResource(R.drawable.ic_baseline_no_meeting_room_24);
                } else {
                    if (idRoom == room.getIdRoom()) {
                        itemRoomSelectButton.setImageResource(R.drawable.ic_baseline_meeting_room_24);
                    } else {
                        itemRoomSelectButton.setImageResource(R.drawable.ic_baseline_meeting_room_standby_24);
                    }
                }
            }
        }

    }


    public interface OnRoomClickListener {
        void onRoomClick(Room room);

    }

}

