package com.example.planify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planify.databinding.ActivityAssignmentDataInsertBinding;
public class AssignmentDataInsertActivity extends AppCompatActivity {
    ActivityAssignmentDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignmentDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")) {
            setTitle("Update");
            binding.AssignName.setText(getIntent().getStringExtra("assignName"));
            binding.DueDate.setText(getIntent().getStringExtra("dueDate"));
            binding.ClassAssoc.setText(getIntent().getStringExtra("classAssoc"));
            binding.DayRepeat.setText(getIntent().getStringExtra("dayRepeat"));
            binding.LocationRmNum.setText(getIntent().getStringExtra("locRm"));
            int id = getIntent().getIntExtra("id", 0);
            binding.addAssignment.setText("Update Assignment");
            binding.addAssignment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("assignName", binding.AssignName.getText().toString());
                    intent.putExtra("dueDate", binding.DueDate.getText().toString());
                    intent.putExtra("classAssoc", binding.ClassAssoc.getText().toString());
                    intent.putExtra("dayRepeat", binding.DayRepeat.getText().toString());
                    intent.putExtra("locRm", binding.LocationRmNum.getText().toString());
                    intent.putExtra("id", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            setTitle("Add Mode");
            //add --> referencing button widget id
            binding.addAssignment.setText("Add Assignment");
            binding.addAssignment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("assignName", binding.AssignName.getText().toString());
                    intent.putExtra("dueDate", binding.DueDate.getText().toString());
                    intent.putExtra("classAssoc", binding.ClassAssoc.getText().toString());
                    intent.putExtra("dayRepeat", binding.DayRepeat.getText().toString());
                    intent.putExtra("locRm", binding.LocationRmNum.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }
}