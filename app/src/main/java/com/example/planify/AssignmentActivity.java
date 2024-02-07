package com.example.planify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.planify.databinding.ActivityAssignmentsBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AssignmentActivity extends AppCompatActivity {
    ActivityAssignmentsBinding binding;
    private AssignmentViewModel assignmentViewModel;
//    private int action = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignmentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        assignmentViewModel = new ViewModelProvider(this, (ViewModelProvider.AndroidViewModelFactory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AssignmentViewModel.class); //casting problem?

//        taskViewModel = new ViewModelProvider(this, (ViewModelProvider.AndroidViewModelFactory)
//                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);
        binding.btnConfirmAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                action = 1;
                Log.d("CONFIRMATION", "confirm");
                Intent intent = new Intent(AssignmentActivity.this, ConfirmationActivityAssign.class);
                intent.putExtra("act", "confirm");
                startActivityForResult(intent, (Globals.actionAssignment == 0) ? 3 : 2);
            }
        });
        binding.btnCancelAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                action = 0;
                Log.d("CONFIRMATION", "cancel");
                Intent intent = new Intent(AssignmentActivity.this, ConfirmationActivityAssign.class);
                intent.putExtra("act", "cancel");
                startActivityForResult(intent, (Globals.actionAssignment == 0) ? 3 : 2);
            }
        });
        binding.addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssignmentActivity.this, AssignmentDataInsertActivity.class);
                intent.putExtra("type", "addMode");
                startActivityForResult(intent, 1);
            }
        });

        binding.ConfirmationAssignmentLayout.setVisibility(View.GONE);
        binding.RVAssignments.setLayoutManager(new LinearLayoutManager(this));
        binding.RVAssignments.setHasFixedSize(true);
        RVAssignmentAdapter assignmentAdapter = new RVAssignmentAdapter();
        binding.RVAssignments.setAdapter(assignmentAdapter); //Adapter feeds underlying data to view? (ViewModel -> View)?

        assignmentViewModel.getAllAssignments().observe(this, new Observer<List<Assignment>>() {
            @Override
            public void onChanged(List<Assignment> assignments) {
                assignmentAdapter.submitList(assignments);
            }
        }); 

        binding.sortByTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.assignmentViewType = 1;
                assignmentViewModel.getAllAssignments().observe(AssignmentActivity.this, new Observer<List<Assignment>>() {
                    @Override
                    public void onChanged(List<Assignment> assignments) {
                        if (assignments != null) {
                            assignmentAdapter.submitList(assignments);
                        }
                    }
                });
            }
        });

        binding.defaultOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.assignmentViewType = 0;
                assignmentViewModel.getAllAssignments().observe(AssignmentActivity.this, new Observer<List<Assignment>>() {
                    @Override
                    public void onChanged(List<Assignment> assignments) {
                        if (assignments != null) {
                            assignmentAdapter.submitList(assignments);
                        }
                    }
                });
            }
        });

        binding.sortByTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.assignmentViewType = 2;
                assignmentViewModel.getAllAssignments().observe(AssignmentActivity.this, new Observer<List<Assignment>>() {
                    @Override
                    public void onChanged(List<Assignment> assignments) {
                        if (assignments != null) {
                            assignmentAdapter.submitList(assignments);
                        }
                    }
                });
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Globals.relAssignment = assignmentAdapter.getAssignment(viewHolder.getAdapterPosition());
                binding.ConfirmationAssignmentLayout.setVisibility(View.VISIBLE);
                if (direction==ItemTouchHelper.RIGHT) {
                    binding.dialogMessageAssign.setText("Are you sure you want to proceed with deletion?");
                    Globals.actionAssignment = 0;
                } else {
                    Globals.actionAssignment = 1;
                    binding.dialogMessageAssign.setText("Are you sure you want to proceed with updating?");
                }
            }
        }).attachToRecyclerView(binding.RVAssignments);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_courses);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.bottom_courses){
                return true;
            }
            else if(item.getItemId() == R.id.bottom_tasks){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
            else if(item.getItemId() == R.id.bottom_exams){
                startActivity(new Intent(getApplicationContext(), ExamActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String title = data.getStringExtra("title");
            String time = data.getStringExtra("time");
            String instructor = data.getStringExtra("instructor");
            String day = data.getStringExtra("day");
            String location = data.getStringExtra("location");
            Assignment assignment = new Assignment(title, time, instructor, day, location);
            assignmentViewModel.insert(assignment);
            Toast.makeText(this, "Assignment added!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == 2) {
            if (data.getStringExtra("continue").equals("t")) {
                String title = data.getStringExtra("title");
                String time = data.getStringExtra("time");
                String instructor = data.getStringExtra("instructor");
                String day = data.getStringExtra("day");
                String location = data.getStringExtra("location");
                Assignment assignment = new Assignment(title, time, instructor, day, location);
                assignment.setId(data.getIntExtra("id", 0));
                assignmentViewModel.update(assignment);
                Toast.makeText(this, "Assignment updated!", Toast.LENGTH_SHORT).show();
            }
            binding.ConfirmationAssignmentLayout.setVisibility(View.GONE);
        }
        else if (requestCode == 3) {
            if (data.getStringExtra("continue").equals("t")) {
                assignmentViewModel.delete(Globals.relAssignment);
                Toast.makeText(this, "Assignment deleted!", Toast.LENGTH_SHORT).show();
            }
            binding.ConfirmationAssignmentLayout.setVisibility(View.GONE);
        }
    }
}