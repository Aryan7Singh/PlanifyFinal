package com.example.planify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planify.databinding.SingleRecvBinding;
import com.example.planify.databinding.ExamRvBinding;

public class RVExamAdapter extends ListAdapter<Exam, RVExamAdapter.ViewHolder> {
    public RVExamAdapter() {
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Exam> CALLBACK = new DiffUtil.ItemCallback<Exam>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exam oldItem, @NonNull Exam newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exam oldItem, @NonNull Exam newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getTime().equals(newItem.getTime())
                    && oldItem.getDate().equals(newItem.getDate()) && oldItem.getLocation().equals(newItem.getLocation())
                    && oldItem.getDetails().equals(newItem.getDetails());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_rv, parent, false);
        //Adapter --> compatible with recycle view and takes care of activity display with recycler view list items
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exam exam = getItem(position);
        holder.binding.examNameRv.setText(exam.getName());
        holder.binding.dateRV.setText(exam.getDate());
        holder.binding.timeRV.setText(exam.getTime());
        holder.binding.locationRV.setText(exam.getLocation());
        holder.binding.detailsRV.setText(exam.getDetails());
    }
    public Exam getExam(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ExamRvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ExamRvBinding.bind(itemView);
        }
    }
}
