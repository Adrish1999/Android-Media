package com.adrish.playmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

//https://gaana.com/song/chale-aana-1
public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://gaana.com/song/chale-aana-1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        mediaPlayer.prepareAsync();
        //mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.kalimba);

        playButton = findViewById(R.id.Play_Button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    pauseMusic();
                }
                else
                {
                    playMusic();
                }
            }
        });
    }

    public void pauseMusic()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.pause();
            playButton.setText(R.string.play_text);
        }
    }

    public void playMusic()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.start();
            playButton.setText(R.string.pause_text);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null)
        {
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }
}