package io.github.mobileteacher.apisample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        //videoView.se

        String uri = "android.resource://" + getPackageName() + "/" + R.raw.coelho;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();
    }
}
