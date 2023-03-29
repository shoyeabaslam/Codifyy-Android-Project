package com.example.codifyy.videosview.PlayYoutubeVideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codifyy.R;
import com.example.codifyy.videosview.PlayYoutubeVideo.VideoModelPack.Item;
import com.example.codifyy.videosview.PlayYoutubeVideo.VideoModelPack.VideoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayYoutubeVideo extends AppCompatActivity {

    RecyclerView recyclerView;
    YoutubeRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public String Key = "AIzaSyBVEm5OmtveW9vB5iq9tt9tkSi4otQAf0U";
    public String id;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_youtube_video);
        recyclerView = findViewById(R.id.youtubeVideoDisplayRecyclerVIew);
        progressBar = findViewById(R.id.progressBarId);
        Intent getYoutubeVideoId = getIntent();
        id = getYoutubeVideoId.getStringExtra("PlaylistId");


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VideoModel> call = apiService.getVideos(Key,id,"snippet","50");
        call.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                Log.d("TAG", "onResponse: ");
                Log.d("TAG", "onResponse: "+response.body().getItems());
                if(response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    setRecyclerView(response.body().getItems(), response.body().getPageInfo().getTotalResults());
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                Toast.makeText(DisplayYoutubeVideo.this, "Sorry!!! "+t.getMessage(), Toast.LENGTH_SHORT).show();

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

    private void setRecyclerView(List<Item> items, Integer totalResults) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(DisplayYoutubeVideo.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new YoutubeRecyclerAdapter(DisplayYoutubeVideo.this,items,totalResults);
        recyclerView.setAdapter(adapter);
    }
}