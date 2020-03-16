package com.mafyusuf.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private boolean running;
  private long second=0;
private  boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate( savedInstanceState );
        if (savedInstanceState != null) {
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean( "wasRunning" );
        }
        runed();
        setContentView( R.layout.activity_main );
    }

    protected void onPause() {
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running=true;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState( savedInstanceState );
        savedInstanceState.putInt( "second", (int) second );
        savedInstanceState.putBoolean( "running", running );
        savedInstanceState.putBoolean("wasRunning", wasRunning );

    }

    public void start(View view) {
        running = true;
        String st ="Start";
        Toast.makeText( MainActivity.this,st,Toast.LENGTH_SHORT ).show();

    }

    public void stop(View view) {
        running = false;
        String st ="Stop";
        Toast.makeText( MainActivity.this,st,Toast.LENGTH_SHORT ).show();
    }

    public void reset(View view) {
        running = false;
        second=0;
        String st ="Reset";
        Toast.makeText( MainActivity.this,st,Toast.LENGTH_SHORT ).show();
    }
    public void runed(){

        final Handler handler = new Handler(  );
        handler.post( new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView)findViewById( R.id.textView );
                int hour = (int)second/3600;
                int sec,min;
                sec=(int)second%60;
                min=(int)(second%3600)/60;

                String st =String.format( "%d:%02d:%02d", hour,min,sec);
                tv.setText( st );
                if(running){
                    second++;
                }
                handler.postDelayed(this,1000 );
            }
        } );

    }


}
