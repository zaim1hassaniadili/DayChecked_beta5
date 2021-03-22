package com.udemy_3.daychecked_beta5.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.udemy_3.daychecked_beta5.Tables.Day;

import java.util.List;

@Dao
public interface DayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDay(Day day);

    @Update
    void updateDay(Day day);

    @Delete
    void deleteDay(Day day);

    @Query("SELECT *FROM day_table")
    LiveData<List<Day>> getAllDay();

    @Query("SELECT *FROM day_table ORDER BY id DESC LIMIT 1")
    LiveData<Day> getDay();

    @Query("DELETE FROM day_table")
    void deleteAll();
}
