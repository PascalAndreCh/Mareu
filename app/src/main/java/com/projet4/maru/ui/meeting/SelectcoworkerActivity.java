package com.projet4.maru.ui.meeting;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projet4.maru.databinding.ActivitySelectcoworkerBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Person;
import com.projet4.maru.service.MaReuApiService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SelectcoworkerActivity extends AppCompatActivity implements MyCoworkerRecyclerViewAdapter.OnCoworkerClickListener {

    public ArrayList<Meeting> mMeeting = new ArrayList<>();
    int h = 1;


//    mMeeting = (ArrayList<Meeting>) getIntent().getSerializableExtra("MEETING2_LIST");

//    Intent intent = getIntent();
//    Bundle args = intent.getBundleExtra("BUNDLE");
    //    ArrayList<Meeting> mMeeting = (ArrayList<Meeting>) args.getSerializable("MEETING_LIST");
//    Calendar dateDeb = getIntent().getCalendarExtra(DATE_START);
//    Calendar dateFin = getIntent().getCalendarExtra(DATE_END);
//    ArrayList<Participant> participants;


    private ActivitySelectcoworkerBinding binding;
    private ArrayList<Participant> mParticipantArrayList = new ArrayList<>();
    private final MaReuApiService mApiService = DI.getStartListApiService();

    private void initUI() {
        binding = ActivitySelectcoworkerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

        MyCoworkerRecyclerViewAdapter mAdapter = new MyCoworkerRecyclerViewAdapter(mParticipantArrayList,this);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
//        mParticipantArrayList = new ArrayList<>(mParticipant.getParticipants());
        mParticipantArrayList = new ArrayList<>(mApiService.getParticipants());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();

        // on récupère la valeur en entrée
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        List<Participant> participants = (List<Participant>) args.getSerializable("ARRAYLIST");

            Toast.makeText(this,"Participants : "+participants.size(), Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onCoworkerClick(Participant participant) {
        Intent data = new Intent();
        data.putExtra("PERSON_ID",participant.getId());
        setResult(RESULT_OK, data);
        finish();

    }
}
