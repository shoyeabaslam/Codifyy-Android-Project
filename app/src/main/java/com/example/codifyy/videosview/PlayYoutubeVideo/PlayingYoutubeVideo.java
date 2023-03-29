package com.example.codifyy.videosview.PlayYoutubeVideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codifyy.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayingYoutubeVideo extends YouTubeBaseActivity {
    private static final String API_KEY = "AIzaSyBVEm5OmtveW9vB5iq9tt9tkSi4otQAf0U"; //api
    Button pre,next,jump;
    TextView currentVideoNumber;
    EditText getVideoNumber;
    YouTubePlayerView youTubePlayerView;

    public String playlistId="";
    public String videoId="";

    public int totalVideos;
    public int indexNumber;
    public int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_youtube_video);
        pre = findViewById(R.id.preButton);
        next = findViewById(R.id.nextButton);
        jump = findViewById(R.id.jumpButton);
        getVideoNumber = findViewById(R.id.videoNumberId);
        currentVideoNumber = findViewById(R.id.currentVideoNumber);
        youTubePlayerView = findViewById(R.id.youtubePlayerView);
        setUpVideoId();

        setYoutuberPlayer();



        //goback imageview
        ImageView backImage = findViewById(R.id.goback);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void setUpVideoId() {
        Intent intent = getIntent();
        playlistId = intent.getStringExtra("PlaylistVideoIdIs");
        indexNumber = intent.getIntExtra("position",0);
        totalVideos = intent.getIntExtra("Total",0);
    }

    private void setYoutuberPlayer() {
        jump.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        getVideoNumber.setVisibility(View.GONE);
        pre.setVisibility(View.GONE);
        result = indexNumber+1;

        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    if(!playlistId.equals("")){
                        youTubePlayer.loadPlaylist(playlistId,indexNumber,0);
                    }
                    else {
                        Toast.makeText(PlayingYoutubeVideo.this, "Sorry!!! Error", Toast.LENGTH_SHORT).show();
                    }
                    jump.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            if(getVideoNumber.getText().toString().equals("")){
                                Toast.makeText(PlayingYoutubeVideo.this, "Enter Video Number", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                int index1 = Integer.parseInt(getVideoNumber.getText().toString());
                                if(index1>0 && index1<=totalVideos) {
                                    getVideoNumber.setText("");
                                    result = index1;
                                    update(result);
                                    youTubePlayer.loadPlaylist(playlistId, index1 - 1, 0);
                                }
                                else{
                                    Toast.makeText(PlayingYoutubeVideo.this,"Sorry Out-of-Index",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                    pre.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(result>1) {
                                --result;
                                update(result);
                                youTubePlayer.previous();
                            }
                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(result<totalVideos){
                                ++result;
                                update(result);
                                youTubePlayer.next();

                            }
                        }
                    });

                }
                youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean b) {
                        youTubePlayer.play();
                    }
                });
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                        jump.setVisibility(View.GONE);
                        next.setVisibility(View.GONE);
                        getVideoNumber.setVisibility(View.GONE);
                        pre.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoaded(String s) {
                        jump.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        getVideoNumber.setVisibility(View.VISIBLE);
                        pre.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {

                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
                update(indexNumber+1);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
                Toast.makeText(PlayingYoutubeVideo.this, "Sorry!!! Error "+result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(int result) {
        currentVideoNumber.setText(result+"/"+totalVideos);
    }
}