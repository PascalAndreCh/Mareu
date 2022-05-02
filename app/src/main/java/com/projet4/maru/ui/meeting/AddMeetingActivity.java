package com.projet4.maru.ui.meeting;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.projet4.maru.databinding.ActivityAddMeetingBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.MaReuApiService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    @NonNull ActivityAddMeetingBinding binding;
    private MaReuApiService mApiService = DI.getStartListApiService();

    private MaReuApiService service;

    boolean is24HView = true;
    public Calendar dateStart;
    public Calendar dateEnd;
    public Calendar timeStart;
    public Calendar timeEnd;

    private int durationNumber = 0;

    private long id;

    private long idRoom;

    private List<Participant> participants = new ArrayList<>();
    private List<Participant> participantsList = new ArrayList<>();
    private List<Meeting> meetings = new ArrayList<>();


    public static final String MEETING2_LIST = "MEETING2_LIST";

    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    private void initUI() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        getSupportActionBar().setTitle("New meeting");
        meetings = new ArrayList<>(mApiService.getMeetings());
        dateStart =  GregorianCalendar.getInstance();
        dateEnd =  GregorianCalendar.getInstance();
//        participants = new ArrayList<Participant>(mApiService.getParticipants());
        List<Participant> participants = new ArrayList<>();
        participants.clear();
        List<Participant> participantsList = new ArrayList<>();
        participantsList.clear();
        setup();

        // init select date button
        binding.dateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog();
            }
        });

        // init select hour button
        binding.hourStartMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeDialog();
            }
        });

        // init select duration button
        binding.durationMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                durationDialog();
            }
        });

        // init select coworker button
        binding.participantMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddMeetingActivity.this, SelectcoworkerActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST1",(Serializable)participantsList);
                intent.putExtra("BUNDLE",args);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void dateDialog() {
        int selectedYear = 2022;
        int selectedMonth = 4;
        int selectedDayOfMonth = 1;

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = dateStart;
                int j = cal.get(Calendar.HOUR_OF_DAY);
                int j1 = cal.get(Calendar.MINUTE);
                cal.set(i, i1, i2, j, j1, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                binding.dateText.setText(format1.format(cal.getTime()));
                dateStart = cal;
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
    }

    private void timeDialog() {
        int selectedHourStartMeeting = 0;
        int selectedMinuteStartMeeting = 0;
        int selectedSecondStartMeeting = 0;

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int j, int j1) {
                Calendar cal = dateStart;
                int i = cal.get(Calendar.YEAR);
                int i1 = cal.get(Calendar.MONTH);
                int i2 = cal.get(Calendar.DAY_OF_MONTH);
                cal.set(i, i1, i2, j, j1, 0);
                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                binding.hourStartText.setText(format2.format(cal.getTime()));
                dateStart = cal;
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, selectedHourStartMeeting, selectedMinuteStartMeeting, is24HView);

        timePickerDialog.show();
    }

    private void durationDialog() {
        int selectedHourNumber = 0;
        int selectedMinuteNumber = 0;

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int l, int l1) {
                Calendar cal2 = GregorianCalendar.getInstance();
                int k = cal2.get(Calendar.YEAR);
                int k1 = cal2.get(Calendar.MONTH);
                int k2 = cal2.get(Calendar.DAY_OF_MONTH);
                cal2.set(k, k1, k2, l, l1, 0);
                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                binding.durationText.setText(format2.format(cal2.getTime()));
                durationNumber = (l*60)+l1;
                Calendar dateEnd = service.endDateMeeting(dateStart, durationNumber);
                binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, selectedHourNumber, selectedMinuteNumber, is24HView);

        timePickerDialog.show();

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



    private void onSubmit() {
        String meetingtitle = binding.textMeetingtitle.getEditText().getText().toString();
        String meetingComment = binding.textMeetingComment.getEditText().getText().toString();
        long id = System.currentTimeMillis();
//        long idRoom = binding.roomMeeting.getId();
        Calendar timeStart = dateStart;
        Calendar timeEnd = dateEnd;
        List<Participant> participantsList = participants;

        if (meetingtitle.isEmpty()) {
            binding.textMeetingtitle.setError("Please type a title");
            return;
        }
        if (meetingComment.isEmpty()) {
            binding.textMeetingComment.setError("Please type a comment");
            return;
        }
        if (participants.size()==0) {
            binding.textParticipant.setError("Please select participants");
            return;
        }



        mApiService.createMeeting(new Meeting(id, idRoom, timeStart, timeEnd, meetingtitle, meetingComment, participantsList));
        Toast.makeText(this, "Meeting created !", Toast.LENGTH_SHORT).show();
        finish();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // on récupère la valeur de retour
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            long id = data.getLongExtra("PERSON_ID", -1);
//            for (Coworker coworker : mApiService.getCoworkers()){
//                if (coworker.getId()==id) {
//                    Participant part = new Participant(coworker.getId(), coworker.getName(), coworker.getMailAddresses(), coworker.getDepartment(), coworker.getFunction());
//                    participants.add(part) ;
//            List<Participant> participantsList = this.getIntent().getExtras().getParcelable("ARRAYLIST1");

                    Toast.makeText(this, "participant créé !", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this,"Participants : "+participantsList.size(), Toast.LENGTH_SHORT).show();
//                }
//            }
            // display participant passer la list
        }

    }

}
