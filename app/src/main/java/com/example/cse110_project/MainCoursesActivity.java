/*
 * Source(s):
 *
 * How to implement a dropdown menu -
 *  1) Title: How to Add a Dropdown Menu in Android Studio
 *     Link: https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
 *     Date: January 1st - February 2nd, 2022
 *     Source used for...: Guidance on how to implement a dropdown menu
 *  2) Spinners
 *     Link: https://developer.android.com/guide/topics/ui/controls/spinner
 *     Date: January 1st - February 2nd, 2022
 *     Source used for...: Understanding Spinners
 * */

package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;

public class MainCoursesActivity extends AppCompatActivity {
    /** Constants */
    private static final String NO_SUB_OR_COURSE_NUMBER_WARNING = "Please enter a subject or course" +
            " number in the respective empty field.";
    private static final String NO_CLASSES_ENTERED_WARNING = "Please enter at least one class for a" +
            " selected year and quarter to proceed to the next page.";
    private static final String SHARED_PREF_CURR_ENTERED_CLASSES_DB = "currEnteredClasses";
    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";
    private static final String SHARED_PREF_ALL_COMPLETE_KEYS_DB = "allCompleteKeys";
    private static final String YEAR_KEY = "year";
    private static final String QTR_KEY = "quarter";
    private static final String INIT_SUBJECT_KEY = "initSubject";
    private static final String INIT_COURSE_NUMBER = "initCourseNumber";
    private static final String SUBJECT_KEY = "subjectKey";
    private static final String WARNING = "Warning!";
    private static final String EMPTY_STRING = "";

    /** Static variables */
    static int keyNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_courses);
        setTitle("Birds of a Feather");

        addCoursesToDatabase();
        disableDoneClickable();
        initYearDropdown();
        initQuarterDropdown();
    }

    /**
     * Proceeds the User to AddCoursesActivity page
     *
     * @return True if successful, otherwise false if the user has left the subject or course number
     *         entry blank
     * */
    public void onClickEnter(View view) {
        TextView subject = findViewById(R.id.enter_subject_textview);
        TextView courseNumber = findViewById(R.id.enter_course_textview);

        if ((subject.getText().toString().equals(EMPTY_STRING))
                || (courseNumber.getText().toString().equals(EMPTY_STRING))) {
            Utilities.showAlert(this, WARNING, NO_SUB_OR_COURSE_NUMBER_WARNING);
            return;
        }

        SharedPreferences preferences = getSharedPreferencesDatabase(SHARED_PREF_CURR_ENTERED_CLASSES_DB);
        SharedPreferences.Editor editor = preferences.edit();
        Spinner s1 = findViewById(R.id.year_dropdown_container);
        Spinner s2 = findViewById(R.id.quarter_dropdown_container);

        // Clears entries from the previous page (AddCoursesActivity)
        editor.clear();
        editor.putString(YEAR_KEY, s1.getSelectedItem().toString());
        editor.putString(QTR_KEY, s2.getSelectedItem().toString());
        editor.apply();

        Intent intent = new Intent(this, AddCoursesActivity.class);

        intent.putExtra(INIT_SUBJECT_KEY, subject.getText().toString());
        intent.putExtra(INIT_COURSE_NUMBER, courseNumber.getText().toString());

        startActivity(intent);
    }

    /**
     * Proceeds the User to HomePageActivity page
     *
     * @return True if successful, otherwise false if no classes have been entered into the "main
     *         user class info" database
     * */
    public boolean onClickDone(View view) {
        SharedPreferences mainUserClassInfoSP = getSharedPreferencesDatabase(SHARED_PREF_MAIN_USER_CLASS_INFO_DB);

        if (mainUserClassInfoSP.getAll().isEmpty()) {
            Utilities.showAlert(this, WARNING, NO_CLASSES_ENTERED_WARNING);
            return false;
        }

        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);

        return true;
    }

    /**
     * Adding the courses entered by the user from AddCoursesActivity.class in the form of
     * extras into the appropriate SharedPreferences database
     *
     * @return True if successful, otherwise false if there are no extras to begin with or the set of
     *         courses entered is null
     * */
    public boolean addCoursesToDatabase() {
        Bundle extras = getExtras();

        if (extras == null) { return false; }

        // Getting the courses entered by the user from the "current entered classes" database
        // in the form of a set
        String subjectKey = extras.getString(SUBJECT_KEY);
        SharedPreferences currEnteredClassesSP= getSharedPreferencesDatabase(SHARED_PREF_CURR_ENTERED_CLASSES_DB);
        HashSet<String> set = (HashSet<String>) currEnteredClassesSP.getStringSet(subjectKey, null);

        if (set == null) { return false; }

        SharedPreferences mainUserClassInfoSP = getSharedPreferencesDatabase(SHARED_PREF_MAIN_USER_CLASS_INFO_DB);
        SharedPreferences.Editor mainEditor = mainUserClassInfoSP.edit();

        // Adding the set of courses received above into a "main user class info" database as a value
        // mapped to a key representing the year, quarter, and subject
        String completeKey = currEnteredClassesSP.getString(YEAR_KEY, null)
                + "," + currEnteredClassesSP.getString(QTR_KEY, null) + "," + subjectKey;
        mainEditor.putStringSet(completeKey, set);
        mainEditor.apply();

        // Saving the key mapped to by the set above into a "key" database for future reference
        SharedPreferences completeKeysSP = getSharedPreferencesDatabase(SHARED_PREF_ALL_COMPLETE_KEYS_DB);
        SharedPreferences.Editor completeKeysEditor = completeKeysSP.edit();
        completeKeysEditor.putString(Integer.toString(keyNumber), completeKey);
        completeKeysEditor.apply();

        keyNumber++;

        return true;
    }

    /**
     * Makes the Done button clickable depending on whether at least a course has been entered into
     * the main database
     * */
    public void disableDoneClickable() {
        Button doneButton = findViewById(R.id.done_button);
        doneButton.setClickable(!getSharedPreferencesDatabase(SHARED_PREF_MAIN_USER_CLASS_INFO_DB).getAll().isEmpty());
    }

    /**
     * Initializes the dropdown list for year entries
     * */
    public void initYearDropdown() {
        Spinner yearDropdown = findViewById(R.id.year_dropdown_container);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.academic_years, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        yearDropdown.setAdapter(adapter);
    }

    /**
     * Initializes the dropdown list for quarter entries
     * */
    public void initQuarterDropdown() {
        Spinner quarterDropdown = findViewById(R.id.quarter_dropdown_container);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.academic_quarters, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        quarterDropdown.setAdapter(adapter);
    }

    /**
     * Gets a SharedPreferences database based on the given string
     *
     * @return a SharedPreferences object representing the database given
     * */
    public SharedPreferences getSharedPreferencesDatabase(String database) {
        return getSharedPreferences(database, MODE_PRIVATE);
    }

    /**
     * Gets the extras from the passed Intent
     *
     * @return a Bundle object containing extras, if there are any
     * */
    public Bundle getExtras() {
        return getIntent().getExtras();
    }
}