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
import com.example.planify.databinding.AssignmentsRvBinding;


public class RVAssignmentAdapter extends ListAdapter<Assignment, RVAssignmentAdapter.ViewHolder> {
    public RVAssignmentAdapter() {
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Assignment> CALLBACK = new DiffUtil.ItemCallback<Assignment>() {
        @Override
        public boolean areItemsTheSame(@NonNull Assignment oldItem, @NonNull Assignment newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Assignment oldItem, @NonNull Assignment newItem) {
            return oldItem.getAssignName().equals(newItem.getAssignName()) &&
                    oldItem.getDueDate().equals(newItem.getDueDate()) &&
                    oldItem.getClassAssoc().equals(newItem.getClassAssoc()) &&
                    oldItem.getClassAssoc().equals(newItem.getClassAssoc()) &&
                    oldItem.getDayRepeat().equals(newItem.getDayRepeat()) &&
                    oldItem.getLocRm().equals(newItem.getLocRm());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent1, int viewType) {
        View view = LayoutInflater.from(parent1.getContext()).inflate(R.layout.assignments_rv, parent1, false);
        //Adapter --> compatible with recycle view and takes care of activity display with recycler view list items
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder1, int position) {
        Assignment assignment = getItem(position);
        holder1.binding.assignNameRv.setText(assignment.getAssignName());
        holder1.binding.dueDateRv.setText(assignment.getDueDate());
        holder1.binding.classAssocRv.setText(assignment.getClassAssoc());
        holder1.binding.dayRepeatRv.setText(assignment.getDayRepeat());
        holder1.binding.locationRV.setText(assignment.getLocRm());
    }
    public Assignment getAssignment(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        AssignmentsRvBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AssignmentsRvBinding.bind(itemView);
        }
    }
}
