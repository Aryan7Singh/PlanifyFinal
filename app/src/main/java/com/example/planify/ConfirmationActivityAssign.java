package com.example.planify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.planify.databinding.ActivityAssignmentsBinding;
import com.example.planify.databinding.ActivityAssignmentDataInsertBinding;

public class ConfirmationActivityAssign extends AppCompatActivity {
    ActivityAssignmentsBinding binding;
    private AssignmentViewModel assignmentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String action = getIntent().getStringExtra("act");
        if (action.equals("confirm")) {
            if (Globals.actionAssignment == 0) {
                Intent intent = new Intent();
                intent.putExtra("continue", "t");
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Intent intent1 = new Intent(ConfirmationActivityAssign.this, AssignmentDataInsertActivity.class);
                intent1.putExtra("type", "update");
                intent1.putExtra("assignName", Globals.relAssignment.getAssignName());
                intent1.putExtra("dueDate", Globals.relAssignment.getDueDate());
                intent1.putExtra("classAssoc", Globals.relAssignment.getClassAssoc());
                intent1.putExtra("dayRepeat", Globals.relAssignment.getDayRepeat());
                intent1.putExtra("locRm", Globals.relAssignment.getLocRm());
                intent1.putExtra("id", Globals.relAssignment.getId());
                startActivityForResult(intent1, 5);
            }
        } else {
            Intent intent = new Intent();
            intent.putExtra("continue", "f");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent();
        intent.putExtra("assignName", data.getStringExtra("assignName"));
        intent.putExtra("dueDate", data.getStringExtra("dueDate"));
        intent.putExtra("classAssoc", data.getStringExtra("classAssoc"));
        intent.putExtra("dayRepeat", data.getStringExtra("dayRepeat"));
        intent.putExtra("locRm", data.getStringExtra("locRm"));
        intent.putExtra("id", data.getIntExtra("id", 0));
        intent.putExtra("continue", "t");
        setResult(RESULT_OK, intent);
        finish();
//        }
    }

}
