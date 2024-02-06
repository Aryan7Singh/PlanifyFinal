package com.example.planify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planify.databinding.ActivityCourseDataInsertBinding;

public class CourseDataInsertActivity extends AppCompatActivity {
    ActivityCourseDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")) {
            setTitle("Update");

            binding.CourseTitle.setText(getIntent().getStringExtra("title"));
            binding.CourseTime.setText(getIntent().getStringExtra("time"));
            binding.CourseInstructor.setText(getIntent().getStringExtra("instructor"));
            binding.CourseDay.setText(getIntent().getStringExtra("day"));
            binding.CourseLocation.setText(getIntent().getStringExtra("location"));
            int id = getIntent().getIntExtra("id", 0);
            binding.addCourse.setText("Update Course");
            binding.addCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.CourseTitle.getText().toString());
                    intent.putExtra("time", binding.CourseTime.getText().toString());
                    intent.putExtra("instructor", binding.CourseInstructor.getText().toString());
                    intent.putExtra("day", binding.CourseDay.getText().toString());
                    intent.putExtra("location", binding.CourseLocation.getText().toString());
                    intent.putExtra("id", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            setTitle("Add Mode");
            //add --> referencing button widget id
            binding.addCourse.setText("Add Course");
            binding.addCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.CourseTitle.getText().toString());
                    intent.putExtra("time", binding.CourseTime.getText().toString());
                    intent.putExtra("instructor", binding.CourseInstructor.getText().toString());
                    intent.putExtra("day", binding.CourseDay.getText().toString());
                    intent.putExtra("location", binding.CourseLocation.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
//        binding.addCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("title",binding.Title.getText().toString());
//                intent.putExtra("time",binding.Time.getText().toString());
//                intent.putExtra("instructor",binding.Instructor.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
    }
}