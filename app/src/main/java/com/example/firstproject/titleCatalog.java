package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class titleCatalog extends AppCompatActivity {

    FloatingActionButton addButton;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_catalog);
        recyclerView = findViewById(R.id.recycler);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void startFirstPage(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}