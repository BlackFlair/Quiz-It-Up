package com.black.flair.quizitup.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.black.flair.quizitup.R;
import com.black.flair.quizitup.StateViewModel;
import com.black.flair.quizitup.data.TaskEntry;

public class addActivity extends AppCompatActivity {
    private StateViewModel viewModel;
    public static final String EXTRA_DATA_STATE_NAME = "extra_state_name_to_be_updated";
    public static final String EXTRA_DATA_STATE_CAPITAL = "extra_state_capital_to_be_updated";
    public static final String EXTRA_DATA_ID = "extra_data_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        viewModel = new ViewModelProvider(this).get(StateViewModel.class);


        final EditText stateName = findViewById(R.id.stateET);
        final EditText capitalName = findViewById(R.id.capitalET);
        Button add = findViewById(R.id.addStateBtn);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String statename = extras.getString(EXTRA_DATA_STATE_NAME, "");
            if (!statename.isEmpty()) {
                stateName.setText(statename);

            }
            String stateCapital = extras.getString(EXTRA_DATA_STATE_CAPITAL, "");
            if (!stateCapital.isEmpty()) {
                capitalName.setText(stateCapital);
                capitalName.setSelection(stateCapital.length());
                capitalName.requestFocus();
            }
            add.setText(R.string.save);
        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StateName = stateName.getText().toString();
                String CapitalName = capitalName.getText().toString();

                if (extras != null && extras.containsKey(EXTRA_DATA_ID)) {
                    long stateId = extras.getLong(EXTRA_DATA_ID, -1);
                    TaskEntry state = new TaskEntry(stateId, StateName, CapitalName);
                    viewModel.updateState(state);
                } else {
                    TaskEntry state = new TaskEntry(StateName, CapitalName);
                    viewModel.insertState(state);
                }
                setResult(RESULT_OK);
                finish();
            }

        });
    }
}