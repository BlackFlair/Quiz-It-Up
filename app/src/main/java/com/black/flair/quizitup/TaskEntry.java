package com.black.flair.quizitup;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "States")
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

}
