package com.projet4.maru.ui.meeting;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;


public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener, Serializable, Parcelable {

    @NonNull ActivityAddMeetingBinding binding;
    private MaReuApiService mMeeting = (MaReuApiService) DI.getStartListApiService();

    boolean is24HView = true;
    private Calendar selectedStartDate;
    private Calendar selectedTimeStart;
    private int selectedDuration;
    public Calendar dateStart =  GregorianCalendar.getInstance();
    public Calendar dateEnd =  GregorianCalendar.getInstance();
    private int durationNumber = 0;

    public static final String MEETING2_LIST = "MEETING2_LIST";
    public static final String DATE2_START = "DATE2_START";
    public static final String DATE2_END = "DATE2_END";
    public static final String MEETING_LIST = "MEETING_LIST";
    public static final String DATE_START = "DATE_START";
    public static final String DATE_END = "DATE_END";


    private void initUI() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        getSupportActionBar().setTitle("New meeting");

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

//                Intent i = new Intent(AddMeetingActivity.this, SelectcoworkerActivity.class);
//                Bundle args = new Bundle();
//                args.putSerializable("MEETING_LIST",(Serializable)mMeeting);
//                i.putExtra("BUNDLE",args);

//                i.putExtra(DATE_START, dateStart);
//                i.putExtra(DATE_END, dateEnd);

//                List<Meeting> meetingss = mMeeting.getMeetings();
//                Intent intent = new Intent(AddMeetingActivity.this, SelectcoworkerActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("MEETING_LIST", (ArrayList<? extends Parcelable>) meetingss);
//                intent.putExtras(bundle);
//                startActivity(intent);

                // on passe une valeur en entrée de la seconde activité
                Intent i1 = new Intent(AddMeetingActivity.this, SelectcoworkerActivity.class);
                i1.putExtra(MEETING2_LIST, "test de départ");
//                i1.putExtra(MEETING_LIST, (Parcelable) mMeeting);
//                Bundle args = new Bundle();
//                List<Meeting> meet = mMeeting.getMeetings();
//                args.putParcelableArrayList("MEETING_LIST", (ArrayList<? extends Parcelable>) meet);
//                args.putSerializable("MEETING_LIST",(Serializable)mMeeting);
//                i1.putExtra("BUNDLE",args);


                startActivityForResult(i1, 0);

//                 startActivityForResult(i,3);
//                startActivity(new Intent(i));
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
                int j = cal.get(Calendar.HOUR);
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
                // dateEnd = endDateMeeting(dateStart, durationNumber);
                dateEnd = dateStart;
                dateEnd.add(Calendar.MINUTE, durationNumber);
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


    private long id;

    private long idRoom;

    private Calendar timeStart;

    private Calendar timeEnd;

    private List<Participant> participants;

    private void onSubmit() {
        String meetingtitle = binding.textMeetingtitle.getEditText().getText().toString();
        String meetingComment = binding.textMeetingComment.getEditText().getText().toString();
        //       Calendar meetingDate = binding.dateText

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


        mMeeting.createMeeting(new Meeting(id, idRoom, timeStart, timeEnd, meetingtitle, meetingComment, participants));
        Toast.makeText(this, "Meeting created !", Toast.LENGTH_SHORT).show();
        finish();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // on récupère la valeur de retour
        if( resultCode==1 ) {
            String s = data.getStringExtra(MEETING2_LIST);
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
