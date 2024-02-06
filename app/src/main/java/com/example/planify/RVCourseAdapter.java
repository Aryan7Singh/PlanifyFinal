package com.example.planify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planify.databinding.ClassesRvBinding;


public class RVCourseAdapter extends ListAdapter<Course, RVCourseAdapter.ViewHolder> {
    public RVCourseAdapter() {
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Course> CALLBACK = new DiffUtil.ItemCallback<Course>() {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getTime().equals(newItem.getTime()) &&
                    oldItem.getInstructor().equals(newItem.getInstructor());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent1, int viewType) {
        View view = LayoutInflater.from(parent1.getContext()).inflate(R.layout.classes_rv, parent1, false);
        //Adapter --> compatible with recycle view and takes care of activity display with recycler view list items
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder1, int position) {
        Course course = getItem(position);
        holder1.binding.courseNameRv.setText(course.getTitle());
        holder1.binding.timeRV.setText(course.getTime());
        holder1.binding.instructorRV.setText(course.getInstructor());
        holder1.binding.dayRV.setText(course.getDayRepeat());
        holder1.binding.locationRV.setText(course.getLocationRmNum());
//        holder1.binding.titleRV.setText(course.getTitle());
//        holder1.binding.timeRV.setText(course.getTime());
//        holder1.binding.instructorRV.setText(course.getInstructor());
//        holder1.binding


    }
    public Course getCourse(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ClassesRvBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ClassesRvBinding.bind(itemView);
        }
    }
}
