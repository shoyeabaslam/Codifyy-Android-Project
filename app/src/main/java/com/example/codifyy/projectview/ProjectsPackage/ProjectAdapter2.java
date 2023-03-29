package com.example.codifyy.projectview.ProjectsPackage;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codifyy.R;
import com.example.codifyy.projectview.ProjectActivity;

import java.util.List;

public class ProjectAdapter2 extends RecyclerView.Adapter<ProjectAdapter2.holder>{
    Context context;
    List<AddProjectList> list;

    public ProjectAdapter2(Context context, List<AddProjectList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProjectAdapter2.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_layout_1,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter2.holder holder, int position) {
        holder.textView.setText(list.get(position).getChild2());
        holder.textView.setTextSize(18);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Project2Activity.class);
                intent.putExtra("child1",list.get(holder.getAdapterPosition()).getChild1());
                intent.putExtra("child2",list.get(holder.getAdapterPosition()).getChild2());
                context.startActivity(intent);
                Toast.makeText(context, ""+list.get(holder.getAdapterPosition()).getChild1(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView textView;
        public holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.project1TextView);
        }
    }

}
