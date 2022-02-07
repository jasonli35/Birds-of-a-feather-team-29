package com.example.cse110_project.prevcourses.db;

import androidx.room.Embedded;
import androidx.room.Query;
import androidx.room.Relation;
import java.util.List;

import com.example.cse110_project.prevcourses.IStudent;

public class StudentWithCourses implements IStudent {
    @Embedded
    public Student student;

    @Relation(parentColumn = "id",
            entityColumn = "student_id",
            entity = Course.class,
            projection = {"text"})
    public List<String> courses;

    @Override
    public int getId(){
        return this.student.studentId;
    }

    @Override
    public String getName(){
        return this.student.name;
    }

    @Override
    public List<String> getCourses() {
        return this.courses;
    }
}
