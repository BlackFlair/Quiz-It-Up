package com.black.flair.quizitup.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "States")
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    public long stateId;

    @NonNull
    @ColumnInfo(name = "State")
    private String stateName;

    @NonNull
    @ColumnInfo(name = "Capital")
    private String capitalName;

    public TaskEntry(){}

    @Ignore
    public TaskEntry(@NonNull String state_Name, @NonNull String capital_Name){

        stateName = state_Name;
        capitalName = capital_Name;
    }

    public TaskEntry(long state_ID, @NonNull String state_Name,@NonNull String capital_Name) {

        stateId = state_ID;
        stateName = state_Name;
        capitalName = capital_Name;
    }

    public long getStateID() {

        return stateId;
    }

    public void setStateID(long state_Id) {

        stateId = state_Id;
    }

    public String getStateName() {

        return stateName;
    }

    public void setStateName(String state_Name) {

        stateName = state_Name;
    }

    public String getCapitalName() {

        return capitalName;
    }

    public void setCapitalName(String capital_Name) {

        capitalName = capital_Name;
    }

    public boolean equals(TaskEntry te) {

        return (stateName == te.getStateName() && capitalName == te.getCapitalName());
    }


}
