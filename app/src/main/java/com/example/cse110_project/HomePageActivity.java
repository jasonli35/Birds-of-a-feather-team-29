package com.example.cse110_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.Course;
import com.example.cse110_project.prevcourses.db.Student;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomePageActivity extends AppCompatActivity{
    private AppDatabase db;
    private Student student;
    protected RecyclerView studentsRecyclerView;
    protected RecyclerView.LayoutManager studentsLayoutManager;
    protected StudentsViewAdapter studentsViewAdapter;
    
    private static final String TAG = "Project-Nearby";
    private static final String MESSAGE =
            "Amy,2020FallCSE,30,12,15L,2020SpringCSE,100,101";
    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        db = AppDatabase.singleton(getApplicationContext());
        List<Student> students = db.studentDao().getAll();

        studentsRecyclerView = findViewById(R.id.students_view);

        studentsLayoutManager = new LinearLayoutManager(this);
        studentsRecyclerView.setLayoutManager(studentsLayoutManager);

        studentsViewAdapter = new StudentsViewAdapter(students);
        studentsRecyclerView.setAdapter(studentsViewAdapter);
    }

    public void onClickStart(View view) {
        TextView topLeftButton = findViewById(R.id.start_button);
        if (topLeftButton.getText().toString().equals("Start")) { topLeftButton.setText("Stop"); }
        else { topLeftButton.setText("Start"); }


    }

    // FIXME add class for storing courses of a user
    public void addCoursesTest() {

    }

    //add student to both database and recycler view
    public void addStudent(String studentName) {
//        //Student newStudent = new Student();
//
//        newStudent.setName(studentName);
//        db.studentDao().insert(newStudent);
//
//        studentsViewAdapter.addStudent(newStudent);
    }

    //compare courses student entered to the messages received
    public ArrayList<String> compareCourses(String message) {
        ArrayList<String> ret = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_MAIN_USER_CLASS_INFO_DB, MODE_PRIVATE);
        String[] courses = message.split(",");
        Map<String, ?> data = sp.getAll();
        if(courses.length == 0 || courses.length == 1){
            return null;
        }
        ret.add(courses[0]);
        ArrayList<String> courseList = new ArrayList<>();
        for (int i = 1; i<courses.length;i++){
            if(courses[i].length()>5){
                for (int j = i+1; j<courses.length;j++){
                    courseList.add(courses[i]+courses[j]);
                    if(courses[j].length()>5){
                        break;
                    }
                }
            }
        }
        for (String key : data.keySet()) {
            Set<String> values = sp.getStringSet(key, null);
            for (String value : values){
                for(int k = 0; k<courseList.size(); k++){
                    if((key+value).equals(courseList.get(k))){
                        ret.add(key+value);
                    }
                }
            }
        }
        return ret;
    }
}