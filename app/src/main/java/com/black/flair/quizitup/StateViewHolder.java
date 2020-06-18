package com.black.flair.quizitup;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.black.flair.quizitup.R;
import com.black.flair.quizitup.data.TaskEntry;

public class StateViewHolder extends RecyclerView.ViewHolder {

    private TextView stateTextView,capitalTextView;


    public StateViewHolder(@NonNull View itemView) {
        super(itemView);
        stateTextView = itemView.findViewById(R.id.stateTV);
        capitalTextView = itemView.findViewById(R.id.capitalTV);
    }

    public void bind(TaskEntry state){
        stateTextView.setText(state.getStateName());
        capitalTextView.setText(state.getCapitalName());
    }
}