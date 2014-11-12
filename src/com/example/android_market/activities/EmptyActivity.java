package com.example.android_market.activities;

import android.app.Activity;
import android.os.Bundle;
import com.example.android_market.R;

public class EmptyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty);
    }
}
