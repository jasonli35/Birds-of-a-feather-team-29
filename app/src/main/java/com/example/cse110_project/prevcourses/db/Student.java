package com.example.cse110_project.prevcourses.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    public int studentId;

    @ColumnInfo(name = "name")
    public String name;

    public Student(String name) {
        this.name = name;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
