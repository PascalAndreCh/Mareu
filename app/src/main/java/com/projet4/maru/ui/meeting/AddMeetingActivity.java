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
import com.projet4.maru.model.Room;
import com.projet4.maru.service.MaReuApiService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    @NonNull
    ActivityAddMeetingBinding binding;
    private MaReuApiService mApiService = DI.getStartListApiService();

    private MaReuApiService service;

    boolean is24HView = true;
    public Calendar dateStart;
    public Calendar dateEnd;
    public Calendar timeStart;
    public Calendar timeEnd;
    public static final String ID_ROOM = "ID_ROOM";
    public static final String NBPEOPLE = "NBPEOPLE";
    public static Room room;
    public static Room room2;
    public static Room room3;
    private int durationNumber = 0;
    private List<Room> rooms1;


    private long idRoom;

    private List<Participant> participants = new ArrayList<>();
    private List<Participant> participantsList = new ArrayList<>();
    private List<Meeting> meetings = new ArrayList<>();


    public static final String MEETING2_LIST = "MEETING2_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Room> rooms1 = mApiService.getRooms();
        initUI();
    }

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
        dateStart = GregorianCalendar.getInstance();
        dateEnd = GregorianCalendar.getInstance();
//        participants = new ArrayList<Participant>(mApiService.getParticipants());
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

                // passage de la liste des participants de AddMeeting à Selectcoworker, la première fois, elle est vide
                Intent intent = new Intent(AddMeetingActivity.this, SelectcoworkerActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST1", (Serializable) participantsList);
                intent.putExtra("BUNDLE", args);
                startActivityForResult(intent, 1);
            }
        });

        // init select room button
        binding.roomMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AddMeetingActivity.this, SelectroomActivity.class);
                intent2.putExtra(ID_ROOM, idRoom);
                intent2.putExtra(NBPEOPLE, participantsList.size());
                startActivityForResult(intent2, 2);
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
                durationNumber = (l * 60) + l1;
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
    public void onClick(View view) {
        if (view == binding.meetingSet) {
            onSubmit();
        }
    }

    private void onSubmit() {
        String meetingtitle = binding.textMeetingtitle.getEditText().getText().toString();
        String meetingComment = binding.textMeetingComment.getEditText().getText().toString();
        long id = System.currentTimeMillis();
        Calendar timeStart = dateStart;
        Calendar timeEnd = dateEnd;

        if (meetingtitle.isEmpty()) {
            binding.textMeetingtitle.setError("Please type a title");
            return;
        }
        if (meetingComment.isEmpty()) {
            binding.textMeetingComment.setError("Please type a comment");
            return;
        }
        if (participantsList.size() == 0) {
            binding.textParticipant.setError("Please select participants");
            return;
        }


        mApiService.createMeeting(new Meeting(id, idRoom, timeStart, timeEnd, meetingtitle, meetingComment, participantsList));
        Toast.makeText(this, "Meeting created ! dans Add", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // on récupère la valeur de retour
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bundle args = data.getBundleExtra("BUNDLE");
                participantsList = (List<Participant>) args.getSerializable("ARRAYLIST1");
                Toast.makeText(this, "Part 5 : " + participantsList.size(), Toast.LENGTH_SHORT).show();
            } else {
                if (requestCode == 2) {
                    idRoom = data.getLongExtra(ID_ROOM, 0);
 //                   for (Room room : rooms1) {
                    for (Room room : mApiService.getRooms())   {
                        if (room.getIdRoom() == idRoom) {
                            room2 = room;
                            break;
                        }
                    }
                   binding.roomText.setError(null);
                   binding.roomText.setText(room2.getNameRoom());
                    if (!service.roomToSmall(idRoom, participantsList.size())) {
                        binding.roomText.setError("Please choice another room, this one is to small for all people");
                    }
                    long idRoomB = service.roomIsBetter(idRoom, room2.getMaximumParticipantRoom(), participantsList.size(), dateStart, dateEnd);
                    if (idRoomB != idRoom) {
                        for (Room room : mApiService.getRooms()) {
                            if (room.getIdRoom() == idRoomB) {
                                room3 = room;
                                break;
                            }
                        }
                        binding.roomText.setError("This room is better " + room3.getNameRoom());
                    }
                   if (service.roomIsFree(idRoom, dateStart, dateEnd)) {
                        binding.roomText.setError("Please choice another room, this room is not free");
                    }
                }
            }
        }
    }

}
