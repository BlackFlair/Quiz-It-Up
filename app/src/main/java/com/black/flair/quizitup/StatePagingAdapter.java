package com.black.flair.quizitup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.black.flair.quizitup.data.TaskEntry;


public class StatePagingAdapter extends PagedListAdapter<TaskEntry,StateViewHolder> {
    public StatePagingAdapter() {
        super(DIFF_CALLBACK);
    }
    private ClickListener clickListener;

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.number_list_item,parent,false);

        return new StateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, final int position) {
        final TaskEntry currentItem = getItem(position);
        if(currentItem!= null){
            holder.bind(currentItem);

            if(clickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(v,position);
                    }
                });
            }
        }
    }

    public void setItemOnClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(View view,int position);
    }

    public TaskEntry getStateAtPosition(int position){
        return getItem(position);
    }


    private static DiffUtil.ItemCallback<TaskEntry> DIFF_CALLBACK = new DiffUtil.ItemCallback<TaskEntry>() {
        @Override
        public boolean areItemsTheSame(@NonNull TaskEntry oldItem, @NonNull TaskEntry newItem) {
            return (oldItem.getStateName() == newItem.getStateName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskEntry oldItem, @NonNull TaskEntry newItem) {
            return oldItem.equals(newItem);
        }
    };
}
