package com.example.planify;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Exam.class, version = 1) //version included for production purposes
public abstract class ExamDatabase extends RoomDatabase {
    private static ExamDatabase instance;
    public abstract ExamDAO examDAO();
    public static synchronized ExamDatabase getInstance(Context context) {
        if (instance == null) { //If the database hasn't been created, create it*?
            instance = Room.databaseBuilder(context.getApplicationContext(), ExamDatabase.class, "exam_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
