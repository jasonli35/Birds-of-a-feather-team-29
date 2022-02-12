package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.BoFCourse;
import com.example.cse110_project.prevcourses.db.BoFCourseDao;
import com.example.cse110_project.prevcourses.db.BoFStudent;
import com.example.cse110_project.prevcourses.db.DefaultCourse;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomePageActivity extends AppCompatActivity{
    private AppDatabase db;

    protected RecyclerView studentsRecyclerView;
    protected RecyclerView.LayoutManager studentsLayoutManager;
    protected BoFStudentViewAdapter studentsViewAdapter;

    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("Birds of a Feather v0.0.1");

        compareUserCoursesWithStudents();

        db = AppDatabase.singleton(getApplicationContext());
        List<BoFStudent> students = db.BoFStudentDao().getAll();

        studentsRecyclerView = findViewById(R.id.students_view);

        studentsLayoutManager = new LinearLayoutManager(this);
        studentsRecyclerView.setLayoutManager(studentsLayoutManager);

        studentsViewAdapter = new BoFStudentViewAdapter(students, db.BoFCourseDao());
        studentsRecyclerView.setAdapter(studentsViewAdapter);
    }

    // FIXME: fix start/stop button
    public void onClickStart(View view) {
        TextView topLeftButton = findViewById(R.id.start_button);
        if (topLeftButton.getText().toString().equals("Start")) { topLeftButton.setText("Stop"); }
        else { topLeftButton.setText("Start"); }
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(this, AddCoursesMainActivity.class);
        startActivity(intent);
    }

    public void compareUserCoursesWithStudents() {
        db = AppDatabase.singleton(getApplicationContext());
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_MAIN_USER_CLASS_INFO_DB, MODE_PRIVATE);
        Map<String, ?> userCoursesMap = sp.getAll();
        Object[] keysArr = userCoursesMap.keySet().toArray();
        List<DefaultCourse> defaultCourseList;
        Set<String> userCourses;
        String[] studentCourseSplit, userKeySplit;
        String year, quarter;
        boolean skipCourse;
        int studentId;

        // Cross-checks the classes entered by the user with the students pre-populated into the
        // database
        for (Object o : keysArr) {
            userKeySplit = ((String)o).split(",");
            userCourses = (Set<String>) userCoursesMap.get(o);
            defaultCourseList = db.courseDao().getAll();

            // Iterates through the course numbers entered by the user for a specific year, quarter,
            // and course
            for (String uC : userCourses) {
                // Iterates through all the remaining courses in the pre-populated database
                for (DefaultCourse cL : defaultCourseList) {
                    skipCourse = false;
                    studentCourseSplit = cL.getCourse().split(" ");
                    year = cL.getYear();
                    quarter = cL.getQuarter();

                    if (cL.getYear().equals(userKeySplit[0]) && cL.getQuarter().equals(userKeySplit[1])
                            && studentCourseSplit[0].equals(userKeySplit[2])
                            && studentCourseSplit[1].equals(uC)) {
                        studentId = cL.getStudentId();

                        // Checks whether or not the student already has the course associated with them
                        // in the database
                        if (db.studentDao().get(studentId).getEncountered()) {
                            List<BoFCourse> studentCourses = db.BoFCourseDao().getForStudent(studentId);
                            for (BoFCourse course : studentCourses) {
                                if (course.getCourse().equals(cL.getCourse())) {
                                    skipCourse = true;
                                    break;
                                }
                            }
                            if (skipCourse) { continue; }
                        }

                        // If the student has not been added to the BoF database, then we add the
                        // student to the BoF database
                        if (!(db.studentDao().get(studentId).getEncountered())) {
                            BoFStudent ns = new BoFStudent(studentId, db.studentDao().get(studentId).getName());
                            db.BoFStudentDao().insert(ns);
                            db.studentDao().updateEncountered(true, studentId);
                        }

                        studentId = db.BoFStudentDao().getBasedOnPrevId(studentId).getStudentId();
                        BoFCourse nc = new BoFCourse(studentId, year, quarter, cL.getCourse());
                        db.BoFCourseDao().insert(nc);
                    }
                }
            }
        }
    }
}