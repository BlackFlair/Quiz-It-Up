package com.black.flair.quizitup.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "States")
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private long stateId;

    @NonNull
    @ColumnInfo(name = "State")
    private String stateName;

    @NonNull
    @ColumnInfo(name = "Capital")
    private String capitalName;



}
