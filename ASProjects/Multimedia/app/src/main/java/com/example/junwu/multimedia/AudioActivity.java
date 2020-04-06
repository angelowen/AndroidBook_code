package com.example.junwu.multimedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudioActivity extends AppCompatActivity {

    MediaPlayer player;
    Button btnPlay, btnPause, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_audio);

        findViewById(R.id.fabChoose).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =
                                new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType( "audio/*" );
                        Intent dest = Intent.createChooser(intent, "Select");
                        startActivityForResult(dest, 0);
                    }
                });

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setEnabled(false);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.start();
            }
        });
        btnPause = (Button)findViewById(R.id.btnPause);
        btnPause.setEnabled(false);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.pause();
            }
        });
        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setEnabled(false);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                player.prepareAsync();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        player = new MediaPlayer();
        player = MediaPlayer.create(this, uri);
        btnPlay.setEnabled(true);
        btnPause.setEnabled(true);
        btnStop.setEnabled(true);
    }
}
