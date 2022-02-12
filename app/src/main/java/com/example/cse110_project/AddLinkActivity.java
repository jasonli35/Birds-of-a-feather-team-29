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
        String default_pic = "https://lh3.googleusercontent.com/A2_HjBIi575lyKBOZBMOD8SOZCHjg3NA-Vwazamtl40qHuvi1SuOVboHXi_ys_6QO_7M2CSmQQEF5zob7MuVixi04DMjszMMP-eeokgxjccbs0kLk1aQFpXJJej86OhsxdtwdNLuVro6Wah_hDlYGSAx83N16TI7ugY5kpIyUTo10zeoKHB1mNPCFG_nqNeYftPLh2t4rrq9nPTw85fcpzNUVpzYnWnhAaKEArlEalrwsDFE9bGnSVTEkWq7JqpOQGSdKM2uKtN8XREn7LVH_9n83JC6PpwNFTgEiQVkfdGHatSSNAFmqKn4X5ak6AojpGZyf73K5cCJCWgf-Wh4xmQusgCjQiP8sQO1ugmNnyXMwp_tLc4tctyzxHfwcQXDzbIfng9A-V6r2KKaWyy70NYKJfYsUi_OeKHALibILyXGkw7RASC2CYHIgbkElUAC3W1HshodykuuQhyLxHQB6WrnXAGIJyg8lzS3uUiu_VGJy6BtwQmb5VPEaCOjbjbfyPZyCwaAuTMj0x1z9NS9e9kqU33y3xCXWv2AcjI-mQSM2e4eNWS94_oGccAodPeoRby0mkQfdEuwpSGXkVHbhe4roQ4JJo_XYTP9dsooz5x9AGHDMOymTQchr-6Ir5UdCgZiG5_-JJpVJo417QfnhKxuIfn7pWBiAGWMB_aLc2TADlN9Xaoi_-J4SS5GVT9bb-GGmEmoVKr8JrEoDLIutF9g=s225-no?authuser=0";

        SharedPreferences preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        TextView urlView = findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, PreviewPhotoActivity.class);

        editor.putString("url", default_pic);
        editor.apply();
        startActivity(intent);
    }
}




