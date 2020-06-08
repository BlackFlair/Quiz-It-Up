package com.black.flair.quizitup.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.black.flair.quizitup.data.TaskEntry;

import androidx.paging.DataSource;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM States")
    DataSource.Factory<Integer, TaskEntry> getAllTasks();

    @Insert
    void insertTask(TaskEntry taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    @Delete
    void deleteTask(TaskEntry taskEntry);
}
