package com.example.android_market.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.android_market.R;

public class AboutUsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        ((TextView)findViewById(R.id.tv_about_info))
                .setText(getResources().getString(R.string.amazon_info));

        TextView tvSite = (TextView)findViewById(R.id.about_site);
        tvSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/"));
                startActivity(intent);
            }
        });
    }
}
