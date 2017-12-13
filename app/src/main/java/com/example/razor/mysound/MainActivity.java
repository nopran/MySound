package com.example.razor.mysound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mplayer; //meginisialisasi media player
    AudioManager audioManager; //menginisiasi audio manager

    //method yang digunakan untuk menjalankan video
    public void playAudio(View view) {
        mplayer.start();
    }

    //method yang digunakan untuk pause video
    public void pauseAudio(View view) {
        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mplayer = MediaPlayer.create(this, R.raw.al_ikhlas); //inisisasi mediaplayer pada layout

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); //mengambil Service Audio pada android
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //membuat variabel maxvolume dari service stream music
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//membuat variabel curvolume dari service stream music

        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekbar_volume); //inisiasi seekbar volume dari layout
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        //methode ketika seekbar diubah nilai nya
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            //methode mengatur volume dari audio ketika seebar diubah nilainya
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }
        });


        final SeekBar duration = (SeekBar) findViewById(R.id.seekbar_duration);//menginisiasi seekbar duration
        duration.setMax(mplayer.getDuration());//get duration

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                duration.setProgress(mplayer.getCurrentPosition());
            }
        }, 0, 100);


        //seek bar ketika diubah nilainya
        duration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mplayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
