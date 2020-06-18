package com.black.flair.quizitup.database;

import android.app.Application;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.black.flair.quizitup.data.TaskEntry;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
                mTaskDao.getAllTasks(),
                PAGE_SIZE
        ).build();
    }

    public Future<List<TaskEntry>> getQuizStates() {
        Callable<List<TaskEntry>> callable = new Callable<List<TaskEntry>>() {
            @Override
            public List<TaskEntry> call() throws Exception {
                return mTaskDao.getQuizStates();
            }
        };
        return executor.submit(callable);
    }

    @WorkerThread
    public TaskEntry getRandomState() {
        return mTaskDao.getRandomState();
    }


    public LiveData<PagedList<TaskEntry>> getStatesInSortedOrder(String sortOrder){
        return new LivePagedListBuilder<>(
                mTaskDao.getSortedStates(constructQuery(sortOrder)),
                PAGE_SIZE
        ).build();
    }

    public SupportSQLiteQuery constructQuery(String sortBy){
        String query = "SELECT * FROM State ORDER BY "+sortBy+" ASC";
        return new SimpleSQLiteQuery(query);
    }
}
