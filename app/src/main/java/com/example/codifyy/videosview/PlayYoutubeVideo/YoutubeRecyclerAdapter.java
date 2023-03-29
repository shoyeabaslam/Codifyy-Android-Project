package com.example.codifyy.videosview.PlayYoutubeVideo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.codifyy.R;
import com.example.codifyy.videosview.PlayYoutubeVideo.VideoModelPack.Item;

import java.util.List;


public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<YoutubeRecyclerAdapter.MyviewHolder> {

    Context context;
    List<Item> items;

    int total ;

    public YoutubeRecyclerAdapter(Context context, List<Item> items, int total) {
        this.context = context;
        this.items = items;
        this.total = total;
    }


    @Override
    public YoutubeRecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thumbnail_view,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeRecyclerAdapter.MyviewHolder holder, int position) {
        holder.youtube_title.setText(items.get(position).getSnippet().getTitle());

        Glide.with(context).load(items.get(position).getSnippet()
                .getThumbnails().getHigh().getUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoId = items.get(holder.getAdapterPosition()).getSnippet().getPlaylistId();
                int index = items.get(holder.getAdapterPosition()).getSnippet().getPosition();
                Intent intent = new Intent(context,PlayingYoutubeVideo.class);
                intent.putExtra("PlaylistVideoIdIs",videoId);
                intent.putExtra("position",index);
                intent.putExtra("Total",total);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return items.size();

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView youtube_title;
        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            youtube_title = (TextView)itemView.findViewById(R.id.youtubeVideoTitle);
            image = (ImageView)itemView.findViewById(R.id.youtubeThumbnailId);
        }
    }
}
