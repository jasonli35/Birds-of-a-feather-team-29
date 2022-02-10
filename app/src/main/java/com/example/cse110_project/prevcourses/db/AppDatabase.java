package com.example.cse110_project.prevcourses.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class, Course.class, NewStudent.class, NewCourse.class}, version = 2)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singletonInstance;

    public static AppDatabase singleton(Context context){
        if(singletonInstance == null){
            singletonInstance = Room.databaseBuilder(context, AppDatabase.class, "students.db")
                    .createFromAsset("starter-students.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return singletonInstance;
    }

    public static void useTestSingleton(Context context) {
        singletonInstance = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    public abstract StudentDao studentDao();
    public abstract CourseDao courseDao();
    public abstract NewStudentDao newStudentDao();
    public abstract NewCourseDao newCourseDao();

    public AppDatabase getSingletonInstance() {
        return singletonInstance;
    }
}

/**
 * Sources:
 *
 * Understanding what happens when Room database is changed -
 *
 * https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
 * */