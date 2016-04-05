package com.example.admin.workout;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.start_button:
                onClickStart(view);
                break;
            case R.id.stop_button:
                onClickStop(view);
                break;
            case R.id.reset_button:
                onClickReset(view);
                break;
        }
    }
    public StopwatchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // gets variables from bundle after the activity is recreated
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View layout = inflater.inflate(R.layout.fragment_stopwatch, container,false);
        Button startButton = (Button) layout.findViewById(R.id.start_button);
        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);


        runTimer(layout);
        return layout;
    }
    private int seconds= 0;
    private boolean running;
    private boolean wasRunning;



    //save varibles
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }



    // onPause helps the app to remember variables when the app isnt in the foreground
    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    // onResume helps the app to remember variables when the app comes back in the foreground
    @Override
    public void onResume()
    {
        super.onResume();
        if(wasRunning)
        {
            running = true;
        }
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view)
    {
        running = false;
    }

    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }
    // handler updates the Textview after a schedule time limit of 1 second
    private void runTimer(View view)
    {   final Handler handler = new Handler();
        final TextView timeView = (TextView)view.findViewById(R.id.time_view);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/ 3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });



    }

}
