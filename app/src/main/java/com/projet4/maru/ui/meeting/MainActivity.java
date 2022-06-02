package com.projet4.maru.ui.meeting;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.projet4.maru.R;
import com.projet4.maru.databinding.ActivityMainBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.service.MaReuApiService;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, MyMeetingRecyclerViewAdapter.OnMeetingClickListener {

    private MaReuApiService mApiService = DI.getStartListApiService();
    private ActivityMainBinding binding;
    private ArrayList<Meeting> mMeetingArrayList = new ArrayList<>();
    private Meeting meeting;
    private Spinner spinner;
    private int idRoom = -1;
    int provenance = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mMeetingArrayList.clear();
        if (idRoom == -1){
            mMeetingArrayList.addAll(mApiService.getMeetings());
        } else {
            mMeetingArrayList.addAll(mApiService.getMeetingsByRoom(idRoom));
        }
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    private void initData() {
        mApiService.deleteObsoleteMeetings();
        mMeetingArrayList = new ArrayList<>(mApiService.getMeetings());
    }

    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

//        MyMeetingRecyclerViewAdapter mAdapter = new MyMeetingRecyclerViewAdapter(meetings, meeting, this);
        MyMeetingRecyclerViewAdapter mAdapter = new MyMeetingRecyclerViewAdapter(mMeetingArrayList, meeting, this);
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filter_date:
                dateDialog();
                return true;
            case R.id.filter_room:
                selectRoom();
                return true;
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                idRoom = data.getExtras().getInt("MESSAGE");

            }
        }
    }

    private void selectRoom() {
        Intent idRoomIntent = new Intent (this, RoomspinnerActivity.class);
        startActivityForResult(idRoomIntent,0);
     }

    private void resetFilter() {
        idRoom=-1;
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mApiService.getMeetings());
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    private void dateDialog() {
        Calendar dateInit = GregorianCalendar.getInstance();
        int selectedYear = dateInit.get(Calendar.YEAR);
        int selectedMonth = dateInit.get(Calendar.MONTH);
        int selectedDayOfMonth = dateInit.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = GregorianCalendar.getInstance();
                cal.set(i, i1, i2);
                mMeetingArrayList.clear();
                mMeetingArrayList.addAll(mApiService.getMeetingsByDate(cal));
                binding.recyclerview.getAdapter().notifyDataSetChanged();
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
    }

    private void setButton() {
        binding.addMeeting.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == binding.addMeeting) {
            Intent intent = new Intent (this, AddMeetingActivity.class);
            Bundle args = new Bundle();
            long idMax = 0;
            long idRoom = 0;
            Calendar timeStart = GregorianCalendar.getInstance();
            timeStart.set(
                    timeStart.get(Calendar.YEAR),
                    timeStart.get(Calendar.MONTH),
                    timeStart.get(Calendar.DAY_OF_MONTH),
                    0,
                    0,
                    0);
            Calendar timeEnd = GregorianCalendar.getInstance();
            timeEnd.set(
                    timeEnd.get(Calendar.YEAR),
                    timeEnd.get(Calendar.MONTH),
                    timeEnd.get(Calendar.DAY_OF_MONTH),
                    0,
                    0,
                    0);
            String meetingtitle = "";
            String meetingComment = "";
            List<Participant> participantsList = new ArrayList();
            Meeting meeting = new Meeting (idMax, idRoom, timeStart, timeEnd, meetingtitle, meetingComment, participantsList ) ;
            intent.putExtra("PROVENANCE", 3);
            args.putSerializable("ARRAYLIST2", (Serializable) meeting);
            args.putSerializable("ARRAYLIST3", (Serializable) mMeetingArrayList);
            intent.putExtra("BUNDLE", args);
            startActivityForResult(intent,3);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onMeetingClick(Meeting meeting) {
        Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
        Bundle args = new Bundle();
        intent.putExtra("PROVENANCE", 4);
        args.putSerializable("ARRAYLIST2", (Serializable) meeting);
        args.putSerializable("ARRAYLIST3", (Serializable) mMeetingArrayList);
        intent.putExtra("BUNDLE", args);
        startActivityForResult(intent, 4);
    }
}