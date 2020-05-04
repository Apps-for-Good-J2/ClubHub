package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Spinner;
import android.os.Bundle;

import java.util.ArrayList;

public class meetingsThisWeek extends AppCompatActivity {
    private Spinner chooseDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_this_week);
    }


}
