package com.projet4.maru.ui.meeting;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projet4.maru.R;
import com.projet4.maru.databinding.ActivityRoomspinnerBinding;

public class RoomspinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ActivityRoomspinnerBinding binding1;
    private int room = 1;

    private void initUI() {
        binding1 = ActivityRoomspinnerBinding.inflate(getLayoutInflater());
        View view = binding1.getRoot();
        setContentView(view);
        getSupportActionBar().setTitle("choice Room");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initUI();

        showSpinner();
        binding1.roomSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(getApplicationContext(), " "+room, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent();
                    intent.putExtra("MESSAGE",room);
                    setResult(RESULT_OK,intent);
                    finish();
            }
        });

     }


     private void showSpinner () {
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.room_array, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         binding1.roomSpinner.setAdapter(adapter);
         binding1.roomSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
        room = i + 1;
        Toast.makeText(getApplicationContext(), "On item select " + room, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


}
