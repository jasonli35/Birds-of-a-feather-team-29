package com.example.cse110_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PreviewPhotoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_photo);
        SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        setTitle("Birds of a Feather v0.0.1");


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load(preferences.getString("url", "https://lh3.googleusercontent.com/EOrcLBJLW8btt7p7MUSRAJxR5n8OPoCA-2eJZHBTSppZEeC8BmLWBzsWQqX7AbyrF7YRE_LE3vaYp_g7PvPD3cswdOGIORMhWgd6hRZStEAMo0vH-aFDgoEUFkjcFHUL4g1qi1BpywczZPFn6nT6jNQ_b7L-uQNPpIoXBkB41OervfOaRujJ8xlNDETi4W2wBCrqmSVmEH-yiv_34HOi4XXIOt0HHS8obgL9NApjzUAi1HrJxmXlJ2VnS6gRU3wsg8pHIlQ-lRTIdozRSUqf8ekKzjoI48IWSkt4-jOXTLzUE3QVuC9LO3SEoA915KODiCQ0zxPqPeI9DVfdfAWp5U2j52kyESM28TeUzwBRD0Cf475ntZ5U7wPFovsWd_wnO9MqSDFRtLax21eyP0BrxX9FSJGxajrIL9EPm5Z44-XzN8LVpZ3DVVc-HXSqu9jxd3EZCFwGr7FvsS2WlQsKrzYq7C45QXukoaN2B9ZkV2N1Kz8IELRD1FJMARn-ckiz75c4cxfpwZ70fLKyj6_bgsO_yHMyDcv6RT9Ipu77Cz18MnB_pjm2r-0PDUgdgzwVtpPd85aaDMCIlsjZEkQ5qGmcZdHjoNaNgFyByX5G_BNPAXKOWOJk7UYmQyqjQkFOLmdfH6VXE5iZowYA14UeEWQqM15Bt4Q1AR9ZywBGpQzuQJxiSYkVNLDMJL_D25hdoY6kb0Ly9UWdX1f3P2gVIsOh=s250-no?authuser=0")).into(imageView);

    }

    public void backToAddPhoto(View view) {
        Intent intent = new Intent(this, AddLinkActivity.class);
        startActivity(intent);
    }

    public void previewActivityConfirmCLicked(View view) {
        //This is the sharePreference that should be use in compare method
        SharedPreferences newPreferences = getSharedPreferences("NameWithURL",MODE_PRIVATE);
        SharedPreferences.Editor newEditor = newPreferences.edit();

        SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor userInfoEditor = preferences.edit();

        newEditor.putString(preferences.getString("userFirstName", "name not found"), preferences.getString("url", "url not found"));
        newEditor.apply();
        userInfoEditor.clear();
        userInfoEditor.apply();
        Intent intent = new Intent(this, AddCoursesMainActivity.class);
        startActivity(intent);
    }
}