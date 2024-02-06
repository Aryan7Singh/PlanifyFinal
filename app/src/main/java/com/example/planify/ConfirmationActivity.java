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

import com.example.planify.databinding.ActivityClassesBinding;
import com.example.planify.databinding.ActivityCourseDataInsertBinding;

public class ConfirmationActivity extends AppCompatActivity {
    ActivityClassesBinding binding;
    private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityClassesBinding.inflate(getLayoutInflater());
//        binding.RVClasses.setLayoutManager(new LinearLayoutManager(this));
//        binding.RVClasses.setHasFixedSize(true);
//        RVCourseAdapter courseAdapter = new RVCourseAdapter();
//        binding.RVClasses.setAdapter(courseAdapter); //Adapter feeds underlying data to view? (ViewModel -> View)?
//        courseViewModel = new ViewModelProvider(this, (ViewModelProvider.AndroidViewModelFactory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CourseViewModel.class); //casting problem?

        String action = getIntent().getStringExtra("act");
//        String ultimateIntent = getIntent().getStringExtra("ultimateIntent");
        if (action.equals("confirm")) {
            if (Globals.actionCourse == 0) {
//                courseViewModel.delete(Globals.relCourse);
                Intent intent = new Intent();
                intent.putExtra("continue", "t");
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Intent intent1 = new Intent(ConfirmationActivity.this, CourseDataInsertActivity.class);
                intent1.putExtra("type", "update");
                intent1.putExtra("title", Globals.relCourse.getTitle());
                intent1.putExtra("time", Globals.relCourse.getTime());
                intent1.putExtra("instructor", Globals.relCourse.getInstructor());
                intent1.putExtra("day", Globals.relCourse.getDayRepeat());
                intent1.putExtra("location", Globals.relCourse.getLocationRmNum());
                intent1.putExtra("id", Globals.relCourse.getId());
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

//        setContentView(binding.getRoot());
//        binding =
//
//        String type = getIntent().getStringExtra("type");
//        if (type.equals("update")) {
//            setTitle("Update");
//            binding.Title.setText(getIntent().getStringExtra("title"));
//            binding.Time.setText(getIntent().getStringExtra("time"));
//            binding.Instructor.setText(getIntent().getStringExtra("instructor"));
//            binding.Day.setText(getIntent().getStringExtra("day"));
//            binding.Location.setText(getIntent().getStringExtra("location"));
//            int id = getIntent().getIntExtra("id", 0);
//            binding.addCourse.setText("Update Course");
//            binding.addCourse.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.putExtra("title", binding.Title.getText().toString());
//                    intent.putExtra("time", binding.Time.getText().toString());
//                    intent.putExtra("instructor", binding.Instructor.getText().toString());
//                    intent.putExtra("day", binding.Day.getText().toString());
//                    intent.putExtra("location", binding.Location.getText().toString());
//                    intent.putExtra("id", id);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            });
//        } else {
//            setTitle("Add Mode");
//            //add --> referencing button widget id
//            binding.addCourse.setText("Add Course");
//            binding.addCourse.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.putExtra("title", binding.Title.getText().toString());
//                    intent.putExtra("time", binding.Time.getText().toString());
//                    intent.putExtra("instructor", binding.Instructor.getText().toString());
//                    intent.putExtra("day", binding.Day.getText().toString());
//                    intent.putExtra("location", binding.Location.getText().toString());
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            });
//        }
////        binding.addCourse.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent();
////                intent.putExtra("title",binding.Title.getText().toString());
////                intent.putExtra("time",binding.Time.getText().toString());
////                intent.putExtra("instructor",binding.Instructor.getText().toString());
////                setResult(RESULT_OK, intent);
////                finish();
////            }
////        });
//    }

}
