package com.example.cse110_project;

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

@RunWith(AndroidJUnit4.class)
public class MainCoursesActivityTest {
    @Rule
    public ActivityScenarioRule<MainCoursesActivity> rule = new ActivityScenarioRule<>(MainCoursesActivity.class);

    /**
     * BDD Scenario Acceptance Test:
     *
     * Tests BDD Scenario 2 of User Story #4
     * */
    @Test
    public void test_Subject_TextView_Is_Empty() {
        ActivityScenario<MainCoursesActivity> scenario = rule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            TextView subject = activity.findViewById(R.id.enter_subject_textview);
            assert(subject.getText().toString().equals(""));
        });
    }

    /**
     * BDD Scenario Acceptance Test:
     *
     * Tests BDD Scenario 2 of User Story #4
     * */
    @Test
    public void test_Course_Number_TextView_Is_Empty() {
        ActivityScenario<MainCoursesActivity> scenario = rule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            TextView courseEntry = activity.findViewById(R.id.enter_course_textview);
            assert(courseEntry.getText().toString().equals(""));
        });
    }
}
