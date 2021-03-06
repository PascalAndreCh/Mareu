package com.projet4.maru.ui.meeting;

import static com.projet4.maru.ui.meeting.AddMeetingActivity.DATE_END;
import static com.projet4.maru.ui.meeting.AddMeetingActivity.DATE_START;
import static com.projet4.maru.ui.meeting.AddMeetingActivity.ID_MEET;
import static com.projet4.maru.ui.meeting.AddMeetingActivity.ID_ROOM;
import static com.projet4.maru.ui.meeting.AddMeetingActivity.NBPEOPLE;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projet4.maru.databinding.ActivitySelectroomBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.service.MaReuApiService;

import java.util.Calendar;
import java.util.List;

public class SelectroomActivity extends AppCompatActivity implements View.OnClickListener, MyRoomRecyclerViewAdapter.OnRoomClickListener {

    private ActivitySelectroomBinding binding;
    private List<Room> allPossibleRoomArrayList;
    private final MaReuApiService mApiService = DI.getStartListApiService();
    long idRoom;
    long idMeet;
    int nbPeople;
    Calendar dateStart;
    Calendar dateEnd;
    String stringDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        idMeet = intent.getLongExtra(ID_MEET,0);
        idRoom = intent.getLongExtra(ID_ROOM, 0);
        nbPeople = intent.getIntExtra(NBPEOPLE, 0);

        stringDate = intent.getStringExtra(DATE_START);
        try {
            dateStart = mApiService.stringToDate(stringDate);
        } catch (Exception e) {
            String zz = "erreur format";
        }
        stringDate = intent.getStringExtra(DATE_END);
        try {
            dateEnd = mApiService.stringToDate(stringDate);
        } catch (Exception e) {
                       String zz = "erreur format";
        }



        initData();
        initUI();
    }

    private void initUI() {
        binding = ActivitySelectroomBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerviewroom.setLayoutManager(layoutManager);
        MyRoomRecyclerViewAdapter mAdapter = new MyRoomRecyclerViewAdapter(allPossibleRoomArrayList, idRoom, nbPeople, dateStart, dateEnd, idMeet, this);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerviewroom.getContext(),
                layoutManager.getOrientation());
        binding.recyclerviewroom.addItemDecoration(dividerItemDecoration);
        binding.recyclerviewroom.setAdapter(mAdapter);
    }

    private void initData() {
        allPossibleRoomArrayList = mApiService.getRooms();
    }

    @Override
    public void onRoomClick(Room room) {

        Intent data2 = new Intent();
        data2.putExtra(ID_ROOM,room.getIdRoom());
        setResult(RESULT_OK, data2);
        finish();
    }

    @Override
    public void onClick(View view) {
    }
}



