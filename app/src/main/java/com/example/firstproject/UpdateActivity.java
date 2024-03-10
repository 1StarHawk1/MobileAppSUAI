package com.example.firstproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, type_input;
    Button update_button, delete_button;
    String id, name, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.title_update_input);
        type_input = findViewById(R.id.type_update_input);
        update_button = findViewById(R.id.update_title_button);
        update_button.setOnClickListener(v -> {
            MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);
            myDB.updateTitle(id, name_input.getText().toString(), type_input.getText().toString());
            Intent intent = new Intent(this, titleCatalog.class);
            startActivity(intent);
        });
        delete_button = findViewById(R.id.delete_title_button);
        delete_button.setOnClickListener(v -> {
            confirmDialog();

        });
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("type")){
            //Получаем данные
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            //Устанавливаем данные на форму
            name_input.setText(name);
            type_input.setText(type);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + name + "?");
        builder.setMessage("Вы уверены что хотить удалить " + name +"?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDBHelper myDB = new MyDBHelper(UpdateActivity.this);
                myDB.deleteTitle(id);
                Intent intent = new Intent(UpdateActivity.this, titleCatalog.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}