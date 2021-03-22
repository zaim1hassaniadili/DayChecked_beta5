package com.udemy_3.daychecked_beta5.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;


public class Task {

    private String nameTask;
    private String color;
    private boolean checkedTask;
    public Task(){

    }
    public Task(String nameTask, String color){

        this.nameTask = nameTask;
        this.color = color;


    }



    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isCheckedTask() {
        return checkedTask;
    }

    public void setCheckedTask(boolean checkedTask) {
        this.checkedTask = checkedTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getNameTask(), task.getNameTask()) &&
                Objects.equals(getColor(), task.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameTask(), getColor());
    }
}
