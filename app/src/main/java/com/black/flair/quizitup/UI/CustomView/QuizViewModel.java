package com.black.flair.quizitup.UI.CustomView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.black.flair.quizitup.data.TaskEntry;
import com.black.flair.quizitup.database.StateRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuizViewModel extends AndroidViewModel {

    public MutableLiveData<List<TaskEntry>> states = new MutableLiveData<>();
    private StateRepository stateRepository;

    public QuizViewModel(@NonNull Application application){

        super(application);
        stateRepository = StateRepository.getStateRepository(application);
        loadGame();
    }

    private void loadGame(){

        try{
            states.postValue(stateRepository.getQuizStates().get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public void refreshGame(){

        loadGame();
    }
}
