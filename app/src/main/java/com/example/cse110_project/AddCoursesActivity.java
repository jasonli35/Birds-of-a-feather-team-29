package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddCoursesActivity extends AppCompatActivity {
    /** Constants */
    private final String TOO_MANY_COURSES_WARNING = "Exceeding maximum of 6 courses per quarter. " +
            "Please press Back to continue.";
    private final String SPACE = " ";
    private final String KEY_SUBJECT = "subject";
    private final String VALUE_COURSE = "course";
    private final int COURSE_COUNTER_MAX = 5;
    private final int LIST_SIZE = 6;

    /** Instance variables */
    List<String> enteredCourses = new ArrayList<>(LIST_SIZE);
    private int courseCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_add);
        displayInitPrevCourse();
    }

    public void onEnterClicked(View view) {
        if (this.courseCounter == COURSE_COUNTER_MAX) {
            displayTooManyCoursesWarning();
            return;
        }
        displayEnteredPrevCourses(this.courseCounter);
        this.courseCounter++;
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MainCoursesActivity.class);
        for (int i = 0; i < this.enteredCourses.size(); i++) {
            intent.putExtra(Integer.toString(i + 1), this.enteredCourses.get(i));
        }
        intent.putExtra("subject", getIntent().getExtras().getString("subject"));
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

    public void displayEnteredPrevCourses(int courseIndex) {
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
        Utilities.showAlert(this, TOO_MANY_COURSES_WARNING);
    }
}