/*
 * Source(s):
 *
 * How to implement a dropdown menu -
 * https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
 * https://developer.android.com/guide/topics/ui/controls/spinner
 * */

package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cse110_project.prevcourses.db.PreviousCoursesDB;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainCoursesActivity extends AppCompatActivity {
    /** Constants */
    private final String NO_SUB_OR_COURSE_NUMBER_WARNING = "Please enter a subject or course number" +
            " in the respective empty field.";
    private final String NO_CLASSES_ENTERED_WARNING = "Please enter at least one class for selected" +
            " a year and quarter to proceed to the next page.";

    /** Instance variables */
    private PreviousCoursesDB prevCourses;

    /** Static variables */
    static int keyNumber = 1;

    // test comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_courses);
        setTitle("Birds of a Feather");

        // Adding entered courses into the database for future reference
        addCoursesToDatabase();

        // Initializing items for each dropdown menu
        initYearDropdown();
        initQuarterDropdown();
    }

    public void onClickEnter(View view) {
        TextView subject = findViewById(R.id.enter_subject_textview);
        TextView courseNumber = findViewById(R.id.enter_course_textview);
        if ((subject.getText().toString().equals("")) || (courseNumber.getText().toString().equals(""))) {
            Utilities.showAlert(this, "Warning!", NO_SUB_OR_COURSE_NUMBER_WARNING);
            return;
        }

        // Clears keys 1-6 from previous call to AddCoursesActivity to account for new classes
        // FIXME: can be another method/class
        SharedPreferences preferences = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Spinner s1 = (Spinner)findViewById(R.id.year_dropdown_container);
        Spinner s2 = (Spinner)findViewById(R.id.quarter_dropdown_container);
        String year = s1.getSelectedItem().toString();
        String quarter = s2.getSelectedItem().toString();
        editor.clear();
        editor.putString("year", year);
        editor.putString("quarter", quarter);
        editor.apply();

        Intent intent = new Intent(this, AddCoursesActivity.class);
        intent.putExtra("subject", subject.getText().toString());
        intent.putExtra("initCourseNumber", courseNumber.getText().toString());
        startActivity(intent);
    }

    public void onClickDone(View view) {
        SharedPreferences mainUserClassInfoSP = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        if (mainUserClassInfoSP.getAll().isEmpty()) {
            Utilities.showAlert(this, "Alert!", NO_CLASSES_ENTERED_WARNING);
            return;
        }
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void initYearDropdown() {
        Spinner yearDropdown = findViewById(R.id.year_dropdown_container);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.academic_years, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        yearDropdown.setAdapter(adapter);
    }

    public void initQuarterDropdown() {
        Spinner quarterDropdown = findViewById(R.id.quarter_dropdown_container);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.academic_quarters, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        quarterDropdown.setAdapter(adapter);
    }

    public void addCoursesToDatabase() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) { return; }

        SharedPreferences currEnteredClassesSP = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        String key = extras.getString("keySubject");
        HashSet<String> set = (HashSet<String>) currEnteredClassesSP.getStringSet(key, null);
        if (set == null) { return; }

        SharedPreferences mainUserClassInfoSP = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        SharedPreferences.Editor mainEditor = mainUserClassInfoSP.edit();

        String completeKey = currEnteredClassesSP.getString("year", null)
                + currEnteredClassesSP.getString("quarter", null) + key;
        mainEditor.putStringSet(completeKey, set);
        mainEditor.apply();

        SharedPreferences completeKeysSP = getSharedPreferences("allCompleteKeys", MODE_PRIVATE);
        SharedPreferences.Editor completeKeyEditor = completeKeysSP.edit();
        completeKeyEditor.putString(Integer.toString(keyNumber), completeKey);
        completeKeyEditor.apply();
        keyNumber++;
    }
}