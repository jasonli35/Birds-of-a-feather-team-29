package com.example.cse110_project.prevcourses.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM students")
    List<Student> getAll();

    @Query("SELECT * FROM students WHERE student_id=:id")
    Student get(int id);

    @Query("DELETE FROM students")
    void delete();

    @Insert
    void insert(Student student);
}
