/*
 * Source(s):
 *
 * https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
 * https://developer.android.com/guide/topics/ui/controls/spinner
 * */

package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.PreviousCoursesDB;

public class MainCoursesActivity extends AppCompatActivity {
    /** Instance variables */
    private PreviousCoursesDB prevCourses;

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

    public void onClickEnter(View view) {
        Intent intent = new Intent(this, AddCoursesActivity.class);
        TextView subject = findViewById(R.id.enter_subject_textview);
        TextView course = findViewById(R.id.enter_course_textview);
        intent.putExtra("subject", subject.getText().toString());
        intent.putExtra("course", course.getText().toString());
        startActivity(intent);
    }
}