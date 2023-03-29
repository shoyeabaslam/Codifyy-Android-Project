package com.example.codifyy.notesview.AddingNotesPackage;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codifyy.R;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.holder> {
    Context context;
    List<AddListOfNotes> list;

    public Adapter1(Context context, List<AddListOfNotes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.title.setText(list.get(position).getNotes());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ListOfNotesActivity.class);
                intent.putExtra("child1",list.get(holder.getAdapterPosition()).getNotes());
                Toast.makeText(context, ""+list.get(holder.getAdapterPosition()).getNotes(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        holder.bookCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ListOfNotesActivity.class);
                intent.putExtra("child1",list.get(holder.getAdapterPosition()).getNotes());
                Toast.makeText(context, ""+list.get(holder.getAdapterPosition()).getNotes(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView bookCover;
        public holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
            bookCover = itemView.findViewById(R.id.bookCover);
        }
    }
}
