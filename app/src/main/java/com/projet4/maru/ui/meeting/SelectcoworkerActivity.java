package com.projet4.maru.ui.meeting;


import android.content.Intent;
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
import com.projet4.maru.service.MaReuApiService;

import java.io.Serializable;
import java.util.ArrayList;


public class SelectcoworkerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, Serializable {

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
    private ArrayList<Coworker> mCoworkerArrayList = new ArrayList<>();
    private ArrayList<Participant> mParticipantArrayList = new ArrayList<>();
    private final MaReuApiService mCoworker = (MaReuApiService) DI.getStartListApiService();

    private void initUI() {
        binding = ActivitySelectcoworkerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

        MyCoworkerRecyclerViewAdapter mAdapter = new MyCoworkerRecyclerViewAdapter(mCoworkerArrayList);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
//        mParticipantArrayList = new ArrayList<>(mParticipant.getParticipants());
        mCoworkerArrayList = new ArrayList<>(mCoworker.getCoworkers());
    }

    private void setButton() {
        binding.addCoworker.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();

        // on récupère la valeur en entrée
        Intent intent = getIntent();
        if (h == 1) {
//            Bundle args = intent.getBundleExtra("BUNDLE");
//            ArrayList<Meeting> mMeeting = (ArrayList<Meeting>) args.getSerializable("MEETING_LIST");

            String message = intent.getStringExtra(AddMeetingActivity.MEETING2_LIST);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        if (view == binding.addCoworker) {

            // on passe une valeur de retour
            Intent i2 = new Intent();
            i2.putExtra(AddMeetingActivity.MEETING2_LIST, "ceci est la valeur de retour");
            SelectcoworkerActivity.this.setResult(1, i2);
            SelectcoworkerActivity.this.finish();
            // finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
