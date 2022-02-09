package com.example.cse110_project.prevcourses.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface NewCourseDao {
    @Transaction
    @Query("SELECT * FROM newCourse where new_student_id=:studentId")
    List<NewCourse> getForStudent(int studentId);

    @Query("SELECT * FROM newCourse")
    List<NewCourse> getAll();

    @Query("SELECT * FROM newCourse WHERE new_student_id=:id")
    NewCourse get(int id);

    @Query("DELETE FROM newCourse")
    void delete();

//    @Query("SELECT COUNT(*) from courses")
//    int count();
//

    @Insert
    void insert(NewCourse course);

    @Delete
    void delete(NewCourse course);
}
