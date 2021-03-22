package com.udemy_3.daychecked_beta5.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.udemy_3.daychecked_beta5.Repository.Repository;
import com.udemy_3.daychecked_beta5.Tables.Day;

import java.util.List;

public class DayViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Day> jDay;
    private LiveData<List<Day>> allDay;
    public DayViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        allDay = repository.getAllDay();
        jDay = repository.getDay();

    }

    public void insertDay(Day day){
        repository.insertDay(day);
    }
    public void updateDay(Day day){
        repository.updateDay(day);
    }
    public void deleteDay(Day day){
        repository.deleteDay(day);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Day>> getAllDay(){
        return  allDay;
    }
    public LiveData<Day> getDay(){
        return jDay;
    }
}
