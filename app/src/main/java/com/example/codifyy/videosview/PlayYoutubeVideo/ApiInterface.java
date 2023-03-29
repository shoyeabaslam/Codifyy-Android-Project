package com.example.codifyy.videosview.PlayYoutubeVideo;

import com.example.codifyy.videosview.PlayYoutubeVideo.VideoModelPack.VideoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("playlistItems")
    Call<VideoModel> getVideos(@Query("key") String key
                            , @Query("playlistId") String Id,
                               @Query("part") String part,
                               @Query("maxResults") String maxResults
                           );
}