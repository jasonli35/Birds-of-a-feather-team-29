package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.BoFCourse;
import com.example.cse110_project.prevcourses.db.BoFStudent;
import com.example.cse110_project.prevcourses.db.DefaultCourse;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomePageActivity extends AppCompatActivity{
    private AppDatabase db;

    protected RecyclerView studentsRecyclerView;
    protected RecyclerView.LayoutManager studentsLayoutManager;
    protected BoFStudentViewAdapter studentsViewAdapter;

    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        compareUserCoursesWithStudents();

        db = AppDatabase.singleton(getApplicationContext());
        List<BoFStudent> students = db.newStudentDao().getAll();

        studentsRecyclerView = findViewById(R.id.students_view);

        studentsLayoutManager = new LinearLayoutManager(this);
        studentsRecyclerView.setLayoutManager(studentsLayoutManager);

        studentsViewAdapter = new BoFStudentViewAdapter(students);
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
//        List<DefaultCourse> c = db.courseDao().getForStudent(0);
//        System.out.println(c.size());
//        String[] tokens;
//        for (DefaultCourse cc : c) {
//            tokens = cc.getText().split(" ");
//            System.out.println(tokens[1]);
//        }

        // FIXME: checking if course is being deleted
//        db = AppDatabase.singleton(getApplicationContext());
//        List<DefaultCourse> cl = db.courseDao().getAll();
//        for (DefaultCourse c : cl) {
//            System.out.println(c.getStudentId() + " " + c.getText());
//        }
//        System.out.println("steel's: " + db.courseDao().getForStudent(0).size());
//        db.courseDao().delete(cl.get(0));
//        System.out.println("After");
//        System.out.println("steel's: " + db.courseDao().getForStudent(0).size());
//        cl = db.courseDao().getAll();
//        for (DefaultCourse c : cl) {
//            System.out.println(c.getStudentId() + " " + c.getText());
//        }
//
//        System.out.println("-----------");
        // FIXME ---

        // For each key, it gets the corresponding list of courses entered by the user
        // and cross-checks each course with the courses pre-populated into the database
        // FIXME: for now, doesn't allow duplicate names (could probably check with studentId)

//        BoFStudent ns2 = new BoFStudent("Elias");
//        BoFCourse nc2 = new BoFCourse(ns2.getStudentId(), "2020", "Fall", "CSE 1");
//        db.newStudentDao().insert(ns2);
//        db.newCourseDao().insert(nc2);
//        System.out.println(ns2.getName() + ", id before: " + ns2.getStudentId());
//        System.out.println(db.newStudentDao().getAll().get(0).getName() + ", id after: "
//                + db.newStudentDao().getAll().get(0).getStudentId());
//        System.out.println(db.newCourseDao().getForStudent(ns2.getStudentId()).size());
//        System.out.println(db.newCourseDao().getForStudent(db.newStudentDao().getAll().get(0).getStudentId()).get(0));



//        System.out.println(db.newCourseDao().getForStudent(db.newStudentDao().getAll().get(0).getStudentId()).get(0).getYear() + " " +
//                db.newCourseDao().getForStudent(db.newStudentDao().getAll().get(0).getStudentId()).get(0).getQuarter() + " " +
//                db.newCourseDao().getForStudent(db.newStudentDao().getAll().get(0).getStudentId()).get(0).getCourse());



        db = AppDatabase.singleton(getApplicationContext());
        List<DefaultCourse> defaultCourseList;
        Set<String> userCourses;
        String[] studentCourseSplit;
        String[] userKeySplit;
        String year;
        String quarter;
        int studentId;

        /*
         * Ex. User's entered previous courses = {140, 191}
         *
         * Algorithm cross-checks 140 with every course in the pre-populated database.
         * Similar logic for 191.
         *
         * 1) Adds Steel to NEW DefaultStudent database as a student the User shares previous courses with,
         *    in particular 140 during a particular year and quarter. Maps 140 to Steel in NEW database.
         * 2) Adds Aiko to NEW DefaultCourse database as a student the User shares previous courses with,
         *    in particular 191 during a particular year and quarter. Maps 191 to Aiko in NEW database.
         *  */
        for (Object o : keysArr) {
            userKeySplit = ((String)o).split(",");
            userCourses = (Set<String>) userCoursesMap.get(o);
            defaultCourseList = db.courseDao().getAll();

            System.out.print(userKeySplit[0] + " " + userKeySplit[1] + " " + userKeySplit[2]);
            System.out.println();

            for (String uC : userCourses) {
                for (DefaultCourse cL : defaultCourseList) {
                    studentCourseSplit = cL.getCourse().split(" ");
                    year = cL.getYear();
                    quarter = cL.getQuarter();

                    if (cL.getYear().equals(userKeySplit[0]) && cL.getQuarter().equals(userKeySplit[1])
                            && studentCourseSplit[0].equals(userKeySplit[2])
                            && studentCourseSplit[1].equals(uC)) {
                        studentId = cL.getStudentId();

                        // If the student has not been added to the new database, then we add the
                        // student to the database
                        if (!(db.studentDao().get(studentId).getEncountered())) {
                            BoFStudent ns = new BoFStudent(studentId, db.studentDao().get(studentId).getName());
                            db.newStudentDao().insert(ns);
                            db.studentDao().updateEncountered(true, studentId);
                        }

                        studentId = db.newStudentDao().getBasedOnPrevId(studentId).getStudentId();
                        BoFCourse nc = new BoFCourse(studentId, year, quarter, cL.getCourse());
                        db.newCourseDao().insert(nc);
                    }
                }
            }
        }

        // FIXME: for testing purposes
        /*System.out.print("Number of students in new database: ");
        List<BoFStudent> nsl = db.newStudentDao().getAll();
        System.out.println(nsl.size());
        for (BoFStudent ns : nsl) {
            List<BoFCourse> ncl = db.newCourseDao().getForStudent(ns.getStudentId());
            System.out.println("Name: " + ns.getName() + " [" + ns.getStudentId() + "] " + " [" + ns.getPrevStudentId() + "]");
            for (BoFCourse nc : ncl) {
                System.out.println(nc.getCourse());
            }
            System.out.println("---");
        }*/
    }
}