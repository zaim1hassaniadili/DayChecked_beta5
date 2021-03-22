package com.udemy_3.daychecked_beta5.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.udemy_3.daychecked_beta5.Convertor.LocalDateTypeConvertor;
import com.udemy_3.daychecked_beta5.Convertor.TaskTypeConvector;


import java.time.LocalDate;
import java.util.HashSet;

@Entity(tableName ="day_table")
public class Day {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;



    @TypeConverters(TaskTypeConvector.class)
    @ColumnInfo(name ="task_List")
    private HashSet<Task> hashSetOfTask;




    @TypeConverters(LocalDateTypeConvertor.class)
    @ColumnInfo(name="date")
    private LocalDate dateDay;

    public Day(HashSet<Task> hashSetOfTask, LocalDate dateDay) {
        this.hashSetOfTask = new HashSet<>();
        this.hashSetOfTask.addAll(hashSetOfTask);
        this.dateDay = dateDay;
    }



    public HashSet<Task> getHashSetOfTask() {
        return hashSetOfTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setHashSetOfTask(HashSet<Task> myArrayListOfTask) {
        this.hashSetOfTask = myArrayListOfTask;
    }
    public LocalDate getDateDay() {

        return dateDay;
    }

    public void setDateDay(LocalDate dateDay) {
        this.dateDay = dateDay;
    }


}

