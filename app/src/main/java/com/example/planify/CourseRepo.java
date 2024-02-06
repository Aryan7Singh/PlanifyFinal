package com.example.planify;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepo {
    private CourseDao courseDao;
    private LiveData<List<Course>> courseList;

    public CourseRepo(Application application) {
        CourseDatabase courseDatabase = CourseDatabase.getInstance(application);
        courseDao = courseDatabase.courseDao();
        courseList = courseDao.getAllData();
    }
    public void insertData(Course course) {new InsertCourse(courseDao).execute(course);}
    public void updateData(Course course) {new UpdateCourse(courseDao).execute(course);}
    public void deleteData(Course course) {new DeleteCourse(courseDao).execute(course);}
    public LiveData<List<Course>> getAllData() {
//        Globals.viewType = 1; //Check --> Manually overriding works
        if (Globals.courseViewType == 0) {
            courseList = courseDao.getAllData();
        } else if (Globals.courseViewType == 1) {
            courseList = courseDao.getTitleData();
        } else {
            courseList = courseDao.getTimeData();
        }
        return courseList;
    }

    private static class InsertCourse extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        public InsertCourse(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.insert(courses[0]);
            return null;
        }
    }
    private static class DeleteCourse extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        public DeleteCourse(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.delete(courses[0]);
            return null;
        }
    }
    private static class UpdateCourse extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        public UpdateCourse(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.update(courses[0]);
            return null;
        }
    }



}
