package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link);
    }


    public void processPhoto(View view) {
        SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        TextView urlView = findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, PreviewPhotoActivity.class);
        startActivity(intent);
        editor.putString("url", urlView.getText().toString());
        editor.apply();
    }
}