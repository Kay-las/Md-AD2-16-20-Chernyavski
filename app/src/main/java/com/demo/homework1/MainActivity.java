package com.demo.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private  static  final String LOG_KEY = "LOG_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_KEY, "onCreate");
            }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_KEY, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_KEY, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_KEY, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_KEY, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_KEY, "Destroy");
    }
}