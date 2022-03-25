package com.projet4.maru.ui.meeting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projet4.maru.R;
import com.projet4.maru.databinding.ActivityAddMeetingBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.MaReuApiService;

import java.util.Date;
import java.util.List;


public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityAddMeetingBinding binding;
    private MaReuApiService mMeeting = (MaReuApiService) DI.getStartListApiService().getMeetings();

    private void initUI() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        getSupportActionBar().setTitle("New meeting");
    }

    private void setButton() {
        binding.meetingSet.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.meetingSet) {
            onSubmit();
        }
    }


    private long id;

    private long idRoom;

    private Date timeStart;

    private Date timeEnd;

    private List<Participant> participants;

    private void onSubmit() {
        String meetingtitle = binding.textMeetingtitle.getEditText().getText().toString();
        String meetingComment = binding.textMeetingComment.getEditText().getText().toString();
        // date

        // heure

        // heure fin

        // list participants

        // salle


        if (meetingtitle.isEmpty()) {
            binding.textMeetingtitle.setError("Please type a title");
            return;
        }
        if (meetingComment.isEmpty()) {
            binding.textMeetingComment.setError("Please type a comment");
            return;
        }


        mMeeting.createMeeting(new Meeting(id, idRoom, timeStart, timeEnd, meetingtitle, meetingComment, participants ));
        Toast.makeText(this, "Meeting created !", Toast.LENGTH_SHORT).show();
        finish();

    }

}
