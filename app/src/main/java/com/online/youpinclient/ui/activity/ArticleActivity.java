package com.online.youpinclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.online.youpinclient.R;

/**
 * Created by permanent love on 2017/5/4.
 */

public class ArticleActivity extends AppCompatActivity {

    @Override
    public void startActivity(Intent intent) {

        super.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.appearance,R.anim.stealth);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();


    }
}
