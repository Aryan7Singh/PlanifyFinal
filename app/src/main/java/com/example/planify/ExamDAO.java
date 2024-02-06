package com.example.planify;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExamDAO {
    @Insert
    public void insert(Exam exam);
    @Update
    public void update(Exam exam);
    @Delete
    public void delete(Exam exam);
    @Query("SELECT * FROM exam_list") //SQLite commands
    public LiveData<List<Exam>> getAllData();
}
