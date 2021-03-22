package com.udemy_3.daychecked_beta5;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.udemy_3.daychecked_beta5.Tables.Task;

import java.time.LocalDate;

public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_TASKNAME ="com.udemy_3.daychecked_beta5.TASKNAME";
    public static final String EXTRA_COLOR ="com.udemy_3.daychecked_beta5.TASKCOLOR";

    public static final String EXTRA_UPDATE ="com.udemy_3.daychecked_beta5.TASKUPDATE";

    private String COLOR="";

    private EditText editTextNameTask;
    private ImageButton greenbtn;
    private ImageButton yellowbtn;
    private ImageButton redbtn;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_adctivity);

        editTextNameTask = findViewById(R.id.name_task);
        greenbtn = findViewById(R.id.greenbtn);
        yellowbtn = findViewById(R.id.yellowbtn);
        redbtn = findViewById(R.id.redbtn);
        saveButton = findViewById(R.id.save);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("new Task");

        greenbtn.setOnClickListener(this);
        yellowbtn.setOnClickListener(this);
        redbtn.setOnClickListener(this);
        saveButton.setOnClickListener(this);


    }




    private void saveTask(){
        String title = editTextNameTask.getText().toString().trim();

        if(title.isEmpty()){
            Toast.makeText(AddEditActivity.this, "insert the name", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TASKNAME, title);

        if(COLOR==""){
            COLOR =MainActivity.GREY;
        }
        data.putExtra(EXTRA_COLOR, COLOR);
        setResult(RESULT_OK, data);
        finish();


    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.save:
                saveTask();
                break;
            case R.id.greenbtn:
                COLOR = MainActivity.GREEN;
                break;
            case R.id.redbtn:
                COLOR = MainActivity.RED;
                break;
            case R.id.yellowbtn:
                COLOR =MainActivity.YELLOW;
            default:
                break;
        }
    }





}