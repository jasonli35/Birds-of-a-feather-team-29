package com.example.cse110_project;

import android.content.Context;
import android.support.v4.os.IResultReceiver;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.cse110_project.prevcourses.db.AppDatabase;
import com.example.cse110_project.prevcourses.db.BoFCourseDao;
import com.example.cse110_project.prevcourses.db.BoFStudentDao;
import com.example.cse110_project.prevcourses.db.DefaultCourse;
import com.example.cse110_project.prevcourses.db.DefaultCourseDao;
import com.example.cse110_project.prevcourses.db.DefaultStudent;
import com.example.cse110_project.prevcourses.db.DefaultStudentDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class HomePageTest {
    DefaultStudentDao defSD;
    DefaultCourseDao defCD;
    BoFStudentDao mainSD;
    BoFCourseDao mainCD;
    AppDatabase db;

    @Rule
    public ActivityScenarioRule<HomePageActivity> rule = new ActivityScenarioRule<>(HomePageActivity.class);

    @Before
    public void createTestDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        db = AppDatabase.getSingletonInstance();
        mainSD = db.BoFStudentDao();
        mainCD = db.BoFCourseDao();
        defSD = db.studentDao();
        defCD = db.courseDao();

        defSD.insert(new DefaultStudent("Steel"));
        defSD.insert(new DefaultStudent("Sandy"));

        List<DefaultStudent> defStudentList = defSD.getAll();

        defCD.insert(new DefaultCourse(defStudentList.get(0).getStudentId(), "2020", "Fall",
                "CSE 11"));
        defCD.insert(new DefaultCourse(defStudentList.get(0).getStudentId(), "2020", "Spring",
                "CSE 21"));
        defCD.insert(new DefaultCourse(defStudentList.get(1).getStudentId(), "2020", "Spring",
                "CSE 21"));
        defCD.insert(new DefaultCourse(defStudentList.get(1).getStudentId(), "2021", "Winter",
                "CSE 100"));
    }

    @After
    public void deleteTestDatabase() {
        db.close();
    }

    @Test
    public void defaultTest() {
        assert(true);
    }

    // FIXME: unfinished
//    @Test
//    public void test_User_Has_One_BoF() {
//
//    }

}
