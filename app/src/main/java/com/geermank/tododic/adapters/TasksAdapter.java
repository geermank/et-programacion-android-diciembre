package com.geermank.tododic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.geermank.tododic.R;
import com.geermank.tododic.models.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    private List<Task> tasks;

    private OnTaskClickListener clickListener;
    private ListItemClickListener<Task> itemClickListener;

    public TasksAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setOnTaskClickListener(OnTaskClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnItemClickListener(ListItemClickListener<Task> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAssignedTo;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_task_title);
            tvAssignedTo = itemView.findViewById(R.id.tv_task_assigned_to);
        }

        public void bind(Task task) {
            tvTitle.setText(task.getTitle());
            tvAssignedTo.setText(task.getAssignedTo());

            if (task.isFinished()) {
                int color = ContextCompat.getColor(itemView.getContext(), R.color.teal_200);
                tvAssignedTo.setTextColor(color);
            } else {
                int color = ContextCompat.getColor(itemView.getContext(), R.color.black);
                tvAssignedTo.setTextColor(color);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onTaskClick(task);
                    }
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(task);
                    }
                }
            });
        }
    }

}
