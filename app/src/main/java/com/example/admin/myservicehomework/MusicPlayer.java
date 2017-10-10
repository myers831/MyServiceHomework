package com.example.admin.myservicehomework;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;

public class MusicPlayer extends AppCompatActivity {
    private static final String NOTIFICATION_FRAG_TAG = "notificationFragTag";
    public static MediaPlayer mediaPlayer;
    public static int path;
    RecyclerView rvMusicList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    List<Music> musicList = new ArrayList<>();
    private MusicListAdapter musicListAdapter;
    ItemTouchHelper itemTouchHelper;
    private Intent alarmIntent;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    public static String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        rvMusicList = (RecyclerView) findViewById(R.id.rvMusicList);

        path = R.raw.trap_queen;
        mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ACTION_STATE_SWIPE, LEFT );
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            public void setAlarm(int i){
                alarmIntent = new Intent(musicBoundService, Alarm.class);
                songName = musicList.get(i).getName() + " Selected";
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);
                alarmManager = (AlarmManager) getSystemService(musicBoundService.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    switch (viewHolder.getAdapterPosition()){
                        case 0:
                            path = R.raw.im_the_plug;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(0);
                            break;
                        case 1:
                            path = R.raw.a_tale_of_two_cities;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(1);
                            break;
                        case 2:
                            path = R.raw.big_rings;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(2);
                            break;
                        case 3:
                            path = R.raw.day_n_nite;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(3);
                            break;
                        case 4:
                            path = R.raw.low_life;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(4);
                            break;
                        case 5:
                            path = R.raw.ny_state_of_mind;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(5);
                            break;
                        case 6:
                            path = R.raw.rambo;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(6);
                            break;
                        case 7:
                            path = R.raw.shabba;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(7);
                            break;
                        case 8:
                            path = R.raw.teach_me_how_to_dougie;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(8);
                            break;
                        case 9:
                            path = R.raw.trap_queen;
                            mediaPlayer = MediaPlayer.create(getApplicationContext(),path);

                            setAlarm(9);
                            break;
                    }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    MusicBoundService musicBoundService;
    boolean isBound;

    public void musicPlayer(View view) throws IOException {
        Intent boundIntent = new Intent(this, MusicBoundService.class);
        switch (view.getId()){

            case R.id.btnPlay:
                mediaPlayer.start();
                break;

            case R.id.btnPause:
               mediaPlayer.pause();
                break;

            case R.id.btnStop:
                mediaPlayer.stop();
                mediaPlayer.prepare();
                break;

            case R.id.btnBoundService:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnAddSongs:
                if(isBound){
                    musicList = musicBoundService.getMusicList();
                    musicListAdapter = new MusicListAdapter(musicList, this);
                    rvMusicList.setAdapter(musicListAdapter);
                    rvMusicList.setLayoutManager(layoutManager);
                    rvMusicList.setItemAnimator(itemAnimator);
                    itemTouchHelper.attachToRecyclerView(rvMusicList);
                }
                break;

            case R.id.btnUnBoundService:
                if(isBound){
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) iBinder;
            musicBoundService = myBinder.getService();

            isBound = true;
            musicBoundService.initData();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
