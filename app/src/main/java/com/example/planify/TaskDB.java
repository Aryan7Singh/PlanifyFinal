package com.example.planify;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Task.class, version = 1) //version included for production purposes
public abstract class TaskDB extends RoomDatabase {
    private static TaskDB instance;
    public abstract TaskDAO taskDAO();
    public static synchronized TaskDB getInstance(Context context) {
        if (instance == null) { //If the database hasn't been created, create it*?
            instance = Room.databaseBuilder(context.getApplicationContext(), TaskDB.class, "task_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
