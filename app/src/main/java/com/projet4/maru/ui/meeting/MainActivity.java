package com.projet4.maru.ui.meeting;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.projet4.maru.R;
import com.projet4.maru.di.DI;
import com.projet4.maru.service.MaReuApiService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MaReuApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DI.getStartListApiService(); // création des listes du jeu d'essai ???
    }

    @Override
    public void onClick(View view) {

    }
}
