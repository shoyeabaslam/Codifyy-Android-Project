package com.example.codifyy.videosview.PlayYoutubeVideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.codifyy.R;
import com.example.codifyy.videosview.PlayYoutubeVideo.VideoModelPack.OnShotVideoActivity;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeViewActivity1 extends AppCompatActivity {
    public   String Hindi_Playlist_id = "";
    public   String English_Playlist_id  = "";
    public   String Telugu_Playlist_id  = "";
    public   String Video_id = "";
    public   String getItem;
    private String[] getVideoListItem;
    private String[] getHindiPlaylistid;
    private String[] getEnglishPlaylistid;
    private String[] getVideoId;
    private String[] getTeluguPlaylistId;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_view1);
        getVideoListItem = getResources().getStringArray(R.array.ProgrammingListItem);
        getHindiPlaylistid = getResources().getStringArray(R.array.HindiPlaylistId);//playlist id
        getEnglishPlaylistid = getResources().getStringArray(R.array.EnglishPlayListId);//playlist id
        getVideoId = getResources().getStringArray(R.array.OneShotVideoId);//video id
        getTeluguPlaylistId = getResources().getStringArray(R.array.TeluguPlayListId);//telugu id

        Intent getListItem = getIntent();//getting the intent
        getItem = getListItem.getStringExtra("ListItemSelected"); //extracting the string using key;
        Toast.makeText(YoutubeViewActivity1.this, ""+getItem, Toast.LENGTH_SHORT).show();
        getThePlaylistId();


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*0.55));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
        //goback imageview

    }

    private void getThePlaylistId() {

        if(getItem.equals(getVideoListItem[0])){
            Hindi_Playlist_id = getHindiPlaylistid[0];
            English_Playlist_id = getEnglishPlaylistid[0];
            Video_id = getVideoId[0];
            Telugu_Playlist_id = getTeluguPlaylistId[0];

        }
        else if(getItem.equals(getVideoListItem[1])){
            Hindi_Playlist_id = getHindiPlaylistid[1];
            English_Playlist_id = getEnglishPlaylistid[1];
            Video_id = getVideoId[1];
            Telugu_Playlist_id = getTeluguPlaylistId[1];

        }
        else if(getItem.equals(getVideoListItem[2])){
            Hindi_Playlist_id = getHindiPlaylistid[2];
            English_Playlist_id = getEnglishPlaylistid[2];
            Video_id = getVideoId[2];
            Telugu_Playlist_id = getTeluguPlaylistId[2];

        }
        else if(getItem.equals(getVideoListItem[3])){
            Hindi_Playlist_id = getHindiPlaylistid[3];
            English_Playlist_id = getEnglishPlaylistid[3];
            Video_id = getVideoId[3];
            Telugu_Playlist_id = getTeluguPlaylistId[3];
        }
        else if(getItem.equals(getVideoListItem[4])){
            Hindi_Playlist_id = getHindiPlaylistid[4];
            English_Playlist_id = getEnglishPlaylistid[4];
            Video_id = getVideoId[4];
            Telugu_Playlist_id = getTeluguPlaylistId[4];
        }
        else if(getItem.equals(getVideoListItem[5])){
            Hindi_Playlist_id = getHindiPlaylistid[5];
            English_Playlist_id = getEnglishPlaylistid[5];
            Video_id = getVideoId[5];
            Telugu_Playlist_id = getTeluguPlaylistId[5];
        }
        else if(getItem.equals(getVideoListItem[6])){
            Hindi_Playlist_id = getHindiPlaylistid[6];
            English_Playlist_id = getEnglishPlaylistid[6];
            Video_id = getVideoId[6];
            Telugu_Playlist_id = getTeluguPlaylistId[6];
        }
        else if(getItem.equals(getVideoListItem[7])){
            Hindi_Playlist_id = getHindiPlaylistid[7];
            English_Playlist_id = getEnglishPlaylistid[7];
            Video_id = getVideoId[7];
            Telugu_Playlist_id = getTeluguPlaylistId[7];
        }
        else if(getItem.equals(getVideoListItem[8])){
            Hindi_Playlist_id = getHindiPlaylistid[8];
            English_Playlist_id = getEnglishPlaylistid[8];
            Telugu_Playlist_id = getTeluguPlaylistId[8];
        }
        else if(getItem.equals(getVideoListItem[9])){
            Hindi_Playlist_id = getHindiPlaylistid[9];
            English_Playlist_id = getEnglishPlaylistid[9];
        }
        else if(getItem.equals(getVideoListItem[10])){
            Hindi_Playlist_id = getHindiPlaylistid[10];
            English_Playlist_id = getEnglishPlaylistid[10];
            Video_id = getVideoId[8];
            Telugu_Playlist_id = getTeluguPlaylistId[9];
        }


        
    }


    public void playHindiVideo(View view) {
        if(!Hindi_Playlist_id.equals("")) {
            Intent intent = new Intent(YoutubeViewActivity1.this, DisplayYoutubeVideo.class);
            intent.putExtra("PlaylistId",Hindi_Playlist_id);
            startActivity(intent);
            finish();
        }

    }

    public void playOneShotVideo(View view) {
        if(!Video_id.equals("")) {
            Intent intent = new Intent(YoutubeViewActivity1.this, OnShotVideoActivity.class);
            intent.putExtra("youtubeVideoId",Video_id);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(YoutubeViewActivity1.this, "Sorry No Video!!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void playEnglishVideo(View view) {
        if(!English_Playlist_id.equals("")) {
            Intent intent = new Intent(YoutubeViewActivity1.this, DisplayYoutubeVideo.class);
            intent.putExtra("PlaylistId",English_Playlist_id);
            startActivity(intent);
            finish();
        }

    }

    public void playTeluguVideo(View view) {
        if(!Telugu_Playlist_id.equals("")) {
            Intent intent = new Intent(YoutubeViewActivity1.this, DisplayYoutubeVideo.class);
            intent.putExtra("PlaylistId",Telugu_Playlist_id);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(YoutubeViewActivity1.this, "Sorry No Video!!!", Toast.LENGTH_SHORT).show();
        }
    }
}