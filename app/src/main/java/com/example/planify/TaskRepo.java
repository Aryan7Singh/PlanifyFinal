package com.example.planify;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepo {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> taskList;

    public TaskRepo(Application application) {
        TaskDB taskDB = TaskDB.getInstance(application); //Application is a subclass of Context
        taskDAO = taskDB.taskDAO();
        taskList = taskDAO.getAllData();
    }
    public void insertData(Task task) {new InsertTask(taskDAO).execute(task);}
    public void updateData(Task task) {new UpdateTask(taskDAO).execute(task);}
    public void deleteData(Task task) {new DeleteTask(taskDAO).execute(task);}
    public LiveData<List<Task>> getAllData() {
        return taskList;
    }
    private static class InsertTask extends AsyncTask<Task,Void, Void> {
        private TaskDAO taskDAO;

        public InsertTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.insert(tasks[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<Task,Void, Void> {
        private TaskDAO taskDAO;

        public UpdateTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.update(tasks[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Task,Void, Void> {
        private TaskDAO taskDAO;

        public DeleteTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.delete(tasks[0]);
            return null;
        }
    }
}
