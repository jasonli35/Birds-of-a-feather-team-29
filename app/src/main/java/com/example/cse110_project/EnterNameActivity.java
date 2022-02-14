package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EnterNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        setTitle("Birds of a Feather v0.0.1");
    }

    public void onConfirmButtonClicked(View view) {
        TextView nameTextView = findViewById(R.id.enter_name);
        String name = nameTextView.getText().toString();
        if(!isValidName(name)){

            runOnUiThread(() -> {
                Utilities.showAlert(this, "Alert!", "Invalid name! Valid characters: A-Z, a-z, space character");
            });
        }
        else {
            SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userFirstName", name);
            Intent intent = new Intent(this, AddLinkActivity.class);
            startActivity(intent);
        }

        //Intent intent = new Intent(this, AddCoursesMainActivity.class);
        //startActivity(intent);
    }

    public static boolean isValidName(String name){
        if(name.isEmpty()){
            return false;
        }
        for(int i = 0; i < name.length(); i++){
            char curr = name.charAt(i);
            if(curr < 32){
                return false;
            }
            else if(curr > 32 && curr < 65){
                return false;
            }
            else if(curr > 90 && curr < 97){
                return false;
            }
            else if(curr > 122){
                return false;
            }
        }
        return true;
    }
}