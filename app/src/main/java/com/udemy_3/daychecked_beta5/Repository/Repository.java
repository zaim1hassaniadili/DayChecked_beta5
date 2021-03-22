package com.udemy_3.daychecked_beta5.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.udemy_3.daychecked_beta5.Dao.DayDao;
import com.udemy_3.daychecked_beta5.Database.DayDatabase;
import com.udemy_3.daychecked_beta5.Tables.Day;

import java.util.List;

public class Repository {
    private DayDao dayDao;
    private LiveData<List<Day>> allDay;
    private LiveData<Day> jDay;

    public Repository(Application application){
        DayDatabase dayDatabase = DayDatabase.getInstance(application);
        dayDao = dayDatabase.dayDao();
        allDay = dayDao.getAllDay();
        jDay = dayDao.getDay();
    }


    public void deleteAll(){
        new DeleteAllAsyncTask(dayDao).execute();

    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private DayDao dayDao;

        private DeleteAllAsyncTask(DayDao dayDao){
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dayDao.deleteAll();
            return null;
        }
    }


    public void deleteDay(Day day){
        new DeleteDayAsynTask(dayDao).execute(day);
    }

    private static class DeleteDayAsynTask extends AsyncTask<Day,Void,Void> {
        private DayDao dayDao;

        public DeleteDayAsynTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... day) {
            dayDao.deleteDay(day[0]);
            return null;
        }
    }

    public void updateDay(Day day){
        new UpdateDayAsynTask(dayDao).execute(day);
    }

    private static class UpdateDayAsynTask extends AsyncTask<Day,Void,Void> {
        private DayDao dayDao;

        public UpdateDayAsynTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... day) {
            dayDao.updateDay(day[0]);
            return null;
        }
    }


    public void insertDay(Day day){
        new  InsertDayAsynTask(dayDao).execute(day);
    }

    private static class InsertDayAsynTask extends AsyncTask<Day,Void,Void> {
        private DayDao dayDao;

        public  InsertDayAsynTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }

        @Override
        protected Void doInBackground(Day... day) {
            dayDao.insertDay(day[0]);
            return null;
        }
    }

    public LiveData<List<Day>> getAllDay(){
        return allDay;
    }

    public LiveData<Day> getDay(){
        return jDay;
    }
}
