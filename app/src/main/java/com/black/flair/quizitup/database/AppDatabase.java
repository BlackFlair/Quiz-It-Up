package com.black.flair.quizitup.database;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.black.flair.quizitup.data.TaskEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    abstract TaskDao taskDao;

    private static AppDatabase INSTANCE = null;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static AppDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "State")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    executor.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            populateFromDB(context.getAssets(), INSTANCE.taskDao());
                                        }
                                    });
                                }
                            }).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }



    private static void populateFromDB(AssetManager assetManager, TaskDao taskDao){

        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String json = "";

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open("state-capital.json")));
            String mLine;
            while ((mLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(mLine);
            }
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {

                }
            }
        }
        try {
            JSONObject states = new JSONObject(json);
            JSONObject section = states.getJSONObject("sections");
            populateFromJSON(section.getJSONArray("States (A-L)"), taskDao);
            populateFromJSON(section.getJSONArray("States (M-Z)"), taskDao);
            populateFromJSON(section.getJSONArray("Union Territories"), taskDao);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void populateFromJSON(JSONArray states, TaskDao taskdao){
        try{
            for (int i=0; i<states.length(); i++){
                JSONObject stateData = states.getJSONObject(i);
                String stateName = stateData.getString("key");
                String stateCapital = stateData.getString("val");
                taskDao.insertTask(new TaskEntry(stateName,stateCapital));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}
