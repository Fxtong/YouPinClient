package com.online.youpinclient.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.online.youpinclient.R;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.appearance,R.anim.stealth);
    }
}
