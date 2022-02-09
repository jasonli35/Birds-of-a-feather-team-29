package com.example.cse110_project.prevcourses.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewStudentDao {
    @Query("SELECT * FROM newStudents")
    List<NewStudent> getAll();

    @Query("SELECT * FROM newStudents WHERE new_student_id=:id")
    NewStudent get(int id);

    @Query("DELETE FROM newStudents")
    void delete();

    @Insert
    void insert(NewStudent student);

    @Delete
    void delete(NewStudent student);
}
