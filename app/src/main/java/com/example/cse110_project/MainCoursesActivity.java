/*
 * Source(s):
 *
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
    /** Instance variables */
    private PreviousCoursesDB prevCourses;

    // FIXME delete
    int counter = 1;

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

    // FIXME -- WORK IN PROGRESS
    public void addCoursesToDatabase() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) { return; }
        SharedPreferences currEnteredClasses = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        String key = extras.getString("keySubject");
        HashSet<String> set = (HashSet<String>) currEnteredClasses.getStringSet(key, null);
        if (set == null) { return; }
        SharedPreferences mainUserClassInfo = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        SharedPreferences.Editor mainEditor = mainUserClassInfo.edit();
        String completeKey = currEnteredClasses.getString("year", "null")
                + currEnteredClasses.getString("quarter", "null") + key;
        mainEditor.putStringSet(completeKey, set);
        mainEditor.apply();

        // FIXME -- test
        SharedPreferences test = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        HashSet<String> set =
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

    public void onClickEnter(View view) {
        // Clears keys 1-6 from previous call to AddCoursesActivity to account for new classes
        // FIXME: can be another method/class
        SharedPreferences preferences = getSharedPreferences("currEnteredClasses", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Spinner s1 = (Spinner)findViewById(R.id.year_dropdown_container);
        Spinner s2 = (Spinner)findViewById(R.id.quarter_dropdown_container);
        String year = s1.getSelectedItem().toString();
        String quarter = s2.getSelectedItem().toString();
        editor.clear();
        editor.apply();
        editor.putString("year", year);
        editor.apply();
        editor.putString("quarter", quarter);
        editor.apply();

        Intent intent = new Intent(this, AddCoursesActivity.class);

        TextView subject = findViewById(R.id.enter_subject_textview);
        TextView course = findViewById(R.id.enter_course_textview);
        intent.putExtra("subject", subject.getText().toString());
        intent.putExtra("initCourseNumber", course.getText().toString());

        startActivity(intent);
    }

    public void onClickDone(View view) {

    }

    // FIXME delete
    public void onClickTest(View view) throws InterruptedException {
//        SharedPreferences pref = getSharedPreferences("userClassInfo", MODE_PRIVATE);
//        if (pref.getString(Integer.toString(counter), "not found").equals("not found") || counter > 6) {
//            Utilities.showAlert(this, "bruh");
//            counter = 1;
//            return;
//        }
//        TextView test = findViewById(R.id.test_textview);
//        test.setText(pref.getString(Integer.toString(counter), "not found"));
//        counter++;

        /*Spinner s1 = (Spinner)findViewById(R.id.year_dropdown_container);
        String text1 = s1.getSelectedItem().toString();
        TextView year = findViewById(R.id.test_textview);
        year.setText(text1);*/

        /*SharedPreferences preferences = getSharedPreferences("mainUserClassInfo", MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) preferences.getStringSet("CSE", null);
        TextView text = findViewById(R.id.test_textview);
        if (set == null) { text.setText("null"); }
        else { text.setText(Integer.toString(set.size())); }
        */

//        TextView text = findViewById(R.id.test_textview);
//        text.setText(Integer.toString(set.size()));


//        Bundle extras = getIntent().getExtras();
//        if (extras == null) { Utilities.showAlert(this, "no extras"); return; }
//        SharedPreferences preferences = getSharedPreferences("userClassInfo", MODE_PRIVATE);
//        String key = extras.getString("key");
//        HashSet<String> set = (HashSet<String>) preferences.getStringSet(key, null);
//        if (set == null) { Utilities.showAlert(this, "set null"); return; }
//        TextView text = findViewById(R.id.test_textview);
//        for (String s : set) {
//            System.out.println(s);
//        }

        //        for (String courseNumber : set) {
//            text.setText(courseNumber);
//            Thread.sleep(100);
//        }
//        Thread.sleep(500);
//        text.setText("done");


        //String key = extras.getString("key");
//        TextView view2 = findViewById(R.id.test_textview);
//        if (extras.containsKey("key")) {
//            view2.setText("true");
//        } else {
//            view2.setText("false");
//        }
    }
}