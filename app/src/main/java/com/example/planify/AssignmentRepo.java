package com.example.planify;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AssignmentRepo {
    private AssignmentDao assignmentDao;
    private LiveData<List<Assignment>> assignmentList;

    public AssignmentRepo(Application application) {
        AssignmentDatabase assignmentDatabase = AssignmentDatabase.getInstance(application);
        assignmentDao = assignmentDatabase.assignmentDao();
        assignmentList = assignmentDao.getAllData();
    }
    public void insertData(Assignment assignment) {new InsertAssignment(assignmentDao).execute(assignment);}
    public void updateData(Assignment assignment) {new UpdateAssignment(assignmentDao).execute(assignment);}
    public void deleteData(Assignment assignment) {new DeleteAssignment(assignmentDao).execute(assignment);}
    public LiveData<List<Assignment>> getAllData() {
//        Globals.viewType = 1; //Check --> Manually overriding works
        if (Globals.assignmentViewType == 0) {
            assignmentList = assignmentDao.getAllData();
        } else if (Globals.assignmentViewType == 1) {
            assignmentList = assignmentDao.getClassAssocData();
        } else {
            assignmentList = assignmentDao.getDueDateData();
        }
        return assignmentList;
    }

    private static class InsertAssignment extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        public InsertAssignment(AssignmentDao assignmentDao) {
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.insert(assignments[0]);
            return null;
        }
    }
    private static class DeleteAssignment extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        public DeleteAssignment(AssignmentDao assignmentDao) {
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.delete(assignments[0]);
            return null;
        }
    }
    private static class UpdateAssignment extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        public UpdateAssignment(AssignmentDao assignmentDao) {
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments) {
            assignmentDao.update(assignments[0]);
            return null;
        }
    }



}
