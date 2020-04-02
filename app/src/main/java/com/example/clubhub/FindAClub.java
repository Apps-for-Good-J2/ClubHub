package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FindAClub extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private Spinner schoolSpinner;
    private TextView schoolText;
    List<String> school = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_club);

        schoolSpinner = findViewById(R.id.schoolSpinner);
        schoolText = findViewById(R.id.schoolText);


        school.add("- choose your school -");
        school.add("MAMS");
        school.add("Doherty");
        school.add("South");

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, school);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);


    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),school.get(position) , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
