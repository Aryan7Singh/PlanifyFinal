package com.example.planify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExamViewModel extends AndroidViewModel {
    private ExamRepo examRepo;
    private LiveData<List<Exam>> examList;

    public ExamViewModel(@NonNull Application application) {
        super(application);
        examRepo = new ExamRepo(application);
        examList = examRepo.getAllData();
    }

    public void insert (Exam task) {
        examRepo.insertData(task);
    }
    public void update (Exam task) {
        examRepo.updateData(task);
    }
    public void delete (Exam task) {
        examRepo.deleteData(task);
    }

    public LiveData<List<Exam>> getAllExams() {
        return examList;
    }
}
