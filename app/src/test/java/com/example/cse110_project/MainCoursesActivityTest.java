package com.example.cse110_project;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(AndroidJUnit4.class)
public class MainCoursesActivityTest {
    @Rule
    public ActivityScenarioRule<MainCoursesActivity> rule = new ActivityScenarioRule<>(MainCoursesActivity.class);

    /**
     * Unit test of MainCoursesActivity:addCoursesToDatabase()
     * */
    @Test
    public void test_Func_addCoursesToDatabase() {
        ActivityScenario<MainCoursesActivity> scenario = rule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            assert(!activity.addCoursesToDatabase());
        });
    }

    /**
     * Unit test of MainCoursesActivity:disableDoneClickable()
     * &
     * BDD Scenario Acceptance Test (Story Test)
     *     - Tests Scenario 2: Premise is that the user hasn't entered any courses into the database
     * */
    @Test
    public void test_Func_disableDoneClickable() {
        ActivityScenario<MainCoursesActivity> scenario = rule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            Button doneButton = activity.findViewById(R.id.done_button);
            assert(!doneButton.isClickable());
        });
    }

    @Test
    public void test_Subject_TextView_Is_Initially_Empty() {
        ActivityScenario<MainCoursesActivity> scenario = rule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            TextView subject = activity.findViewById(R.id.enter_subject_textview);
            assert(subject.getText().toString().equals(""));
        });
    }

    @Test
    public void test_Course_Number_TextView_Is_Initially_Empty() {
        ActivityScenario<MainCoursesActivity> scenario = rule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            TextView courseEntry = activity.findViewById(R.id.enter_course_textview);
            assert(courseEntry.getText().toString().equals(""));
        });
    }
}
