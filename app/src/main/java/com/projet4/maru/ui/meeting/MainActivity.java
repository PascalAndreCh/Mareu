package com.projet4.maru.ui.meeting;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.projet4.maru.R;
import com.projet4.maru.databinding.ActivityMainBinding;
import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.service.MaReuApiService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaReuApiService mApiService;
    private ActivityMainBinding binding;
    private ArrayList<Meeting> mMeetingArrayList = new ArrayList<>();
    private MaReuApiService mMeeting = (MaReuApiService) DI.getStartListApiService().getMeetings();

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

        MyMeetingRecyclerViewAdapter mAdapter = new MyMeetingRecyclerViewAdapter(mMeetingArrayList);
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
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetFilter() {
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll((Collection<? extends Meeting>) mMeeting);
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    private void dateDialog() {
        int selectedYear = 2022;
        int selectedMonth = 4;
        int selectedDayOfMonth = 1;


        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = GregorianCalendar.getInstance();
                cal.set(i, i1, i2);
                mMeetingArrayList.clear();
                mMeetingArrayList.addAll(mMeeting.getMeetingsByDate(cal));
                binding.recyclerview.getAdapter().notifyDataSetChanged();
            }

        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
    }

    private void initData() {
        mMeetingArrayList = new ArrayList<>(mMeeting.getMeetings());
    }

    private void setButton() {
        binding.addMeeting.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addMeeting) {
            startActivity(new Intent(this, AddMeetingActivity.class));
        }
    }
}
