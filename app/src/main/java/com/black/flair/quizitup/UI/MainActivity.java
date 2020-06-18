package com.black.flair.quizitup.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.black.flair.quizitup.R;
import com.black.flair.quizitup.UI.CustomView.QuizView;
import com.black.flair.quizitup.UI.CustomView.QuizViewModel;
import com.black.flair.quizitup.UI.CustomView.QuizViewModelFactory;
import com.black.flair.quizitup.data.TaskEntry;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuizView view;
    private QuizViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new QuizViewModelFactory(this.getApplication())).get(QuizViewModel.class);
        view = findViewById(R.id.quizView);

        viewModel.states.observe(this, new Observer<List<TaskEntry>>() {

            @Override
            public void onChanged(List<TaskEntry> states) {
                if (states != null) {
                    if (states.size() == 4) {
                        view.setData(states);
                    } else {
                        Toast.makeText(MainActivity.this, "Add More states", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.setOptionsClickListener(new QuizView.optionsClickListener() {
            @Override
            public void OnClicked(Boolean result) {
                updateResult(result);
            }
        });
    }

    private void updateResult(Boolean result) {
        if (result) {
            Toast.makeText(MainActivity.this, "Correct answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        viewModel.refreshGame();
        view.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_activity:
                Intent intent = new Intent(MainActivity.this, ListRV.class);
                startActivity(intent);
                return true;
            case R.id.settings_fragment:
                Intent intent1 = new Intent(MainActivity.this, Settings.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
