package com.example.android_market.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.android_market.R;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText etLogin = (EditText)findViewById(R.id.et_login),
                 etPass = (EditText)findViewById(R.id.et_pass);
        TextView tvReg = (TextView)findViewById(R.id.tv_reg),
                 tvForgot = (TextView)findViewById(R.id.tv_forgot),
                 tvAbout = (TextView)findViewById(R.id.tv_about);
        Button   btnGo = (Button)findViewById(R.id.btn_go);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), GoodsActivity.class);
                startActivity(intent);
            }
        });
        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EmptyActivity.class);
                startActivity(intent);
            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EmptyActivity.class);
                startActivity(intent);
            }
        });

        Button btnBucket = (Button)findViewById(R.id.btn_go_bucket);
        btnBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BucketActivity.class));
            }
        });
    }
}
