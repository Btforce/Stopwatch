package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private Button start_stop;
    private Button reset;
    private Chronometer timer;
    private ConstraintLayout constraintLayout;
    private boolean running = false;
    private long time = 0;

    public static final String TAG = MainActivity.class.getSimpleName();

    //look up the Log class for android
    //list all the levels of logging and when they're used

    //v     verbose         log.v
    //d     debug           log.d
    //i     info            log.i
    //w     warning         log.w
    //e     error           log.e
    //a     assert          log.a


    //phone rotates = onPause(), onStop(), onDestroy(), onCreate(), onStart(), onResume()
    //square button = onPause(), onStop()
    //back button (after square) =  onStart(), onResume()
    //circle button = onPause(), onStop()
    //Relaunch app = onStart(), onResume()
    //Hit back = onPause(), onStop(), onDestroy()


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        wireWidgets();
        setListeners();
    }

    private void setListeners() {
        start_stop.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(!running){
                    if(time == 0){
                        time = SystemClock.elapsedRealtime();
                    }

                    timer.setBase(SystemClock.elapsedRealtime() - time + timer.getBase());
                    timer.start();
                    start_stop.setText("Pause");
                    running = true;
                }
                else{
                    time = SystemClock.elapsedRealtime();
                    timer.stop();
                    start_stop.setText("Start");
                    running = false;

                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.setBase(SystemClock.elapsedRealtime());
                timer.stop();
                start_stop.setText("Start");
                running = false;
                time = SystemClock.elapsedRealtime();

            }
        });
    }

    private void wireWidgets() {
        start_stop =findViewById(R.id.button_main_startstop);
        reset =findViewById(R.id.button_main_reset);
        timer =findViewById(R.id.chronometer_main_stopwatch);
        constraintLayout =findViewById(R.id.constraintLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    //activity is now running


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    //another activity is covering a part of this activity

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    //activity completely hidden


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }



    //activity is finished
}
