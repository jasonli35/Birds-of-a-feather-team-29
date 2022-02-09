package com.example.cse110_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.BoFCourse;
import com.example.cse110_project.prevcourses.db.DefaultStudent;

public class StudentDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private DefaultStudent defaultStudent;

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager coursesLayoutManager;
    private BoFCourseViewAdapter coursesViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Intent intent = getIntent();
        int studentId = intent.getIntExtra("new_student_id", 0);

        db = AppDatabase.singleton(this);
        defaultStudent = db.studentDao().get(studentId);
        List<BoFCourse> courses = db.newCourseDao().getForStudent(studentId);

        setTitle(defaultStudent.getName());

        coursesRecyclerView = findViewById(R.id.courses_view);
        coursesLayoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);

        coursesViewAdapter = new BoFCourseViewAdapter(courses);
        coursesRecyclerView.setAdapter(coursesViewAdapter);
    }

    public void onGoBackClicked(View view) {
        finish();
    }
}