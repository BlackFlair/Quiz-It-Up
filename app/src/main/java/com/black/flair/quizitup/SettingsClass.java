package com.black.flair.quizitup;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;


public class SettingsClass extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);
    }
}
