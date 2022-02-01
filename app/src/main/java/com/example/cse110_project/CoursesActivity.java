/*
* Source(s):
*
* https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
* https://developer.android.com/guide/topics/ui/controls/spinner
* */

package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        setTitle("Birds of a Feather");

        // Initializing items for each dropdown menu
        initYearDropdown();
        initQuarterDropdown();
    }

    public void initYearDropdown() {
        Spinner yearDropdown = findViewById(R.id.year_dropdown_container);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.academic_years, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        yearDropdown.setAdapter(adapter);
    }

    public void initQuarterDropdown() {
        Spinner quarterDropdown = findViewById(R.id.quarter_dropdown_container);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.academic_quarters, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        quarterDropdown.setAdapter(adapter);
    }
}