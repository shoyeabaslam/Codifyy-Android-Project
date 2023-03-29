package com.example.codifyy.videosview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codifyy.AddLanguages;
import com.example.codifyy.R;
import com.example.codifyy.videosview.PlayYoutubeVideo.YoutubeViewActivity1;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder > {
    Context context;
    ArrayList<AddLanguages> list;

    public MyAdapter(Context context, ArrayList<AddLanguages> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.holder holder, int position) {
        holder.txt.setText(list.get(position).getName());
        holder.img.setImageResource(list.get(position).getImageId());
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YoutubeViewActivity1.class);
                intent.putExtra("ListItemSelected",list.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;
        public holder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textViewId);
            img = itemView.findViewById(R.id.imageViewId);
        }
    }
}
