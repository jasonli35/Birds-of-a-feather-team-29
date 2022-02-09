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
import com.example.cse110_project.prevcourses.db.NewCourse;
import com.example.cse110_project.prevcourses.db.NewStudent;
import com.example.cse110_project.prevcourses.db.NewStudentDao;
import com.example.cse110_project.prevcourses.db.Student;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomePageActivity extends AppCompatActivity{
    private AppDatabase db;

    protected RecyclerView studentsRecyclerView;
    protected RecyclerView.LayoutManager studentsLayoutManager;
    protected StudentsViewAdapter studentsViewAdapter;

    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        compareUserCoursesWithStudents();

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

    public void compareUserCoursesWithStudents() {
        ArrayList<String> ret = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_MAIN_USER_CLASS_INFO_DB, MODE_PRIVATE);
        Map<String, ?> userCoursesMap = sp.getAll();
        Set<String> keys = userCoursesMap.keySet();
        Object[] keysArr = keys.toArray();

        // FIXME ---
//        System.out.println("-----------");
//
//        System.out.println("Number of keys: " + keysArr.length);
//        for (Object o : keysArr) {
//            System.out.println(o);
//
//            Set<String> s = (Set<String>) userCoursesMap.get(o);
//            for (String num : s) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("-----------");

        // FIXME: for comparing with database
//        db = AppDatabase.singleton(getApplicationContext());
//        List<Course> c = db.courseDao().getForStudent(0);
//        System.out.println(c.size());
//        String[] tokens;
//        for (Course cc : c) {
//            tokens = cc.getText().split(" ");
//            System.out.println(tokens[1]);
//        }

        // FIXME: checking if course is being deleted
//        db = AppDatabase.singleton(getApplicationContext());
//        List<Course> cl = db.courseDao().getAll();
//        for (Course c : cl) {
//            System.out.println(c.getStudentId() + " " + c.getText());
//        }
//        System.out.println("steel's: " + db.courseDao().getForStudent(0).size());
//        db.courseDao().delete(cl.get(0));
//        System.out.println("After");
//        System.out.println("steel's: " + db.courseDao().getForStudent(0).size());
//        cl = db.courseDao().getAll();
//        for (Course c : cl) {
//            System.out.println(c.getStudentId() + " " + c.getText());
//        }
//
//        System.out.println("-----------");
        // FIXME ---

        db = AppDatabase.singleton(getApplicationContext());
        List<Course> courseList;
        Set<String> userCourses;
        String[] split;
        Student currStudent;
        int studentId;

        // For each key, it gets the corresponding list of courses entered by the user
        // and cross-checks each course with the courses pre-populated into the database
        // FIXME: for now, doesn't allow duplicate names (could probably check with studentId)

        NewStudent ns2 = new NewStudent("Elias");
        System.out.println(ns2.getStudentId());
        NewCourse nc2 = new NewCourse(ns2.getStudentId(), "CSE 1");
        db.newStudentDao().insert(ns2);
        db.newCourseDao().insert(nc2);
        System.out.println(db.newStudentDao().getAll().get(0).getStudentId());
        System.out.println(db.newCourseDao().getForStudent(ns2.getStudentId()).get(0).getText());

        /*
         * Ex. User's entered previous courses = {140, 191}
         *
         * Algorithm cross-checks 140 with every course in the pre-populated database.
         * Similar logic for 191.
         *
         * 1) Adds Steel to NEW Student database as a student the User shares previous courses with,
         *    in particular 140 during a particular year and quarter. Maps 140 to Steel in NEW database.
         * 2) Adds Aiko to NEW Course database as a student the User shares previous courses with,
         *    in particular 191 during a particular year and quarter. Maps 191 to Aiko in NEW database.
         *  */
        for (Object o : keysArr) {
            userCourses = (Set<String>) userCoursesMap.get(o);
            courseList = db.courseDao().getAll();

            for (String uC : userCourses) {
                for (Course cL : courseList) {
                    split = cL.getCourse().split(" ");
                    if (uC.equals(split[1])) {
                        studentId = cL.getStudentId();
                        NewStudent ns = new NewStudent(db.studentDao().get(studentId).getName());
                        NewCourse nc = new NewCourse(studentId, split[1]);

                        ns.setStudentId(studentId);
                        db.newStudentDao().insert(ns);
                        db.newCourseDao().insert(nc);
                    }
                }
            }
        }

        System.out.println("Sizes: ");
        List<NewStudent> ns = db.newStudentDao().getAll();
        System.out.println(ns.size());
    }
}


//            for (String course : userCourses) {
//                for (Course uCourse : courseList) {
//                    if (!course.equals(uCourse.getText())) {
//                        id = uCourse.getStudentId();
//                        currStudent = db.studentDao().get(id);
//
//                        db.courseDao().delete(uCourse);
//
//                        if (db.courseDao().getForStudent(id) == null
//                                || db.courseDao().getForStudent(id).size() == 0) {
//                            db.studentDao().delete(currStudent);
//                        }
//                    }
//                }
//            }