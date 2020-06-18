package com.black.flair.quizitup.UI;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.black.flair.quizitup.R;


public class SettingsClass extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);
    }
}
