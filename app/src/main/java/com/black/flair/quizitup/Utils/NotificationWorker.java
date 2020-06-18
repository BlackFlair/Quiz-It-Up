package com.black.flair.quizitup.Utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.black.flair.quizitup.data.TaskEntry;
import com.black.flair.quizitup.database.StateRepository;

public class NotificationWorker extends Worker {
    private StateRepository stateRepository;
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        stateRepository = StateRepository.getStateRepository((Application) context.getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {
        TaskEntry state  = stateRepository.getRandomState();
        Notifications.getDailyNotification(getApplicationContext(),state);
        return Result.success();
    }
}
