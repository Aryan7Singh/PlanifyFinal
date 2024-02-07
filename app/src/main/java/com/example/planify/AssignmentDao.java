package com.example.planify;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssignmentDao {
    @Insert
    public void insert(Assignment assignment);
    @Update
    public void update(Assignment assignment);

    @Delete
    public void delete(Assignment assignment);

//    @Query("SELECT * FROM assignment_list ORDER BY title")

    @Query("SELECT * FROM assignment_list")
    public LiveData<List<Course>> getAllData();

    @Query("SELECT * FROM assignment_list ORDER BY title")
    public LiveData<List<Course>> getAssignNameData();

    @Query("SELECT * FROM assignment_list ORDER BY time")
    public LiveData<List<Course>> getDueDateData();
}
