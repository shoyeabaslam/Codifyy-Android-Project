package com.example.codifyy.MainFeaturesPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codifyy.LearnViewPackage.LearnActivity;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;
import com.example.codifyy.notesview.NotesActivity;
import com.example.codifyy.projectview.ProjectActivity;
import com.example.codifyy.technicalquesview.QuestionsActivity;
import com.example.codifyy.videosview.VideosActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    Activity context;
    List<AddFeaturesInfo> list;

    public RecyclerAdapter(Activity context, List<AddFeaturesInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_features,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.Holder holder, int position) {

        holder.img.setImageResource(list.get(position).getImage_id());
        holder.txt.setText(list.get(position).getName());
//        holder.linearLayout.setBackgroundResource(list.get(position).getColor_id()); no colors adding

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (holder.getAdapterPosition()){
                    case 0:context.startActivity(new Intent(context, VideosActivity.class));context.finish();break;
                    case 1:context.startActivity(new Intent(context, LearnActivity.class));context.finish();break;
                    case 2:context.startActivity(new Intent(context, NotesActivity.class));context.finish();break;
                    case 3:context.startActivity(new Intent(context, BookActivity.class));context.finish();break;
                    case 4:context.startActivity(new Intent(context, ProjectActivity.class));context.finish();break;
                    case 5:context.startActivity(new Intent(context, QuestionsActivity.class));context.finish();break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        LinearLayout linearLayout;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.logoViewId);
            txt = itemView.findViewById(R.id.textViewId);
            linearLayout = itemView.findViewById(R.id.backgroundViewId);
        }
    }

}
