package com.projet4.maru.ui.meeting;



import static com.projet4.maru.ui.meeting.AddMeetingActivity.DATE_END;
import static com.projet4.maru.ui.meeting.AddMeetingActivity.DATE_START;
import static com.projet4.maru.ui.meeting.AddMeetingActivity.ID_MEET;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projet4.maru.R;
import com.projet4.maru.databinding.ActivitySelectcoworkerBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.DummyMaReuGenerator;
import com.projet4.maru.service.MaReuApiService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SelectcoworkerActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivitySelectcoworkerBinding binding;
    private final MaReuApiService mApiService = DI.getStartListApiService();
    public List<Participant> participantsList = new ArrayList<>();
    private List<Participant> allPossibleParticipantArrayList = DummyMaReuGenerator.generateParticipants();
    Calendar dateStart;
    Calendar dateEnd;
    String stringDate;
    long idMeet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        idMeet = intent.getLongExtra(ID_MEET,0);
        Bundle args = intent.getBundleExtra("BUNDLE");
        participantsList = (List<Participant>) args.getSerializable("ARRAYLIST1");
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

        initUI();

    }

    private void initUI() {
        binding = ActivitySelectcoworkerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecyclerView();
        binding.addCoworker.setOnClickListener(this);
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerviewcow.setLayoutManager(layoutManager);

        MyCoworkerRecyclerViewAdapter mAdapter = new MyCoworkerRecyclerViewAdapter(allPossibleParticipantArrayList, participantsList, dateStart, dateEnd, idMeet);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerviewcow.getContext(),
                layoutManager.getOrientation());
        binding.recyclerviewcow.addItemDecoration(dividerItemDecoration);
        binding.recyclerviewcow.setAdapter(mAdapter);
    }

    private void initData() {
//        allPossibleParticipantArrayList = mApiService.getParticipants();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addCoworker) {

            // on passe la liste en retour de Selectcoworker à AddMeeting
            Intent data = new Intent();
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST1",(Serializable)participantsList);
            data.putExtra("BUNDLE",args);
            setResult(RESULT_OK, data);
            finish();
         }
    }



}
