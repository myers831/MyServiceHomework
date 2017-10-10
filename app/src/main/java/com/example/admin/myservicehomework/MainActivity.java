package com.example.admin.myservicehomework;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    boolean musicPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicPlaying = false;
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startServices(View view) {

        Intent musicServiceIntent = new Intent(this, MusicForegroundService.class);

        switch (view.getId()){

            case R.id.btnStartForegroundService:
                startService(musicServiceIntent);
                break;

            case R.id.btnStopForegroundService:
                stopService(musicServiceIntent);
                break;
        }
    }
}
