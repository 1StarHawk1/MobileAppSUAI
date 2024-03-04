package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void startFirstPage(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void startSecondPage(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}