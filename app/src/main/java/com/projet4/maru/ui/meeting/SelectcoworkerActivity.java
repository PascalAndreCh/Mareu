package com.projet4.maru.ui.meeting;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projet4.maru.databinding.ActivitySelectcoworkerBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.MaReuApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SelectcoworkerActivity extends AppCompatActivity implements View.OnClickListener, MyCoworkerRecyclerViewAdapter.OnCoworkerClickListener {

    public ArrayList<Meeting> mMeeting = new ArrayList<>();
    int h = 1;
    List<Long> idList;

    private ActivitySelectcoworkerBinding binding;
    private List<Participant> allPossibleParticipantArrayList;
    private final MaReuApiService mApiService = DI.getStartListApiService();
    public List<Participant> participantsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();

        // on récupère la valeur en entrée
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");

        List<Participant> participantsList = (List<Participant>) args.getSerializable("ARRAYLIST1");


        Toast.makeText(this,"Participants : "+participantsList.size(), Toast.LENGTH_SHORT).show();

    }

    private void initUI() {
        binding = ActivitySelectcoworkerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

        MyCoworkerRecyclerViewAdapter mAdapter = new MyCoworkerRecyclerViewAdapter(allPossibleParticipantArrayList,this);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
        allPossibleParticipantArrayList = mApiService.getParticipants();

    }

    @Override
    public void onCoworkerClick(Participant participant) {
        if (!participantsList.contains(participant)) {
            participantsList.add(participant);
        } else {
            participantsList.remove(participant);  
        }
        Toast.makeText(this,"Participants : "+participantsList.size(), Toast.LENGTH_SHORT).show();

//        idList.add(participant.getId());
        
//        Intent data = new Intent();
//        data.putExtra("PERSON_ID",participant.getId());
//        setResult(RESULT_OK, data);
//        finish();

    }

    @Override
    public void onClick(View view) {
        if (view == binding.addCoworker) {

            // on passe une valeur de retour
//        Intent data = new Intent();

//        data.putExtra("PERSON_ID",participant.getId());

//            Bundle args = new Bundle();
//            args.putSerializable("ARRAYLIST1",(Serializable)participantsList);
//            data.putExtra("BUNDLE",args);
//            setResult(RESULT_OK, data);
//            finish();


//           Intent i2 = new Intent();
//            i2.putExtra(AddMeetingActivity.MEETING2_LIST, "ceci est la valeur de retour");
//            SelectcoworkerActivity.this.setResult(1, i2);
//            SelectcoworkerActivity.this.finish();
             finish();
        }
    }
}
