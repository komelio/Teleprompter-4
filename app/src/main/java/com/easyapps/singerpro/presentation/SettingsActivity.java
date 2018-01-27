package com.easyapps.singerpro.presentation;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.easyapps.singerpro.presentation.fragments.TimerPreferenceFragment;
import com.easyapps.singerpro.presentation.helper.ActivityUtils;
import com.easyapps.teleprompter.R;

/**
 * Created by daniel on 08/09/2016.
 * Settings activity for each lyric. The file name is received by parameter.
 */
public class SettingsActivity extends Activity {
    private String mLyricName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLyricName = ActivityUtils.getFileNameParameter(getIntent());
        if (mLyricName == null)
            throw new RuntimeException("File not found.");

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                TimerPreferenceFragment.newInstance(mLyricName)).commit();
    }

    @Override
    public void onBackPressed() {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        int orientation = getResources().getConfiguration().orientation;

        if (isTablet && orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActivityUtils.backToMain(this);
        } else {
            ActivityUtils.backToMaintainLyric(this, mLyricName);
        }
    }
}