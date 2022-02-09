package com.example.cse110_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.Course;
import com.example.cse110_project.prevcourses.db.Student;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomePageActivity extends AppCompatActivity{
    private AppDatabase db;

    protected RecyclerView studentsRecyclerView;
    protected RecyclerView.LayoutManager studentsLayoutManager;
    protected StudentsViewAdapter studentsViewAdapter;

    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        compareCourses();

        db = AppDatabase.singleton(getApplicationContext());
        List<Student> students = db.studentDao().getAll();

        studentsRecyclerView = findViewById(R.id.students_view);

        studentsLayoutManager = new LinearLayoutManager(this);
        studentsRecyclerView.setLayoutManager(studentsLayoutManager);

        studentsViewAdapter = new StudentsViewAdapter(students);
        studentsRecyclerView.setAdapter(studentsViewAdapter);
    }

    public void onClickStart(View view) {
        TextView topLeftButton = findViewById(R.id.start_button);
        if (topLeftButton.getText().toString().equals("Start")) { topLeftButton.setText("Stop"); }
        else { topLeftButton.setText("Start"); }
    }

    public void compareCourses() {
        ArrayList<String> ret = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_MAIN_USER_CLASS_INFO_DB, MODE_PRIVATE);
        Map<String, ?> userCoursesMap = sp.getAll();
        Set<String> keys = userCoursesMap.keySet();
        Object[] keysArr = keys.toArray();


        db = AppDatabase.singleton(getApplicationContext());
        List<Course> courseList;
        Set<String> userCourses;
        Student currStudent;
        int id;

        for (int i = 0; i < keysArr.length; i++) {
            userCourses = (Set<String>) userCoursesMap.get(keysArr[i]);
            courseList = db.courseDao().getAll();

            for (String course : userCourses) {
                for (Course uCourse : courseList) {
                    if (!course.equals(uCourse.getText())) {
                        id = uCourse.getStudentId();
                        currStudent = db.studentDao().get(id);

                        db.courseDao().delete(uCourse);

                        if (db.courseDao().getForStudent(id) == null
                                || db.courseDao().getForStudent(id).size() == 0) {
                            db.studentDao().delete(currStudent);
                        }
                    }
                }
            }
        }
    }
}