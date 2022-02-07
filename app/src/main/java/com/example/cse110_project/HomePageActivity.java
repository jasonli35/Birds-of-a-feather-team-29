package com.example.cse110_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

//import com.example.cse110_project.MainCoursesActivity;


public class HomePageActivity extends AppCompatActivity{
    private static final String TAG = "Project-Nearby";
    private static final String MESSAGE =
            "Amy 2020FallCSE 30 12 15L 2020SpringCSE 100 101";
    private static final String SHARED_PREF_MAIN_USER_CLASS_INFO_DB = "mainUserClassInfo";
    private MessageListener messageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        MessageListener realListener = new MessageListener() {
            @Override
            public void onFound(@NonNull Message message){
                //Log.d(TAG, "Found message: " + new String(message.getContent()));
                ArrayList<String> result = compareCourses(new String(message.getContent()));
                //Log.d(TAG, "Found message: " + result);
            }

            @Override
            public void onLost(@NonNull Message message){
                //Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
            }
        };

        this.messageListener = new FakedMessageListener(realListener,3,MESSAGE);
    }

    public void onClickStart(View view) {
        TextView topLeftButton = findViewById(R.id.start_button);
        if (topLeftButton.getText().toString().equals("Start")) { topLeftButton.setText("Stop"); }
        else { topLeftButton.setText("Start"); }

//        MessageListener realListener = new MessageListener() {
//            @Override
//            public void onFound(@NonNull Message message){
//                //Log.d(TAG, "Found message: " + new String(message.getContent()));
//                ArrayList<String> result = compareCourses(new String(message.getContent()));
//                //Log.d(TAG, "Found message: " + result);
//            }
//
//            @Override
//            public void onLost(@NonNull Message message){
//                //Log.d(TAG, "Lost sight of message: " + new String(message.getContent()));
//            }
//        };
        //this.messageListener = new FakedMessageListener(realListener,3,MESSAGE);
    }

    //compare courses student entered to the messages received
    public ArrayList<String> compareCourses(String message) {
        ArrayList<String> ret = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_MAIN_USER_CLASS_INFO_DB, MODE_PRIVATE);
        String[] courses = message.split(" ");
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



    @Override
    protected void onStart(){
        super.onStart();
        //Nearby.getMessagesClient(this).subscribe(messageListener);
    }

    @Override
    protected void onStop(){
        super.onStop();
        //Nearby.getMessagesClient(this).unsubscribe(messageListener);
    }

}