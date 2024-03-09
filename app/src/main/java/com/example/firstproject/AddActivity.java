package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, type_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        type_input = findViewById(R.id.type_input);
        add_button = findViewById(R.id.add_title_button);
        add_button.setOnClickListener(v -> {
            MyDBHelper myDB = new MyDBHelper(AddActivity.this);
            myDB.addTitle(title_input.getText().toString(), type_input.getText().toString());
            Intent intent = new Intent(this, titleCatalog.class);
            startActivity(intent);
        });
    }

}