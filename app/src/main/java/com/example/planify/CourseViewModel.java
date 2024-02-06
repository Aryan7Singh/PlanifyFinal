package com.example.planify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private CourseRepo courseRepo;
    private LiveData<List<Course>> courseList;
    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepo = new CourseRepo(application);
        courseList = courseRepo.getAllData();
    }
    public void insert(Course course) {
        courseRepo.insertData(course);
    }
    public void delete(Course course) {
        courseRepo.deleteData(course);
    }
    public void update(Course course) {
        courseRepo.updateData(course);
    }
    public LiveData<List<Course>> getAllCourses() {
        courseList = courseRepo.getAllData();
        return courseList;
    }
}
