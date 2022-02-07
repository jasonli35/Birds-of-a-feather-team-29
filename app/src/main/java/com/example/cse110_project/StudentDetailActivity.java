package com.example.cse110_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.cse110_project.prevcourses.IStudent;
import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.Course;

public class StudentDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private IStudent student;

    private RecyclerView coursesRecyclerView;
    private RecyclerView.LayoutManager coursesLayoutManager;
    private CoursesViewAdapter coursesViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        //TextView studentCoursesView = findViewById(R.id.student_detail_courses);

        Intent intent = getIntent();
        int studentId = intent.getIntExtra("student_id", 0);

        db = AppDatabase.singleton(this);
        student = db.studentWithCoursesDao().get(studentId);
        List<Course> courses = db.coursesDao().getForStudent(studentId);

        setTitle(student.getName());

        coursesRecyclerView = findViewById(R.id.courses_view);
        coursesLayoutManager = new LinearLayoutManager(this);
        coursesRecyclerView.setLayoutManager(coursesLayoutManager);

        coursesViewAdapter = new CoursesViewAdapter(courses, (course) -> {
            db.coursesDao().delete(course);
        });
        coursesRecyclerView.setAdapter(coursesViewAdapter);
    }

    public void onGoBackClicked(View view) {
        finish();
    }

}