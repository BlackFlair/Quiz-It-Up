package com.black.flair.quizitup.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import com.black.flair.quizitup.data.TaskEntry;

import androidx.paging.DataSource;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

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

    @Query("SELECT DISTINCT * FROM States ORDER BY RANDOM() LIMIT 4")
    List<TaskEntry> getQuizStates();

    @Query("SELECT * FROM States ORDER BY RANDOM() LIMIT 1")
    TaskEntry getRandomState();

    @RawQuery(observedEntities = TaskEntry.class)
    DataSource.Factory<Integer,TaskEntry> getSortedStates(SupportSQLiteQuery sqLiteQuery);

}
