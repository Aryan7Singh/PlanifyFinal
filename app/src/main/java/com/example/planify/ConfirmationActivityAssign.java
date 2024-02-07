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
                intent1.putExtra("title", Globals.relAssignment.getAssignName());
                intent1.putExtra("time", Globals.relAssignment.getTime());
                intent1.putExtra("instructor", Globals.relAssignment.getInstructor());
                intent1.putExtra("day", Globals.relAssignment.getDayRepeat());
                intent1.putExtra("location", Globals.relAssignment.getLocationRmNum());
                intent1.putExtra("id", Globals.relAssignment.getId());
                startActivityForResult(intent1, 5);
//                startActivity(intent1);
//                getIntent().getStringExtra("title")
//                startActivity(intent1);
//                finish();
//                    startActivityForResult(intent, 2);
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
//        if(requestCode == 5) {
//        Log.d("POST UPDATE", "HELLLLOOOOOOO");
        Intent intent = new Intent();
//        Log.d("POST UPDATE", data.getStringExtra("title"));
        intent.putExtra("title", data.getStringExtra("title"));
        intent.putExtra("time", data.getStringExtra("time"));
        intent.putExtra("instructor", data.getStringExtra("instructor"));
        intent.putExtra("day", data.getStringExtra("day"));
        intent.putExtra("location", data.getStringExtra("location"));
        intent.putExtra("id", data.getIntExtra("id", 0));
        intent.putExtra("continue", "t");
        setResult(RESULT_OK, intent);
        finish();
//        }
    }

}
