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

import com.example.planify.databinding.ActivityClassesBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ClassesActivity extends AppCompatActivity {
    ActivityClassesBinding binding;
    private CourseViewModel courseViewModel;
//    private int action = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClassesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseViewModel = new ViewModelProvider(this, (ViewModelProvider.AndroidViewModelFactory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CourseViewModel.class); //casting problem?

//        taskViewModel = new ViewModelProvider(this, (ViewModelProvider.AndroidViewModelFactory)
//                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                action = 1;
                Log.d("CONFIRMATION", "confirm");
                Intent intent = new Intent(ClassesActivity.this, ConfirmationActivity.class);
                intent.putExtra("act", "confirm");
                startActivityForResult(intent, (Globals.actionCourse == 0) ? 3 : 2);
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                action = 0;
                Log.d("CONFIRMATION", "cancel");
                Intent intent = new Intent(ClassesActivity.this, ConfirmationActivity.class);
                intent.putExtra("act", "cancel");
                startActivityForResult(intent, (Globals.actionCourse == 0) ? 3 : 2);
            }
        });
        binding.floatingSHOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassesActivity.this, CourseDataInsertActivity.class);
                intent.putExtra("type", "addMode");
                startActivityForResult(intent, 1);
            }
        });

        binding.ConfirmationLayout.setVisibility(View.GONE);
        binding.RVClasses.setLayoutManager(new LinearLayoutManager(this));
        binding.RVClasses.setHasFixedSize(true);
        RVCourseAdapter courseAdapter = new RVCourseAdapter();
        binding.RVClasses.setAdapter(courseAdapter); //Adapter feeds underlying data to view? (ViewModel -> View)?

        //Fetching all task data ---- UNCOMMENT
        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseAdapter.submitList(courses);
            }
        }); //THIS RUNS ON INITIALIZATION!!!!! THIS IS CALLED EVERY TIME THERE IS AN ADDITION, UPDATE, OR DELETION BY THE OTHER METHODS ALREADY?

        binding.sortByTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ClassesActivity.this, CourseDataInsertActivity.class);
//                intent.putExtra("type", "addMode");
//                startActivityForResult(intent, 1);
                Globals.courseViewType = 1;
                courseViewModel.getAllCourses().observe(ClassesActivity.this, new Observer<List<Course>>() {
                    @Override
                    public void onChanged(List<Course> courses) {
                        if (courses != null) {
                            // Now, 'courses' should have the updated list
                            courseAdapter.submitList(courses);
                        }
                    }
                });
                //TEST --> Course Adapter works
//                courseAdapter.submitList(Arrays.asList(new Course("FFF", "FFF", "FFF"), new Course("BBB", "BBB", "BBB")));
                //DEBUGGING AND PRINTING BACKING COURSE LIST CONTENTS TO CONSOLE
//                String ret = "";
//                List<Course> lc = courseViewModel.getAllCourses().getValue();
//                for (Course c: lc) {
//                    ret = ret + c.toString() + "\n";
//                }
//                Log.d("CREATION", "" + ((lc == null) ? "HELLO????" : lc.size()));
//                courseAdapter.submitList(courseViewModel.getAllCourses().getValue());
            }
        });
//
        binding.defaultOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ClassesActivity.this, CourseDataInsertActivity.class);
//                intent.putExtra("type", "addMode");
//                startActivityForResult(intent, 1);
                Globals.courseViewType = 0;
                courseViewModel.getAllCourses().observe(ClassesActivity.this, new Observer<List<Course>>() {
                    @Override
                    public void onChanged(List<Course> courses) {
                        if (courses != null) {
                            // Now, 'courses' should have the updated list
                            courseAdapter.submitList(courses);
                        }
                    }
                });
            }
        });

        binding.sortByTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ClassesActivity.this, CourseDataInsertActivity.class);
