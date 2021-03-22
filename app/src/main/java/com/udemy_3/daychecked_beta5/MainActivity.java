package com.udemy_3.daychecked_beta5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.udemy_3.daychecked_beta5.Database.DayDatabase;
import com.udemy_3.daychecked_beta5.RecyclerView.TaskAdapter;
import com.udemy_3.daychecked_beta5.Tables.Day;
import com.udemy_3.daychecked_beta5.Tables.Task;
import com.udemy_3.daychecked_beta5.ViewModel.DayViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DayViewModel viewModel;



    public static final String RED = "#FA3D15";
    public static final String YELLOW = "#F6FA15";
    public static final String GREEN = "#15FA2F";
    public static final String GREY = "#8BA8C2";

    public static final int ADD_TASK_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addTask = findViewById(R.id.fab_add_task);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });

        //SET UP RECYCLER VIEW
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);


        viewModel = ViewModelProviders.of(MainActivity.this).get(DayViewModel.class);
        viewModel.getDay().observe(MainActivity.this, new Observer<Day>() {
            @Override
            public void onChanged(Day day) {
                List<Task> sumb = new ArrayList<>(day.getHashSetOfTask());
                viewModel.updateDay(day);
                submitList((ArrayList<Task>) sumb, adapter);
            }
        });



        //SWIP TO DELETE

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.getDay().observe(MainActivity.this, new Observer<Day>() {
                    @Override
                    public void onChanged(Day day) {
                        Log.d("Test", "onChanged: " + viewHolder.getAdapterPosition());

                        day.getHashSetOfTask().remove(viewHolder.getAdapterPosition());
                        viewModel.updateDay(day);
                        List<Task> sumb1 = new ArrayList<>(day.getHashSetOfTask());
                        submitList((ArrayList<Task>) sumb1, adapter);
                        Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).attachToRecyclerView(recyclerView);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            final String title = data.getStringExtra(AddEditActivity.EXTRA_TASKNAME);
            final String color = data.getStringExtra(AddEditActivity.EXTRA_COLOR);

            viewModel.getDay().observe(MainActivity.this, new Observer<Day>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onChanged(Day day) {
                    if (day == null) {
                        Day currentDay = new Day(new HashSet<Task>(), LocalDate.now());
                        currentDay.getHashSetOfTask().add(new Task(title, color));
                        viewModel.insertDay(currentDay);
                    } else if (day != null) {
                        day.getHashSetOfTask().add(new Task(title, color));
                        viewModel.updateDay(day);
                    }//there is a day but we are not in the same dau
                }

            });
        }
    }

    public static synchronized void submitList(ArrayList<Task> arr, TaskAdapter adapter){
        Collections.sort(arr, new ColorComparator());

        adapter.submitList(arr);
    }
    static class ColorComparator implements Comparator<Task> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public int compare(Task task1, Task task2) {
            if (task1.getColor().equals(task2.getColor())) {
                return 0;
            } else {
                if (task1.getColor().equals(RED)) {
                    return -1;
                } else if (task1.getColor().equals(YELLOW) && task2.getColor().equals(RED)) {
                    return 1;
                } else if (task1.getColor().equals(GREEN) &&
                        (task2.getColor().equals(YELLOW) || task2.getColor().equals(RED))) {
                    return 1;
                } else if (task1.getColor().equals(GREY)) {
                    return 1;
                } else {
                    return -1;
                }
            }


        }
    }
}