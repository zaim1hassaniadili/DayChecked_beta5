package com.udemy_3.daychecked_beta5.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.udemy_3.daychecked_beta5.Dao.DayDao;
import com.udemy_3.daychecked_beta5.Convertor.LocalDateTypeConvertor;
import com.udemy_3.daychecked_beta5.MainActivity;
import com.udemy_3.daychecked_beta5.Tables.Day;
import com.udemy_3.daychecked_beta5.Convertor.TaskTypeConvector;
import com.udemy_3.daychecked_beta5.Tables.Task;
import com.udemy_3.daychecked_beta5.ViewModel.DayViewModel;

import java.time.LocalDate;
import java.util.HashSet;

@TypeConverters({LocalDateTypeConvertor.class, TaskTypeConvector.class})
@Database(entities = Day.class, version=6, exportSchema = false)
public abstract class DayDatabase extends RoomDatabase {
    private static DayDatabase INSTANCE;
    public abstract  DayDao dayDao();

    public static synchronized DayDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DayDatabase.class,
                    "day_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();

        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DayDao dayDao;

        public PopulateDbAsyncTask(DayDatabase db){
            dayDao = db.dayDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            HashSet<Task> hashSet = new HashSet<>();
            hashSet.add(new Task("task 1", "#8BA8C2"));
            hashSet.add(new Task("task 1", "#8BA8C2"));
            hashSet.add(new Task("task 1", "#8BA8C2"));

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Day day = new Day(hashSet, LocalDate.now());
                dayDao.insertDay(day);
            }

            return null;
        }
    }

}
