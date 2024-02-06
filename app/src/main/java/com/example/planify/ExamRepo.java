package com.example.planify;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExamRepo {
    private ExamDAO examDAO;
    private LiveData<List<Exam>> examList;

    public ExamRepo(Application application) {
        ExamDatabase examDatabase = ExamDatabase.getInstance(application); //Application is a subclass of Context
        examDAO = examDatabase.examDAO();
        examList = examDAO.getAllData();
    }
    public void insertData(Exam exam) {new InsertExam(examDAO).execute(exam);}
    public void updateData(Exam exam) {new UpdateExam(examDAO).execute(exam);}
    public void deleteData(Exam exam) {new DeleteExam(examDAO).execute(exam);}
    public LiveData<List<Exam>> getAllData() {
        return examList;
    }
    private static class InsertExam extends AsyncTask<Exam,Void, Void> {
        private ExamDAO examDAO;

        public InsertExam(ExamDAO examDAO) {
            this.examDAO = examDAO;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDAO.insert(exams[0]);
            return null;
        }
    }
    private static class UpdateExam extends AsyncTask<Exam,Void, Void> {
        private ExamDAO examDAO;

        public UpdateExam(ExamDAO examDAO) {
            this.examDAO = examDAO;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDAO.update(exams[0]);
            return null;
        }
    }
    private static class DeleteExam extends AsyncTask<Exam,Void, Void> {
        private ExamDAO examDAO;

        public DeleteExam(ExamDAO examDAO) {
            this.examDAO = examDAO;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDAO.delete(exams[0]);
            return null;
        }
    }
}
