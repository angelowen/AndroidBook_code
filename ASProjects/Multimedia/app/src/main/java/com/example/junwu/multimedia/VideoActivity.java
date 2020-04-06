package com.example.junwu.multimedia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        findViewById(R.id.fabChoose).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =
                                new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType( "video/*" );
                        Intent dest = Intent.createChooser(intent, "Select");
                        startActivityForResult(dest, 0);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
