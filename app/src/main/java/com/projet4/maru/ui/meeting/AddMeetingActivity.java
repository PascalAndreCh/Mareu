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
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;


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
        dateStart = GregorianCalendar.getInstance();
        dateStart.set(
                dateStart.get(Calendar.YEAR),
                dateStart.get(Calendar.MONTH),
                dateStart.get(Calendar.DAY_OF_MONTH),
                0,
                0,
                0);
        timeEnd = GregorianCalendar.getInstance();
        timeStart = GregorianCalendar.getInstance();
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
//       dateStart = GregorianCalendar.getInstance();
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
                int mYYd6 = dateStart.get(Calendar.YEAR);
                int mMMd6 = dateStart.get(Calendar.MONTH);
                int mDDd6 = dateStart.get(Calendar.DAY_OF_MONTH);
                int mHHd6 = dateStart.get(Calendar.HOUR);
                int mMNd6 = dateStart.get(Calendar.MINUTE);
                int mSSd6 = dateStart.get(Calendar.SECOND);
                int mYYf6 = dateEnd.get(Calendar.YEAR);
                int mMMf6 = dateEnd.get(Calendar.MONTH);
                int mDDf6 = dateEnd.get(Calendar.DAY_OF_MONTH);
                int mHHf6 = dateEnd.get(Calendar.HOUR);
                int mMNf6 = dateEnd.get(Calendar.MINUTE);
                int mSSf6 = dateEnd.get(Calendar.SECOND);
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
                int mYYd5 = dateStart.get(Calendar.YEAR);
                int mMMd5 = dateStart.get(Calendar.MONTH);
                int mDDd5 = dateStart.get(Calendar.DAY_OF_MONTH);
                int mHHd5 = dateStart.get(Calendar.HOUR);
                int mMNd5 = dateStart.get(Calendar.MINUTE);
                int mSSd5 = dateStart.get(Calendar.SECOND);
                int mYYf5 = dateEnd.get(Calendar.YEAR);
                int mMMf5 = dateEnd.get(Calendar.MONTH);
                int mDDf5 = dateEnd.get(Calendar.DAY_OF_MONTH);
                int mHHf5 = dateEnd.get(Calendar.HOUR);
                int mMNf5 = dateEnd.get(Calendar.MINUTE);
                int mSSf5 = dateEnd.get(Calendar.SECOND);
                startActivityForResult(intent2, 2);
            }
        });
    }

    private void dateDialog() {
        int selectedYear = dateStart.get(Calendar.YEAR);
        int selectedMonth = dateStart.get(Calendar.MONTH);
        int selectedDayOfMonth = dateStart.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateStart.set(i,i1,i2);
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                binding.dateText.setText(format1.format(dateStart.getTime()));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
        timeStart.setTime(dateStart.getTime());

        //
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        Calendar dateEnd = service.endDateMeeting(dateStart, durationNumber);
        timeEnd.setTime(dateEnd.getTime());
        binding.hourStartText.setText(format2.format(dateStart.getTime()));
        binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
        //


    }

    private void timeDialog() {
        int selectedHourStartMeeting = 0;
        int selectedMinuteStartMeeting = 0;
        int selectedSecondStartMeeting = 0;

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int j, int j1) {
                dateStart.set(
                        dateStart.get(Calendar.YEAR),
                        dateStart.get(Calendar.MONTH),
                        dateStart.get(Calendar.DAY_OF_MONTH),
                        j,
                        j1,
                        0
                );
                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                binding.hourStartText.setText(format2.format(dateStart.getTime()));
                timeStart.setTime(dateStart.getTime());

                //
                Calendar dateEnd = service.endDateMeeting(dateStart, durationNumber);
                timeEnd.setTime(dateEnd.getTime());
                binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
                //



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
                timeEnd.setTime(dateEnd.getTime());
                timeStart.setTime(dateStart.getTime());
                int mYYd = dateStart.get(Calendar.YEAR);
                int mMMd = dateStart.get(Calendar.MONTH);
                int mDDd = dateStart.get(Calendar.DAY_OF_MONTH);
                int mHHd = dateStart.get(Calendar.HOUR);
                int mMNd = dateStart.get(Calendar.MINUTE);
                int mSSd = dateStart.get(Calendar.SECOND);
                int mYYf = dateEnd.get(Calendar.YEAR);
                int mMMf = dateEnd.get(Calendar.MONTH);
                int mDDf = dateEnd.get(Calendar.DAY_OF_MONTH);
                int mHHf = dateEnd.get(Calendar.HOUR);
                int mMNf = dateEnd.get(Calendar.MINUTE);
                int mSSf = dateEnd.get(Calendar.SECOND);
                int mYYf9 = timeEnd.get(Calendar.YEAR);
                int mMMf9 = timeEnd.get(Calendar.MONTH);
                int mDDf9 = timeEnd.get(Calendar.DAY_OF_MONTH);
                int mHHf9 = timeEnd.get(Calendar.HOUR);
                int mMNf9 = timeEnd.get(Calendar.MINUTE);
                int mSSf9 = timeEnd.get(Calendar.SECOND);
                int mYYf90 = timeStart.get(Calendar.YEAR);
                int mMMf90 = timeStart.get(Calendar.MONTH);
                int mDDf90 = timeStart.get(Calendar.DAY_OF_MONTH);
                int mHHf90 = timeStart.get(Calendar.HOUR);
                int mMNf90 = timeStart.get(Calendar.MINUTE);
                int mSSf90 = timeStart.get(Calendar.SECOND);
                binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, selectedHourNumber, selectedMinuteNumber, is24HView);

        int mYYd6 = dateStart.get(Calendar.YEAR);
        int mMMd6 = dateStart.get(Calendar.MONTH);
        int mDDd6 = dateStart.get(Calendar.DAY_OF_MONTH);
        int mHHd6 = dateStart.get(Calendar.HOUR);
        int mMNd6 = dateStart.get(Calendar.MINUTE);
        int mSSd6 = dateStart.get(Calendar.SECOND);
        int mYYf6 = dateEnd.get(Calendar.YEAR);
        int mMMf6 = dateEnd.get(Calendar.MONTH);
        int mDDf6 = dateEnd.get(Calendar.DAY_OF_MONTH);
        int mHHf6 = dateEnd.get(Calendar.HOUR);
        int mMNf6 = dateEnd.get(Calendar.MINUTE);
        int mSSf6 = dateEnd.get(Calendar.SECOND);
        timePickerDialog.show();
        int mYYd7 = dateStart.get(Calendar.YEAR);
        int mMMd7 = dateStart.get(Calendar.MONTH);
        int mDDd7 = dateStart.get(Calendar.DAY_OF_MONTH);
        int mHHd7 = dateStart.get(Calendar.HOUR);
        int mMNd7 = dateStart.get(Calendar.MINUTE);
        int mSSd7 = dateStart.get(Calendar.SECOND);
        int mYYf7 = dateEnd.get(Calendar.YEAR);
        int mMMf7 = dateEnd.get(Calendar.MONTH);
        int mDDf7 = dateEnd.get(Calendar.DAY_OF_MONTH);
        int mHHf7 = dateEnd.get(Calendar.HOUR);
        int mMNf7 = dateEnd.get(Calendar.MINUTE);
        int mSSf7 = dateEnd.get(Calendar.SECOND);
        int mSSf77 = dateEnd.get(Calendar.SECOND);
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
            int mYYd = dateStart.get(Calendar.YEAR);
            int mMMd = dateStart.get(Calendar.MONTH);
            int mDDd = dateStart.get(Calendar.DAY_OF_MONTH);
            int mHHd = dateStart.get(Calendar.HOUR);
            int mMNd = dateStart.get(Calendar.MINUTE);
            int mSSd = dateStart.get(Calendar.SECOND);
            int mYYf = dateEnd.get(Calendar.YEAR);
            int mMMf = dateEnd.get(Calendar.MONTH);
            int mDDf = dateEnd.get(Calendar.DAY_OF_MONTH);
            int mHHf = dateEnd.get(Calendar.HOUR);
            int mMNf = dateEnd.get(Calendar.MINUTE);
            int mSSf = dateEnd.get(Calendar.SECOND);
            if (requestCode == 1) {
                Bundle args = data.getBundleExtra("BUNDLE");
                participantsList = (List<Participant>) args.getSerializable("ARRAYLIST1");
            } else {
                if (requestCode == 2) {
                    idRoom = data.getLongExtra(ID_ROOM, 0);
                    for (Room room : mApiService.getRooms())   {
                        if (room.getIdRoom() == idRoom) {
                            room2 = room;
                            break;
                        }
                    }
                   binding.roomText.setError(null);
                   binding.roomText.setText(room2.getNumberRoom()+" "+room2.getNameRoom()+" Etage : "+room2.getStageRoom()+" "+room2.getMaximumParticipantRoom()+" person max");
                    if (!service.roomToSmall(idRoom, participantsList.size())) {
                        binding.roomText.setError("Please choice another room, this one is to small for all people");
                    }
                    int mYYd4 = dateStart.get(Calendar.YEAR);
                    int mMMd4 = dateStart.get(Calendar.MONTH);
                    int mDDd4 = dateStart.get(Calendar.DAY_OF_MONTH);
                    int mHHd4 = dateStart.get(Calendar.HOUR);
                    int mMNd4 = dateStart.get(Calendar.MINUTE);
                    int mSSd4 = dateStart.get(Calendar.SECOND);
                    int mYYf4 = dateEnd.get(Calendar.YEAR);
                    int mMMf4 = dateEnd.get(Calendar.MONTH);
                    int mDDf4 = dateEnd.get(Calendar.DAY_OF_MONTH);
                    int mHHf4 = dateEnd.get(Calendar.HOUR);
                    int mMNf4 = dateEnd.get(Calendar.MINUTE);
                    int mSSf4 = dateEnd.get(Calendar.SECOND);
                    int mYYf88 = timeEnd.get(Calendar.YEAR);
                    int mMMf88 = timeEnd.get(Calendar.MONTH);
                    int mDDf88 = timeEnd.get(Calendar.DAY_OF_MONTH);
                    int mHHf88 = timeEnd.get(Calendar.HOUR);
                    int mMNf88 = timeEnd.get(Calendar.MINUTE);
                    int mSSf88 = timeEnd.get(Calendar.SECOND);
                    int mYYf9001 = timeStart.get(Calendar.YEAR);
                    int mMMf9001 = timeStart.get(Calendar.MONTH);
                    int mDDf9001 = timeStart.get(Calendar.DAY_OF_MONTH);
                    int mHHf9001 = timeStart.get(Calendar.HOUR);
                    int mMNf9001 = timeStart.get(Calendar.MINUTE);
                    int mSSf9001 = timeStart.get(Calendar.SECOND);
                    long idRoomB = service.roomIsBetter(idRoom, room2.getMaximumParticipantRoom(), participantsList.size(), timeStart, timeEnd);
                    int mYYd2 = dateStart.get(Calendar.YEAR);
                    int mMMd2 = dateStart.get(Calendar.MONTH);
                    int mDDd2 = dateStart.get(Calendar.DAY_OF_MONTH);
                    int mHHd2 = dateStart.get(Calendar.HOUR);
                    int mMNd2 = dateStart.get(Calendar.MINUTE);
                    int mSSd2 = dateStart.get(Calendar.SECOND);
                    int mYYf2 = dateEnd.get(Calendar.YEAR);
                    int mMMf2 = dateEnd.get(Calendar.MONTH);
                    int mDDf2 = dateEnd.get(Calendar.DAY_OF_MONTH);
                    int mHHf2 = dateEnd.get(Calendar.HOUR);
                    int mMNf2 = dateEnd.get(Calendar.MINUTE);
                    int mSSf2 = dateEnd.get(Calendar.SECOND);
                    int mYYf89 = timeEnd.get(Calendar.YEAR);
                    int mMMf89 = timeEnd.get(Calendar.MONTH);
                    int mDDf89 = timeEnd.get(Calendar.DAY_OF_MONTH);
                    int mHHf89 = timeEnd.get(Calendar.HOUR);
                    int mMNf89 = timeEnd.get(Calendar.MINUTE);
                    int mSSf89 = timeEnd.get(Calendar.SECOND);
                    int mYYf9002 = timeStart.get(Calendar.YEAR);
                    int mMMf9002 = timeStart.get(Calendar.MONTH);
                    int mDDf9002 = timeStart.get(Calendar.DAY_OF_MONTH);
                    int mHHf9002 = timeStart.get(Calendar.HOUR);
                    int mMNf9002 = timeStart.get(Calendar.MINUTE);
                    int mSSf9002 = timeStart.get(Calendar.SECOND);
                    if (idRoomB != idRoom) {
                        for (Room room : mApiService.getRooms()) {
                            if (room.getIdRoom() == idRoomB) {
                                room3 = room;
                                break;
                            }
                        }
                        binding.roomText.setError("This room is better " + room3.getNameRoom());
                    }
                    int mYYd3 = dateStart.get(Calendar.YEAR);
                    int mMMd3 = dateStart.get(Calendar.MONTH);
                    int mDDd3 = dateStart.get(Calendar.DAY_OF_MONTH);
                    int mHHd3 = dateStart.get(Calendar.HOUR);
                    int mMNd3 = dateStart.get(Calendar.MINUTE);
                    int mSSd3 = dateStart.get(Calendar.SECOND);
                    int mYYf3 = dateEnd.get(Calendar.YEAR);
                    int mMMf3 = dateEnd.get(Calendar.MONTH);
                    int mDDf3 = dateEnd.get(Calendar.DAY_OF_MONTH);
                    int mHHf3 = dateEnd.get(Calendar.HOUR);
                    int mMNf3 = dateEnd.get(Calendar.MINUTE);
                    int mSSf3 = dateEnd.get(Calendar.SECOND);
                    int mYYf8 = timeEnd.get(Calendar.YEAR);
                    int mMMf8 = timeEnd.get(Calendar.MONTH);
                    int mDDf8 = timeEnd.get(Calendar.DAY_OF_MONTH);
                    int mHHf8 = timeEnd.get(Calendar.HOUR);
                    int mMNf8 = timeEnd.get(Calendar.MINUTE);
                    int mSSf8 = timeEnd.get(Calendar.SECOND);
                    int mYYf900 = timeStart.get(Calendar.YEAR);
                    int mMMf900 = timeStart.get(Calendar.MONTH);
                    int mDDf900 = timeStart.get(Calendar.DAY_OF_MONTH);
                    int mHHf900 = timeStart.get(Calendar.HOUR);
                    int mMNf900 = timeStart.get(Calendar.MINUTE);
                    int mSSf900 = timeStart.get(Calendar.SECOND);

                    if (!service.roomIsFree(idRoom, timeStart, timeEnd)) {
                        binding.roomText.setError("Please choice another room, this room is not free");
                    }
                }
            }
        }
    }

}
