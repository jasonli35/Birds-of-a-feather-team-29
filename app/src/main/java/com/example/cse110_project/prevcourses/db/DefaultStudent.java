package com.example.cse110_project.prevcourses.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "students")
public class DefaultStudent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    public int studentId = 0;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "encountered")
    public boolean encountered;

    public DefaultStudent(String name) {
        this.name = name;
        this.encountered = false;
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

    public boolean getEncountered() { return encountered; }

    public void setEncounteredTrue() { encountered = true; }
}
