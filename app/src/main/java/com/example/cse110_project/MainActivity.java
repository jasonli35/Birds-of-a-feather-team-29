/*
 * Source(s) used:
 *
 * Automated testing on GitHub -
 *  1) Title: gradlew: Permission Denied
 *     Link: https://stackoverflow.com/questions/17668265/gradlew-permission-denied
 *     Date: 2/4/22
 *     Source used for...: Understanding an error on GitHub actions
 *  2) Lab 2 (page 11)
 *     Link: https://docs.google.com/document/d/19VngfyPVahd7LdmW2fyWTNqWI67Sag_ylnP5Ja2wtBo/edit?usp=sharing
 *     Date: 2/4/22
 *     Source used for...: Reference on how to write a .yml file for automated testing
 * */

package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.DefaultCourse;
import com.example.cse110_project.prevcourses.db.DefaultStudent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Birds of a Feather v0.0.1");

        clearUserClassInfo();
        populateDatabase();
    }

    public void onMockFunctionalityClicked(View view) {}

    public void onStartAppClicked(View view) {
        Intent intent = new Intent(this, EnterNameActivity.class);
        startActivity(intent);
    }

    // FIXME: temporary -- use a separate Class instead?
    private void populateDatabase() {
        DefaultStudent[] defaultStudentList = {
                new DefaultStudent("Steel"),
                new DefaultStudent("Sandy"),
                new DefaultStudent("Aiko")
        };

        AppDatabase db = AppDatabase.singleton(getApplicationContext());
        db.studentDao().delete();
        db.courseDao().delete();

        for (DefaultStudent dS : defaultStudentList) { db.studentDao().insert(dS); }

        List<DefaultStudent> defStudentsList = db.studentDao().getAll();

        DefaultCourse[] defaultCourseList = {
                new DefaultCourse(defStudentsList.get(0).getStudentId(), "2017",
                        "Fall", "CSE 11"),
                new DefaultCourse(defStudentsList.get(0).getStudentId(), "2017",
                        "Fall","CSE 12"),
                new DefaultCourse(defStudentsList.get(0).getStudentId(), "2017",
                        "Fall","CSE 21"),
                new DefaultCourse(defStudentsList.get(0).getStudentId(), "2019",
                        "Spring","CSE 100"),
                new DefaultCourse(defStudentsList.get(0).getStudentId(), "2019",
                        "Spring","CSE 140"),
                new DefaultCourse(defStudentsList.get(0).getStudentId(), "2019",
                        "Spring","CSE 105"),

                new DefaultCourse(defStudentsList.get(1).getStudentId(), "2018",
                        "Winter","CSE 11"),
                new DefaultCourse(defStudentsList.get(1).getStudentId(), "2018",
                        "Winter","CSE 12"),
                new DefaultCourse(defStudentsList.get(1).getStudentId(), "2018",
                        "Winter","CSE 21"),
                new DefaultCourse(defStudentsList.get(1).getStudentId(), "2019",
                        "Fall", "CSE 100"),

                new DefaultCourse(defStudentsList.get(2).getStudentId(), "2018",
                        "Spring","CSE 15L"),
                new DefaultCourse(defStudentsList.get(2).getStudentId(), "2020",
                        "Summer Session I","CSE 191"),
                new DefaultCourse(defStudentsList.get(2).getStudentId(), "2020",
                        "Fall","CSE 142"),
                new DefaultCourse(defStudentsList.get(2).getStudentId(), "2020",
                        "Fall","CSE 112"),
                new DefaultCourse(defStudentsList.get(2).getStudentId(), "2020",
                        "Fall","CSE 167")
        };

        for (DefaultCourse defaultCourse : defaultCourseList) { db.courseDao().insert(defaultCourse); }
    }

    private void clearUserClassInfo() {
        SharedPreferences currEnteredClassesSP = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        SharedPreferences.Editor currEnteredClassesEditor = currEnteredClassesSP.edit();
        currEnteredClassesEditor.clear();
        currEnteredClassesEditor.apply();

        SharedPreferences mainUserClassInfoSP = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        SharedPreferences.Editor mainUserClassInfoEditor = mainUserClassInfoSP.edit();
        mainUserClassInfoEditor.clear();
        mainUserClassInfoEditor.apply();
    }
}