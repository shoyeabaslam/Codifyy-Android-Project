package com.example.codifyy.technicalquesview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.codifyy.LearnViewPackage.AddInfo;
import com.example.codifyy.LearnViewPackage.LearbActivity2;
import com.example.codifyy.LearnViewPackage.LearnAdaper;
import com.example.codifyy.R;

import java.util.List;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.holder> {
    Context context;
    List<AddQuesInfo> list;

    public QuesAdapter(Context context, List<AddQuesInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuesAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.questions_card_view,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuesAdapter.holder holder, int position) {
        holder.title.setText(list.get(position).getTitle()); //to set the title
        if(list.get(position).img_url.equals("")){
            holder.img_icon.setImageResource(R.drawable.code);
        }
        else {
            Glide.with(context).load(list.get(position).getImg_url()).into(holder.img_icon);
        }// to set the image from the firebase

        //jump to learnactivity2
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LearbActivity2.class);
                intent.putExtra("title",list.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("web_url",list.get(holder.getAdapterPosition()).getWeb_url());
                context.startActivity(intent);
            }
        });
        holder.img_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LearbActivity2.class);
                intent.putExtra("title",list.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("web_url",list.get(holder.getAdapterPosition()).getWeb_url());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView img_icon;
        TextView title;
        public holder(@NonNull View itemView) {
            super(itemView);
            img_icon = itemView.findViewById(R.id.imageViewId);
            title = itemView.findViewById(R.id.textViewId);
        }
    }
}
