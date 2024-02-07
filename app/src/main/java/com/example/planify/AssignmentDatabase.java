package com.example.planify;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Assignment.class,version = 2)
public abstract class AssignmentDatabase extends RoomDatabase {
    private static AssignmentDatabase instance;
    public abstract AssignmentDao assignmentDao();
    public static synchronized AssignmentDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AssignmentDatabase.class, "assignment_database").fallbackToDestructiveMigration().
                    build();
        }
        return instance;
    }

}
