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

    @ColumnInfo(name = "text")
    public String text;

    public NewCourse(int studentId, String text){
        this.studentId = studentId;
        this.text = text;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
