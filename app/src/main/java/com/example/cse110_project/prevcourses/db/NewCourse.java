package com.example.cse110_project.prevcourses.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "newCourse")
public class NewCourse {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "new_course_id")
    public int courseId = 0;

    @ColumnInfo(name = "new_student_id")
    public int studentId;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "quarter")
    public String quarter;

    @ColumnInfo(name = "course")
    public String course;

    public NewCourse(int studentId, String year, String quarter, String course) {
        this.studentId = studentId;
        this.year = year;
        this.quarter = quarter;
        this.course = course;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getYear() { return year; }

    public String getQuarter() { return quarter; }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

}
