package com.black.flair.quizitup.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.black.flair.quizitup.data.TaskEntry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StateRepository {

    private static StateRepository REPOSITORY = null;

    private TaskDao mTaskDao;
    private int PAGE_SIZE = 15;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private StateRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
    }

    public static StateRepository getStateRepository(Application application){
        if (REPOSITORY == null) {
            synchronized (StateRepository.class){
                if (REPOSITORY == null){
                    REPOSITORY = new StateRepository(application);
                }
            }
        }
        return REPOSITORY;
    }

    public void insertState(final TaskEntry state){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTaskDao.insertTask(state);
            }
        });
    }

    public void deleteState(final TaskEntry state){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTaskDao.deleteTask(state);
            }
        });
    }

    public void updateState(final TaskEntry state){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTaskDao.updateTask(state);
            }
        });
    }

    public LiveData<PagedList<TaskEntry>> getAllStates(){
        return new LivePagedListBuilder<>(
                mTaskDao.getAllStates(),
                PAGE_SIZE
        ).build();
    }
}
