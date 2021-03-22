package com.udemy_3.daychecked_beta5.Convertor;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udemy_3.daychecked_beta5.Tables.Task;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashSet;

public class TaskTypeConvector {
    @TypeConverter
    public static HashSet<Task> fromString(String value) {
        Type listType = new TypeToken<HashSet<Task>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(HashSet<Task> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
