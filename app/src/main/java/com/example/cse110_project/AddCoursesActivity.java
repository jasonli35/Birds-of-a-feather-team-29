package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class AddCoursesActivity extends AppCompatActivity {
    /** Constants */
    private final String TOO_MANY_COURSES_WARNING = "Exceeding maximum of 6 courses per quarter. " +
            "Please press Back to continue.";
    private final String NO_COURSE_ENTERED = "Please enter a course number. If finished, press Back" +
            " to continue.";
    private final String SPACE = " ";
    private final String KEY_SUBJECT = "subject";
    private final String VALUE_COURSE = "initCourseNumber";
    private final int COURSE_COUNTER_MAX = 5;
    private final int LIST_SIZE = 6;

    /** Instance variables */
    List<String> enteredCourses = new ArrayList<>(LIST_SIZE);
    private int courseCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        displayInitPrevCourse();
    }

    public void onEnterClicked(View view) {
        TextView enteredCourseNumber = findViewById(R.id.course_number_textview);
        if (this.courseCounter == COURSE_COUNTER_MAX) {
            displayTooManyCoursesWarning();
            return;
        // FIXME: could be a unit test of some sort testing whether or not the user entered a class
        } else if (enteredCourseNumber.getText().toString().equals("")) {
            Utilities.showAlert(this, "Warning!", NO_COURSE_ENTERED);
            return;
        }
        displayEnteredPrevCourse(this.courseCounter);
        this.courseCounter++;
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MainCoursesActivity.class);

        // FIXME potential
        SharedPreferences preferences = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Bundle extras = getIntent().getExtras();
        HashSet<String> set = new HashSet<>();
        for (String courses : this.enteredCourses) {
            set.add(courses);
        }
        editor.putStringSet(extras.getString(KEY_SUBJECT), set);
        editor.apply();
        intent.putExtra("keySubject", extras.getString(KEY_SUBJECT));

        startActivity(intent);
    }

    public void displayInitPrevCourse() {
        TextView firstCourse = findViewById(R.id.prev_course_one_textview);
        Bundle extras = getIntent().getExtras();
        String fullCourseName = extras.getString(KEY_SUBJECT)
                + SPACE + extras.getString(VALUE_COURSE);
        firstCourse.setText(fullCourseName);
        addToList(extras.getString(VALUE_COURSE));
    }

    public void displayEnteredPrevCourse(int courseIndex) {
        TextView[] courseLayouts = {findViewById(R.id.prev_course_two_textview),
                findViewById(R.id.prev_course_three_textview),
                findViewById(R.id.prev_course_four_textview),
                findViewById(R.id.prev_course_five_textview),
                findViewById(R.id.prev_course_six_textview)};
        TextView courseNumber = findViewById(R.id.course_number_textview);
        Bundle extras = getIntent().getExtras();
        String fullCourseName = extras.getString(KEY_SUBJECT) + SPACE
                + courseNumber.getText().toString();
        courseLayouts[courseIndex].setText(fullCourseName);
        addToList(courseNumber.getText().toString());
    }

    public void addToList(String courseNumber) {
        enteredCourses.add(courseNumber);
    }

    public void displayTooManyCoursesWarning() {
        Utilities.showAlert(this, "Alert!", TOO_MANY_COURSES_WARNING);
    }
}