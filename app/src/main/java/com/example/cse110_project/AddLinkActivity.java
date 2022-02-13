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
        setTitle("Birds of a Feather v0.0.1");
    }


    public void processPhoto(View view) {
        SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        TextView urlView = findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, PreviewPhotoActivity.class);

        editor.putString("url", urlView.getText().toString());
        editor.apply();
        startActivity(intent);
    }

    public void skipPhoto(View view) {
        String default_pic = "https://lh3.googleusercontent.com/pw/AM-JKLWckaAiQnn9K7DpJJGQgQxhDkIpsqGkpPluj5-kKN4fm3kJ4S0yM3hgUNnfFGLC8tFgYZicHvhIZCjwSNqzcanv28VJ4YkX_56rZejnAOU7wbk7Wiwbb8m-lzGtNYurHVdbRaLidgPyhk0mikN7dXaI=s225-no";

        SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Intent intent = new Intent(this, PreviewPhotoActivity.class);

        editor.putString("url", default_pic);
        editor.apply();
        startActivity(intent);
    }
}




