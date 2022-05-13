package com.projet4.maru.ui.meeting;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.projet4.maru.databinding.ActivityAddMeetingBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.service.MaReuApiService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
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
    public String meetingtitle;
    public String meetingComment;
    public static final String ID_ROOM = "ID_ROOM";
    public static final String NBPEOPLE = "NBPEOPLE";
    public static Room room;
    public static Room room2;
    public static Room room3;
    public static Participant part;
    public static Meeting meeting;
    private int durationNumber = 0;
    private int nbParticipants;
    private List<Room> rooms1;
    public long idMax;
    public int provenance;
    public int selectedHourNumber = 0;
    public int selectedMinuteNumber = 0;
    Calendar cal2 = GregorianCalendar.getInstance();
    int selectedYear;
    int selectedMonth;
    int selectedDayOfMonth;
    int selectedHourStartMeeting;
    int selectedMinuteStartMeeting;
    String textPart;

    private long idRoom;

    private List<Participant> participants = new ArrayList<>();
    private List<Participant> participantsList = new ArrayList<>();
    private List<Meeting> meetings = new ArrayList<>();


    public static final String MEETING2_LIST = "MEETING2_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setup();

        initVariables();

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
        if (provenance == 3) {
            getSupportActionBar().setTitle("New meeting");
        }

        if (provenance == 4) {
            getSupportActionBar().setTitle("Modify meeting");
            affichage();
    }

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
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateStart.set(i, i1, i2);
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                binding.dateText.setText(format1.format(dateStart.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
        timeStart.setTime(dateStart.getTime());
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        Calendar dateEnd = service.endDateMeeting(dateStart, durationNumber);
        timeEnd.setTime(dateEnd.getTime());
        binding.hourStartText.setText(format2.format(dateStart.getTime()));
        binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
    }

    private void timeDialog() {
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
                Calendar dateEnd = service.endDateMeeting(dateStart, durationNumber);
                timeEnd.setTime(dateEnd.getTime());
                binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, selectedHourStartMeeting, selectedMinuteStartMeeting, is24HView);
        timePickerDialog.show();
    }

    private void durationDialog() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int l, int l1) {
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if (view == binding.meetingSet) {
            onSubmit();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onSubmit() {
        String meetingtitle = binding.textMeetingtitle.getEditText().getText().toString();
        String meetingComment = binding.textMeetingComment.getEditText().getText().toString();
        long idMax = 1;
        Optional<Long> idMaxOpt = meetings.stream().map(Meeting::getId).max((i1, i2)-> (int) (i1-i2));
        if (idMaxOpt.isPresent()) {
            idMax = idMaxOpt.get()+1;
        }
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
        if (idRoom ==0) {
            binding.roomText.setError("Please select room");
            return;
        }
        if (durationNumber > 240) {
            binding.durationText.setError("Please choice a duration inferior to 4h00");
            return;
        }
        if (durationNumber == 0) {
            binding.durationText.setError("Please choice a duration");
            return;
        }
        if (!mApiService.inputDateSuperiorToThisDay(timeEnd)){
            binding.hourEndMeetingText.setError("The end time doesn't inferior to now time");
            return;
        }
        if (!mApiService.inputDateSuperiorToThisDay(timeStart)){
            binding.hourStartText.setError("This meeting is starting");
            return;
        }
        if (provenance == 3) {
            mApiService.createMeeting(new Meeting(idMax, idRoom, timeStart, timeEnd, meetingtitle, meetingComment, participantsList));
            Toast.makeText(this, "Meeting created !", Toast.LENGTH_SHORT).show();
        } else  if (provenance == 4) {
            meeting.setIdRoom(idRoom);
            meeting.setTimeStart(timeStart);
            meeting.setTimeEnd(timeEnd);
            meeting.setTitle(meetingtitle);
            meeting.setDescription(meetingComment);
            meeting.setParticipants(participantsList);
            Optional<Meeting> optEmp = meetings.stream().filter(e -> e.getId() == meeting.getId()).findFirst();
            if (optEmp.isPresent()) {
                int index = meetings.indexOf(optEmp.get());
                meetings.set(index, meeting);
            }

            Optional<Meeting> optEmp2 = mApiService.getMeetings().stream().filter(e -> e.getId() == meeting.getId()).findFirst();
            if (optEmp2.isPresent()) {
                int index = mApiService.getMeetings().indexOf(optEmp2.get());
                mApiService.setMeeting(index, meeting);
            }

            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST2",(Serializable)meeting);
            args.putSerializable("ARRAYLIST3",(Serializable)meetings);
            intent.putExtra("BUNDLE",args);
            intent.putExtra("PROVENANCE", 4);
            setResult(RESULT_OK, intent);
            Toast.makeText(this, "Meeting modify !", Toast.LENGTH_SHORT).show();
        }
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
                nbParticipants = participantsList.size();
                binding.participantNumber.setText(Integer.toString(nbParticipants));
                binding.participantNumber.setError(null);
                if (nbParticipants > 10) {
                    int diff = nbParticipants - 10;
                    binding.participantNumber.setError("The number's people is to high, please, remove " + diff + " people");
                }
                String textPart = "";
                for (Participant part : participantsList) {
                    textPart = textPart + part.getName() + " \t " + part.getMailAddresses() + "\n";
                }
                binding.textParticipant.setText(textPart);
            } else {
                if (requestCode == 2) {
                    idRoom = data.getLongExtra(ID_ROOM, 0);
                    for (Room room : mApiService.getRooms()) {
                        if (room.getIdRoom() == idRoom) {
                            room2 = room;
                            break;
                        }
                    }
                    binding.roomText.setText("N° " + room2.getNumberRoom() + " " + room2.getNameRoom() + "        étage " + room2.getStageRoom() + "      " + room2.getMaximumParticipantRoom() + " person max");
                    binding.roomText.setError(null);
                    if (!service.roomIsFree(idRoom, timeStart, timeEnd)) {
                        binding.roomText.setError("Please choice another room, this room is not free");
                    }
                    if (participantsList.size() != 0) {
                        if (!service.roomToSmall(idRoom, participantsList.size())) {
                            binding.roomText.setError("This room is to small ");
                        }
                        long idRoomB = service.roomIsBetter(idRoom, room2.getMaximumParticipantRoom(), participantsList.size(), timeStart, timeEnd);
                        if (idRoomB != idRoom) {
                            for (Room room : mApiService.getRooms()) {
                                if (room.getIdRoom() == idRoomB) {
                                    room3 = room;
                                    break;
                                }
                            }
                            binding.roomText.setError("This room is better " + room3.getNameRoom());
                        }
                    }
                }
            }
        }
    }

    private void initVariables () {
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
        nbParticipants = 0;
        List<Room> rooms1 = mApiService.getRooms();

        Intent intent = getIntent();
        provenance = intent.getIntExtra("PROVENANCE", 0);
        Bundle args = intent.getBundleExtra("BUNDLE");
        meeting = (Meeting) args.getSerializable("ARRAYLIST2");
        meetings = (List<Meeting>) args.getSerializable("ARRAYLIST3");

        if (provenance == 4) {
            idMax = meeting.getId();
            idRoom = meeting.getIdRoom();
            timeStart = meeting.getTimeStart();
            dateStart = timeStart;
            timeEnd = meeting.getTimeEnd();
            dateEnd = timeEnd;
            meetingtitle = meeting.getTitle();
            meetingComment = meeting.getDescription();
            participantsList = meeting.getParticipants();
            nbParticipants = participantsList.size();
            durationNumber = service.dureeMinutes(timeStart, timeEnd);
            cal2 = service.duree(timeStart, timeEnd);
            dateStart = timeStart;
            dateEnd = timeEnd;
            selectedYear = dateStart.get(Calendar.YEAR);
            selectedMonth = dateStart.get(Calendar.MONTH);
            selectedDayOfMonth = dateStart.get(Calendar.DAY_OF_MONTH);
            selectedHourStartMeeting = timeStart.get(Calendar.HOUR);
            selectedMinuteStartMeeting = timeStart.get(Calendar.MINUTE);
            selectedHourNumber = cal2.get(Calendar.HOUR);
            selectedMinuteNumber = cal2.get(Calendar.MINUTE);
            for (Room room : mApiService.getRooms()) {
                if (room.getIdRoom() == idRoom) {
                    room2 = room;
                    break;
                }
            }
            textPart = "";
            for (Participant part : participantsList) {
                textPart = textPart + part.getName() + " \t " + part.getMailAddresses() + "\n";
            }
        }
    }

    private void affichage() {
        binding.textMeetingtitleEdit.setText(meetingtitle);
        binding.textMeetingCommentEdit.setText(meetingComment);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        binding.dateText.setText(format1.format(dateStart.getTime()));
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        binding.hourStartText.setText(format2.format(dateStart.getTime()));
        binding.hourEndMeetingText.setText(format2.format(dateEnd.getTime()));
        binding.durationText.setText(format2.format(cal2.getTime()));
        binding.participantNumber.setText(Integer.toString(nbParticipants));
        binding.textParticipant.setText(textPart);
        room3 = room2;
        binding.roomText.setText("N° " + room2.getNumberRoom() + " " + room2.getNameRoom() + "        étage " + room2.getStageRoom() + "      " + room2.getMaximumParticipantRoom() + " person max");
    }

}
