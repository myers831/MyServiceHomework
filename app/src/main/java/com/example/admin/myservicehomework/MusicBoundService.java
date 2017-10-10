package com.example.admin.myservicehomework;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MusicBoundService extends Service {
    IBinder iBinder = new MyBinder();
    List<Music> musicList;

    public MusicBoundService() {
    }

    public class MyBinder extends Binder{
        public MusicBoundService getService(){
            return MusicBoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void initData(){
        musicList = new ArrayList<>();
        musicList.add(new Music(R.raw.im_the_plug, "I'm The Plug"));
        musicList.add(new Music(R.raw.a_tale_of_two_cities, "A Tale of Two Cities"));
        musicList.add(new Music(R.raw.big_rings, "Big Rings"));
        musicList.add(new Music(R.raw.day_n_nite, "Day N Nite"));
        musicList.add(new Music(R.raw.low_life, "Low Life"));
        musicList.add(new Music(R.raw.ny_state_of_mind, "NY State of Mind"));
        musicList.add(new Music(R.raw.rambo, "Rambo"));
        musicList.add(new Music(R.raw.shabba, "Shabba"));
        musicList.add(new Music(R.raw.teach_me_how_to_dougie, "Teach Me How To Dougie"));
        musicList.add(new Music(R.raw.trap_queen, "Trap Queen"));
    }

    public List<Music> getMusicList(){
        return musicList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Destroy", "onDestroy: ");
    }
}

