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
        binding = ActivityCourseDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")) {
            setTitle("Update");
            binding.Title.setText(getIntent().getStringExtra("title"));
            binding.Time.setText(getIntent().getStringExtra("time"));
            binding.Instructor.setText(getIntent().getStringExtra("instructor"));
            binding.Day.setText(getIntent().getStringExtra("day"));
            binding.Location.setText(getIntent().getStringExtra("location"));
            int id = getIntent().getIntExtra("id", 0);
            binding.addCourse.setText("Update Assignment");
            binding.addCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.Title.getText().toString());
                    intent.putExtra("time", binding.Time.getText().toString());
                    intent.putExtra("instructor", binding.Instructor.getText().toString());
                    intent.putExtra("day", binding.Day.getText().toString());
                    intent.putExtra("location", binding.Location.getText().toString());
                    intent.putExtra("id", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            setTitle("Add Mode");
            //add --> referencing button widget id
            binding.addCourse.setText("Add Assignment");
            binding.addCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.Title.getText().toString());
                    intent.putExtra("time", binding.Time.getText().toString());
                    intent.putExtra("instructor", binding.Instructor.getText().toString());
                    intent.putExtra("day", binding.Day.getText().toString());
                    intent.putExtra("location", binding.Location.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }
}