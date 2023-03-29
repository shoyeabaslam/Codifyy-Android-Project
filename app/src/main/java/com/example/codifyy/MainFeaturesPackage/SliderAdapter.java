package com.example.codifyy.MainFeaturesPackage;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.codifyy.QuizPackage.QuizInterface;
import com.example.codifyy.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    Context context;
    ArrayList<AddData> arrayList;
    public SliderAdapter(Context context,ArrayList<AddData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider,
                parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if(!isNetworkConnected()){
            holder.imageView.setImageResource(arrayList.get(position).getImages());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = arrayList.get(position).getImg_name();
                    if(s.equals("quiz")){
                        context.startActivity(new Intent(context, QuizInterface.class));
                    }
                    else{
                        Toast.makeText(context, "No Content", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Glide.with(context).load(arrayList.get(position).getImg_url()).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(arrayList.get(position).getIf_click_url().equals("")){
                        Toast.makeText(context, "No Content", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(arrayList.get(position).getIf_click_url()));
                        context.startActivity(intent);
                    }
                }
            });
        }


    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewId);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
