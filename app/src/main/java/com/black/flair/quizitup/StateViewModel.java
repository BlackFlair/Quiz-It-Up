package com.black.flair.quizitup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.black.flair.quizitup.data.TaskEntry;
import com.black.flair.quizitup.database.StateRepository;


public class StateViewModel extends AndroidViewModel {

    private StateRepository stateRepository;
    public LiveData<PagedList<TaskEntry>> pagedListLiveData;

    public StateViewModel(@NonNull Application application) {
        super(application);
        stateRepository = StateRepository.getStateRepository(application);
        pagedListLiveData = stateRepository.getAllStates();
    }
    public void insertState(TaskEntry state){
        stateRepository.insertState(state);
    }

    public void updateState(TaskEntry state){
        stateRepository.updateState(state);
    }

    public void deleteState(TaskEntry state){
        stateRepository.deleteState(state);
    }
}