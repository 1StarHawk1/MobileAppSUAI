package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class titleCatalog extends AppCompatActivity {

    FloatingActionButton addButton;
    RecyclerView recyclerView;
    MyDBHelper myDB;
    ArrayList<String> title_id, title_name, title_type;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_catalog);
        recyclerView = findViewById(R.id.recycler);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(titleCatalog.this, AddActivity.class);
            startActivity(intent);
        });

        myDB = new MyDBHelper(titleCatalog.this);
        title_id = new ArrayList<>();
        title_name = new ArrayList<>();
        title_type = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(titleCatalog.this, title_id, title_name, title_type);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(titleCatalog.this));
    }

        void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
            if(cursor.getCount() == 0){
                Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
            }else{
                while (cursor.moveToNext()){
                    title_id.add(cursor.getString(0));
                    title_name.add(cursor.getString(1));
                    title_type.add(cursor.getString(2));
                }
            }
        }

    public void startFirstPage(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}