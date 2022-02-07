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

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Birds of a Feather");
        clearUserClassInfo();
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