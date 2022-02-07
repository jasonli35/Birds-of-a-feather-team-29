package com.example.cse110_project.prevcourses.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int courseId;

    @ColumnInfo(name = "student_id")
    public int studentId;

    @ColumnInfo(name = "text")
    public String text;

    public Course(int courseId, int studentId, String text){
        this.courseId = courseId;
        this.studentId = studentId;
        this.text = text;
    }
}
