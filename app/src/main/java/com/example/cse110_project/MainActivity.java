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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.Course;
import com.example.cse110_project.prevcourses.db.Student;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Birds of a Feather");
        clearUserClassInfo();
        populateDatabase();
    }

    // FIXME: temporary -- use a separate Class instead?
    public void populateDatabase() {
        Student[] studentList = {
                new Student("Steel"),
                new Student("Sandy"),
                new Student("Aiko")
        };

        AppDatabase db = AppDatabase.singleton(getApplicationContext());

        db.studentDao().delete();
        db.courseDao().delete();

        for (Student student : studentList) { db.studentDao().insert(student); }

        List<Student> defaultStudents = db.studentDao().getAll();

        Course[] courseList = {
                new Course(defaultStudents.get(0).getStudentId(), "2017", "Fall", "CSE 11"),
                new Course(defaultStudents.get(0).getStudentId(), "2017", "Fall","CSE 12"),
                new Course(defaultStudents.get(0).getStudentId(), "2017", "Fall","CSE 21"),
                new Course(defaultStudents.get(0).getStudentId(), "2019", "Spring","CSE 100"),
                new Course(defaultStudents.get(0).getStudentId(), "2019", "Spring","CSE 140"),
                new Course(defaultStudents.get(0).getStudentId(), "2019", "Spring","CSE 105"),

                new Course(defaultStudents.get(1).getStudentId(), "2018", "Winter","CSE 11"),
                new Course(defaultStudents.get(1).getStudentId(), "2018", "Winter","CSE 12"),
                new Course(defaultStudents.get(1).getStudentId(), "2018", "Winter","CSE 21"),
                new Course(defaultStudents.get(1).getStudentId(), "2019", "Fall", "CSE 100"),

                new Course(defaultStudents.get(2).getStudentId(), "2020", "Summer Session I","CSE 191"),
                new Course(defaultStudents.get(2).getStudentId(), "2020", "Fall","CSE 142"),
                new Course(defaultStudents.get(2).getStudentId(), "2020", "Fall","CSE 112"),
                new Course(defaultStudents.get(2).getStudentId(), "2020", "Fall","CSE 167")
        };

        for (Course course : courseList) { db.courseDao().insert(course); }

        // FIXME -- test code

        List<Student> sl = db.studentDao().getAll();
        for (Student s : sl) {
            List<Course> cl = db.courseDao().getForStudent(s.getStudentId());
            System.out.println(s.getName() + " " + s.getStudentId());
            for (Course c : cl) {
                System.out.println(c.getYear() + " " + c.getQuarter() + " " + c.getCourse());
            }
            //s.setEncounteredTrue();
            //System.out.println("Now..." + s.getEncountered());
        }
        System.out.println("---------------------");

//        for (Course c : db.courseDao().getAll()) {
//            Student s = db.studentDao().get(c.getStudentId());
//
//            System.out.println(s.getName());
//            System.out.println("BEFORE: " + s.getEncountered());
//            System.out.println("BEFORE: " + db.studentDao().get(s.getStudentId()).getEncountered());
//
//            db.studentDao().updateEncountered(true, s.getStudentId());
//
//            System.out.println("AFTER: " + s.getEncountered());
//            System.out.println("AFTER: " + db.studentDao().get(s.getStudentId()).getEncountered());
//        }
    }

    public void clearUserClassInfo() {
        SharedPreferences currEnteredClassesSP = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        SharedPreferences.Editor currEnteredClassesEditor = currEnteredClassesSP.edit();
        currEnteredClassesEditor.clear();
        currEnteredClassesEditor.apply();

        SharedPreferences mainUserClassInfoSP = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        SharedPreferences.Editor mainUserClassInfoEditor = mainUserClassInfoSP.edit();
        mainUserClassInfoEditor.clear();
        mainUserClassInfoEditor.apply();

        SharedPreferences completeKeysSP = getSharedPreferences("allCompleteKeys", MODE_PRIVATE);
        SharedPreferences.Editor completeKeysSPEditor = completeKeysSP.edit();
        completeKeysSPEditor.clear();
        completeKeysSPEditor.apply();
    }

    // FIXME: delete
    public void testButtonClick(View view) {
        Intent intent = new Intent(this, EnterNameActivity.class);
        startActivity(intent);
    }

    // FIXME: delete
    public void goToCoursesActivityPage(View view) {
        Intent intent = new Intent(this, MainCoursesActivity.class);
        //intent.putExtra("poop", "poop");
        startActivity(intent);
    }
}