//                intent.putExtra("type", "addMode");
//                startActivityForResult(intent, 1);
                Globals.courseViewType = 2;
                courseViewModel.getAllCourses().observe(ClassesActivity.this, new Observer<List<Course>>() {
                    @Override
                    public void onChanged(List<Course> courses) {
                        if (courses != null) {
                            // Now, 'courses' should have the updated list
                            courseAdapter.submitList(courses);
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
                Globals.relCourse = courseAdapter.getCourse(viewHolder.getAdapterPosition());
                binding.ConfirmationLayout.setVisibility(View.VISIBLE);
                if (direction==ItemTouchHelper.RIGHT) {
                    binding.dialogMessage.setText("Are you sure you want to proceed with deletion?");
                    Globals.actionCourse = 0;

//                    String action;

//                    while(true) {
//                        action = getIntent().getStringExtra("act");
//                        if (action == -1) {
////                            continue;
//                        }
//                        if (action == 1) {
//                            action = -1;
//                            binding.ConfirmationLayout.setVisibility(View.GONE);
//                            courseViewModel.delete(courseAdapter.getCourse(viewHolder.getAdapterPosition()));
//                            Toast.makeText(ClassesActivity.this, "Course deleted!", Toast.LENGTH_SHORT).show();
////                            break;
//                        } else if (action == 0) {
//                            action = -1;
//                            binding.ConfirmationLayout.setVisibility(View.GONE);
////                            break;
//                        }
//                    }
                } else {
                    Globals.actionCourse = 1;
                    binding.dialogMessage.setText("Are you sure you want to proceed with updating?");
//                    intent.putExtra("ultimateIntent", "update");
//                    startActivityForResult(intent, 2);
//                    Intent intent = new Intent(ClassesActivity.this, CourseDataInsertActivity.class);
//                    intent.putExtra("type", "update");
//                    intent.putExtra("title", courseAdapter.getCourse(viewHolder.getAdapterPosition()).getTitle());
//                    intent.putExtra("time", courseAdapter.getCourse(viewHolder.getAdapterPosition()).getTime());
//                    intent.putExtra("instructor", courseAdapter.getCourse(viewHolder.getAdapterPosition()).getInstructor());
//                    intent.putExtra("day", courseAdapter.getCourse(viewHolder.getAdapterPosition()).getDayRepeat());
//                    intent.putExtra("location", courseAdapter.getCourse(viewHolder.getAdapterPosition()).getLocationRmNum());
//                    intent.putExtra("id", courseAdapter.getCourse(viewHolder.getAdapterPosition()).getId());
//                    startActivityForResult(intent, 2);
                }
            }
        }).attachToRecyclerView(binding.RVClasses);

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
            /*switch (item.getItemId()) {
                case R.id.bottom_tasks:
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
            String title = data.getStringExtra("title");
            String time = data.getStringExtra("time");
            String instructor = data.getStringExtra("instructor");
            String day = data.getStringExtra("day");
            String location = data.getStringExtra("location");
            Course course = new Course(title, time, instructor, day, location);
            courseViewModel.insert(course);
            Toast.makeText(this, "Course added!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == 2) {
//            Log.d("AUTHORIZE ACTION", data.getStringExtra("continue"));
            if (data.getStringExtra("continue").equals("t")) {
//                Log.d("AUTHORIZE ACTION", data.getStringExtra("title")); // <-- WORKS
                String title = data.getStringExtra("title");
                String time = data.getStringExtra("time");
                String instructor = data.getStringExtra("instructor");
                String day = data.getStringExtra("day");
                String location = data.getStringExtra("location");
                Course course = new Course(title, time, instructor, day, location);
                course.setId(data.getIntExtra("id", 0));
                courseViewModel.update(course);
//                List<Course> lc = courseViewModel.getAllCourses().getValue();
//                if (lc == null) {
//                    Log.d("COURSE UPDATE", "NULL??");
//                }
//                for (Course c : lc) {
//                    Log.d("COURSE UPDATE", c.toString());
//                }
//                courseViewModel.getAllCourses().observe(ClassesActivity.this, new Observer<List<Course>>() {
//                    @Override
//                    public void onChanged(List<Course> courses) {
//                        if (courses != null) {
//                            // Now, 'courses' should have the updated list
//                            courseAdapter.submitList(courses);
//                        }
//                    }
//                });
                Toast.makeText(this, "Course updated!", Toast.LENGTH_SHORT).show();
            }
            binding.ConfirmationLayout.setVisibility(View.GONE);
        }
        else if (requestCode == 3) {
            if (data.getStringExtra("continue").equals("t")) {
                courseViewModel.delete(Globals.relCourse);
                Toast.makeText(this, "Course deleted!", Toast.LENGTH_SHORT).show();
            }
            binding.ConfirmationLayout.setVisibility(View.GONE);
        }
//        Toast.makeText(ClassesActivity.this, "Course deleted!", Toast.LENGTH_SHORT).show();
    }
}

//
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1) {
//            String title = data.getStringExtra("title");
//            String time = data.getStringExtra("time");
//            String instructor = data.getStringExtra("instructor");
//            Course course = new Course(title, time, instructor);
//            courseViewModel.insert(course);
//            Toast.makeText(this, "Course added", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//}