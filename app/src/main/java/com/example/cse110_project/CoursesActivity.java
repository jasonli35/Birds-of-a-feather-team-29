package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        setTitle("Birds of a Feather");

        // FIXME
//        Spinner spinnerLanguages = findViewById(R.id.year_dropdown_container);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.academic_years, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinnerLanguages.setAdapter(adapter);
    }


}