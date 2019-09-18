package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private static final String STATE = "chronometer_state";
    private boolean is_counting = false;
    private long last_number = SystemClock.elapsedRealtime();
    private Button bStart_stop;
    private Button bReset;
    private Chronometer chTime;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Well THAT happened -office");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart_stop = findViewById(R.id.bStart_stop);
        bReset = findViewById(R.id.bReset);
        chTime = findViewById(R.id.chTime);
        bStart_stop.setText("Start");
        chTime.stop();
        last_number = SystemClock.elapsedRealtime();
        Listeners();
        if(savedInstanceState != null){
            chTime.setBase(savedInstanceState.getLong(STATE));
            chTime.start();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    private void Listeners(){
        bStart_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_counting){
                    chTime.stop();
                    last_number = SystemClock.elapsedRealtime();
                    bStart_stop.setText(R.string.start_string);
                    is_counting = false;
                }
                else if(!is_counting){
                    chTime.setBase(chTime.getBase() + (SystemClock.elapsedRealtime() - last_number));
                    chTime.start();

                    bStart_stop.setText(R.string.stop_string);
                    is_counting = true;
                }
            }
        });
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chTime.setBase(SystemClock.elapsedRealtime());
                last_number = SystemClock.elapsedRealtime();
                chTime.stop();
                is_counting = false;
                bStart_stop.setText(R.string.start_string);
            }
        });

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(STATE, chTime.getBase());
    }
}
