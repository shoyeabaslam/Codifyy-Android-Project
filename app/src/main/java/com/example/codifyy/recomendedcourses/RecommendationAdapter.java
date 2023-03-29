package com.example.codifyy.recomendedcourses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.codifyy.LearnViewPackage.LearbActivity2;
import com.example.codifyy.MainFeaturesPackage.AddFeaturesInfo;
import com.example.codifyy.R;

import java.util.List;


public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.holder> {
    Context context;
    List<AddRecommendation> list;

    public RecommendationAdapter(Context context, List<AddRecommendation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recomended_courses_view,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.img.setImageResource(list.get(position).getImageId());
        holder.txt.setText(list.get(position).getTitle());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LearbActivity2.class);
                String web_url = list.get(holder.getAdapterPosition()).getUrl();
                intent.putExtra("web_url",web_url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        ImageView img;
        TextView txt;
        public holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recImage);
            txt = itemView.findViewById(R.id.recText);
            layout = itemView.findViewById(R.id.recLayout);
        }
    }
}
