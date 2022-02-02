package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddPrevCoursesActivity extends AppCompatActivity {
    /** Constants */
    private final String tooManyCoursesWarning = "Exceeding maximum of 6 courses per quarter. " +
            "Please press Back to continue.";

    /** Instance variables */
    private int courseCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_add);
        displayInitPrevCourse();
    }

    public void displayTooManyCoursesWarning() {
        Utilities.showAlert(this, tooManyCoursesWarning);
    }

    public void onEnterClicked(View view) {
        if (this.courseCounter == 5) {
            displayTooManyCoursesWarning();
            return;
        }
        displayEnteredPrevCourses(this.courseCounter);
        this.courseCounter++;
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(this, MainPrevCoursesActivity.class);
        startActivity(intent);
    }

    public void displayInitPrevCourse() {
        TextView firstCourse = findViewById(R.id.prev_course_one_textview);
        Bundle extras = getIntent().getExtras();
        String fullCourseName = extras.getString("subject")
                + " " + extras.getString("course");
        firstCourse.setText(fullCourseName);
    }

    public void displayEnteredPrevCourses(int courseIndex) {
        TextView courseLayouts[] = {findViewById(R.id.prev_course_two_textview),
                findViewById(R.id.prev_course_three_textview),
                findViewById(R.id.prev_course_four_textview),
                findViewById(R.id.prev_course_five_textview),
                findViewById(R.id.prev_course_six_textview)};
        TextView courseNumber = findViewById(R.id.course_number_textview);
        Bundle extras = getIntent().getExtras();
        String fullCourseName = extras.getString("subject") + " "
                + courseNumber.getText().toString();
        courseLayouts[courseIndex].setText(fullCourseName);
    }
}