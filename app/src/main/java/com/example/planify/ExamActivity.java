package com.example.planify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.planify.databinding.ActivityExamBinding;
import com.example.planify.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ExamActivity extends AppCompatActivity {
    ActivityExamBinding binding;
    private ExamViewModel examViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); //showing content; setting screen to outermost view
        examViewModel = new ViewModelProvider(this, (ViewModelProvider.AndroidViewModelFactory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ExamViewModel.class);

        binding.floatingActionButtonExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View raw) {
                Intent intent = new Intent(ExamActivity.this, ExamDataInsertActivity.class);
                intent.putExtra("type", "addMode");
                startActivityForResult(intent, 1);
            }
        });
        binding.RV.setLayoutManager(new LinearLayoutManager(this));
        binding.RV.setHasFixedSize(true);
        RVExamAdapter adapter = new RVExamAdapter();
        binding.RV.setAdapter(adapter);

        //Fetching all exam data
        examViewModel.getAllExams().observe(this, new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                adapter.submitList(exams);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==ItemTouchHelper.RIGHT) {
                    examViewModel.delete(adapter.getExam(viewHolder.getAdapterPosition()));
                    Toast.makeText(ExamActivity.this, "Exam deleted!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ExamActivity.this, DataInsertActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("ExamName", adapter.getExam(viewHolder.getAdapterPosition()).getName());
                    intent.putExtra("Date", adapter.getExam(viewHolder.getAdapterPosition()).getDate());
                    intent.putExtra("Time", adapter.getExam(viewHolder.getAdapterPosition()).getTime());
                    intent.putExtra("Location", adapter.getExam(viewHolder.getAdapterPosition()).getLocation());
                    intent.putExtra("ExtraDetails", adapter.getExam(viewHolder.getAdapterPosition()).getDetails());
                    intent.putExtra("id", adapter.getExam(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent, 2);
                }
            }
        }).attachToRecyclerView(binding.RV);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_exams);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.bottom_exams){
                return true;
            }
            else if(item.getItemId() == R.id.bottom_courses){
                startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            else if(item.getItemId() == R.id.bottom_assignments){
                startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            else if(item.getItemId() == R.id.bottom_assignments){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            /*switch (item.getItemId()) {
                case R.id.bottom_exams:
                    return true;
                case R.id.bottom_courses:
                    startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_settings:
                    startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }*/
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String name = data.getStringExtra("name");
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");
            String location = data.getStringExtra("location");
            String details = data.getStringExtra("details");
            Exam exam = new Exam(name, date, time, location, details);
            examViewModel.insert(exam);
            Toast.makeText(this, "Exam added!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == 2) {
            String name = data.getStringExtra("name");
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");
            String location = data.getStringExtra("location");
            String details = data.getStringExtra("details");
            Exam exam = new Exam(name, date, time, location, details);
            exam.setId(data.getIntExtra("id", 0));
            examViewModel.update(exam);
            Toast.makeText(this, "Exam updated!", Toast.LENGTH_SHORT).show();
        }
    }
}