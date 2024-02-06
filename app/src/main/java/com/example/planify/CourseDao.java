package com.example.planify;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    public void insert(Course course);
    @Update
    public void update(Course course);

    @Delete
    public void delete(Course course);

//    @Query("SELECT * FROM course_list ORDER BY title")

    @Query("SELECT * FROM course_list")
    public LiveData<List<Course>> getAllData();

    @Query("SELECT * FROM course_list ORDER BY title")
    public LiveData<List<Course>> getTitleData();

    @Query("SELECT * FROM course_list ORDER BY time")
    public LiveData<List<Course>> getTimeData();
}
