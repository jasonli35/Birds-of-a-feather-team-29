package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.Course;
import com.example.cse110_project.prevcourses.db.Student;

import java.util.List;

public class PreStoreStudentInfo extends AppCompatActivity {
    private static final Student[] studentList = {
            new Student("Steel"),
            new Student("Sandy"),
            new Student("Aiko"),
    };

    private static final Course[] courseList = {
            new Course(studentList[0].getStudentId(), "CSE 11"),
            new Course(studentList[0].getStudentId(), "CSE 12"),
            new Course(studentList[0].getStudentId(), "CSE 21"),
            new Course(studentList[0].getStudentId(), "CSE 100"),
            new Course(studentList[0].getStudentId(), "CSE 140"),
            new Course(studentList[0].getStudentId(), "CSE 105"),

            new Course(studentList[1].getStudentId(), "CSE 11"),
            new Course(studentList[1].getStudentId(), "CSE 12"),
            new Course(studentList[1].getStudentId(), "CSE 21"),
            new Course(studentList[1].getStudentId(), "CSE 100"),

            new Course(studentList[2].getStudentId(), "CSE 191"),
            new Course(studentList[2].getStudentId(), "CSE 142"),
            new Course(studentList[2].getStudentId(), "CSE 112"),
            new Course(studentList[2].getStudentId(), "CSE 167"),
    };

    AppDatabase db;

    public PreStoreStudentInfo() {}

    public void storeStudentInfo() {
        db = AppDatabase.singleton(this);

        for (Student student : studentList) { db.studentDao().insert(student); }
        for (Course course : courseList) { db.courseDao().insert(course); }
    }
}
