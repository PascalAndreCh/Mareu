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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SelectcoworkerActivity extends AppCompatActivity implements View.OnClickListener, MyCoworkerRecyclerViewAdapter.OnCoworkerClickListener {


    private ActivitySelectcoworkerBinding binding;
    private List<Participant> allPossibleParticipantArrayList;
    private final MaReuApiService mApiService = DI.getStartListApiService();
    public List<Participant> participantsList = new ArrayList<>();
//    public List<Participant> participantsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();

        // on récupère en entrée la liste des participants, la première fois, elle est vide
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        List<Participant> participantsList = (List<Participant>) args.getSerializable("ARRAYLIST1");

        Toast.makeText(this, "Part 1 : " + participantsList.size(), Toast.LENGTH_SHORT).show();

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

        MyCoworkerRecyclerViewAdapter mAdapter = new MyCoworkerRecyclerViewAdapter(allPossibleParticipantArrayList, this);
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
        // On sélection un collaborateur. S'il existe dans la liste, on le supprime de la liste, sinon on l'ajoute à la liste

//        if (!participantsList.contains(participant)) {
//            participantsList.add(participant);
//        } else {
//            participantsList.remove(participant);
//        }
        boolean test = false;
        Toast.makeText(this, "Part 2 : " + participantsList.size(), Toast.LENGTH_SHORT).show();


        if (participantsList.size()==0) {
            test = false;
        } else {
            for (Participant i : participantsList) {
                if (i.getId() == participant.getId()) {
                    test = true;
                    break;
                } else {
                    test = false;
                }
            }
        }
        if (test) {
            participantsList.remove(participant);
        } else {
            participantsList.add(participant);
        }

        Toast.makeText(this, "Part 3 : " + participantsList.size(), Toast.LENGTH_SHORT).show();

//        idList.add(participant.getId());
//        Intent data = new Intent();
//        data.putExtra("PERSON_ID",participant.getId());
//        setResult(RESULT_OK, data);
//        finish();
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
            Toast.makeText(this, "Part 4 : " + participantsList.size(), Toast.LENGTH_SHORT).show();
            finish();
         }
    }
}
