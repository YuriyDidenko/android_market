package com.example.android_market.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;
import com.example.android_market.R;

// тут вообще жесть
public class BillingActivity extends Activity {

    private TabHost tabs;
    private TabHost.TabSpec spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing);

        tabs = (TabHost)findViewById(R.id.tabHost);
        Button btnSave = (Button)findViewById(R.id.btn_tab1_save);
        Button btnDelete = (Button)findViewById(R.id.btn_tab1_delete);

        tabs.setup();

        spec = tabs.newTabSpec("tag1");
        spec.setIndicator("Credit Card 1");
        spec.setContent(R.id.tab1);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spec = tabs.newTabSpec("tag2");
                spec.setIndicator("Credit Card 2");
                spec.setContent(R.id.tab2);
                tabs.addTab(spec);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "can't delete", Toast.LENGTH_SHORT).show();
                //tabs.removeView(tabs.getCurrentView());
                //tabs.invalidate();
                tabs.getTabWidget().removeView(tabs.getTabWidget().getChildTabViewAt(0));
                //tabs.invalidate();
                //tabs.setCurrentTab(tabs.getTabWidget().getChildTabViewAt(1).getId());
            }
        });


    }
}
