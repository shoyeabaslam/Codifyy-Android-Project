package com.example.codifyy.videosview.PlayYoutubeVideo.VideoModelPack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.codifyy.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class OnShotVideoActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    private static final String API_KEY = "AIzaSyBVEm5OmtveW9vB5iq9tt9tkSi4otQAf0U";
    public String youtubeVideoId  = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_shot_video);
        youTubePlayerView = findViewById(R.id.youtubePlayerViewId);
        Intent intent = getIntent();
        youtubeVideoId = intent.getStringExtra("youtubeVideoId");


        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    if(!youtubeVideoId.equals("")){
                        youTubePlayer.cueVideo(youtubeVideoId);
                    }
                }
                youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean b) {
                        youTubePlayer.play();
                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


        //goback imageview
        ImageView backImage = findViewById(R.id.goback);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}