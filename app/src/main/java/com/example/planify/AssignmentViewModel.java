package com.example.planify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AssignmentViewModel extends AndroidViewModel {
    private AssignmentRepo assignmentRepo;
    private LiveData<List<Assignment>> assignmentList;
    public AssignmentViewModel(@NonNull Application application) {
        super(application);
        assignmentRepo = new AssignmentRepo(application);
        assignmentList = assignmentRepo.getAllData();
    }
    public void insert(Assignment assignment) {
        assignmentRepo.insertData(assignment);
    }
    public void delete(Assignment assignment) {
        assignmentRepo.deleteData(assignment);
    }
    public void update(Assignment assignment) {
        assignmentRepo.updateData(assignment);
    }
    public LiveData<List<Assignment>> getAllAssignments() {
        assignmentList = assignmentRepo.getAllData();
        return assignmentList;
    }
}
