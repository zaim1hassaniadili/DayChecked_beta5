package com.udemy_3.daychecked_beta5.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.udemy_3.daychecked_beta5.R;

import com.udemy_3.daychecked_beta5.Tables.Task;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.DayHolder> {
    OnItemClickListener listener;
    OnCheckboxClickListener checkboxClickListener;

    public TaskAdapter() {
        super(DIFF_CALLBACK );
    }

    public static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getNameTask().equals(newItem.getNameTask());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return   oldItem.getNameTask().equals(newItem.getNameTask()) && oldItem.getColor().equals(newItem.getColor());
        }
    };



    @NonNull
    @Override
    public DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);

        return new DayHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayHolder holder, int position) {
            Task currentTask= getItem(position);
            holder.textViewName.setText(currentTask.getNameTask());
            holder.relativeLayout.setBackgroundColor(Color.parseColor(currentTask.getColor()));
            if(currentTask.isCheckedTask()){
                holder.checkBox.setChecked(true);
            }
            holder.checkBox.setChecked(false);
    }



    public Task getTaskAt(int position){
        return getItem(position);
    }



    class DayHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private CheckBox checkBox;
        public RelativeLayout relativeLayout;

        public DayHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.task_from_row);
            checkBox = itemView.findViewById(R.id.myCheckBox);
            relativeLayout = itemView.findViewById(R.id.relative_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position!= RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkboxClickListener.onCheckboxClick(view);
                }
            });


        }
    }



    public interface OnItemClickListener{
        void onItemClick(Task task);
    }

    public interface OnCheckboxClickListener{
        void onCheckboxClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public void setOnCheckboxClickListener(OnCheckboxClickListener checkboxClickListener){
        this.checkboxClickListener =checkboxClickListener;
    }
}
