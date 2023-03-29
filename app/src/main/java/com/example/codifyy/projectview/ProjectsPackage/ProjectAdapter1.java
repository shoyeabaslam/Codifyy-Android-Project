package com.example.codifyy.projectview.ProjectsPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codifyy.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.List;

public class ProjectAdapter1 extends RecyclerView.Adapter<ProjectAdapter1.holder> {
    Context context;
    List<AddProjectList> list;

    public ProjectAdapter1(Context context, List<AddProjectList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_layout_1,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.textView.setText(list.get(position).getChild1());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProjectL1Activity.class);
                intent.putExtra("child1",list.get(holder.getAdapterPosition()).getChild1());
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
