package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FindAClub extends AppCompatActivity {

    private Spinner schoolSpinner;
    private TextView schoolText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_club);

        schoolSpinner = findViewById(R.id.schoolSpinner);
        schoolText = findViewById(R.id.schoolText);

        List<String> school = new ArrayList<>();
        school.add("- choose your school -");
        school.add("MAMS");
        school.add("Doherty");
        school.add("South");

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, school);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);

        schoolSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String yourSchool = schoolSpinner.getSelectedItem().toString();
                if(!yourSchool.equals("- choose your school -")){
                    schoolText.setText("You choose" + yourSchool);

                }
            }
        });


    }
}
