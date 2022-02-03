/*
 * Source(s):
 *
 * https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
 * https://developer.android.com/guide/topics/ui/controls/spinner
 * */

package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.PreviousCoursesDB;

public class MainCoursesActivity extends AppCompatActivity {
    /** Instance variables */
    private PreviousCoursesDB prevCourses;

    // FIXME delete
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_courses);
        setTitle("Birds of a Feather");

        // Initializing items for each dropdown menu
        initYearDropdown();
        initQuarterDropdown();
    }

    // FIXME -- WORK IN PROGRESS
//    public void addCoursesToDatabase() {
//        Bundle extras = getIntent().getExtras();
//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//
//        for (int i = 1; i <= 6; i++) {
//            if (extras.getString("subject", "default").equals("default")
//                    || extras.getString(Integer.toString(i), "default").equals("default")) {
//                continue;
//            }
//            editor.putString(extras.getString("subject"), extras.getString(Integer.toString(i)));
//        }
//    }

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

    // FIXME delete
    public void onClickTest(View view) {
        if (counter > 6) { Utilities.showAlert(this, "bruh"); counter = 1; return; }
        SharedPreferences pref = getSharedPreferences("userClassInfo", MODE_PRIVATE);
        TextView test = findViewById(R.id.test_textview);
        test.setText(pref.getString(Integer.toString(counter), "not found"));
        counter++;
    }
}