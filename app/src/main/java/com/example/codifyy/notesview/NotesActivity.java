package com.example.codifyy.notesview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codifyy.AddLanguages;
import com.example.codifyy.MainActivity;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;
import com.example.codifyy.notesview.AddingNotesPackage.Adapter1;
import com.example.codifyy.notesview.AddingNotesPackage.AddListOfNotes;
import com.example.codifyy.videosview.MyAdapter;
import com.example.codifyy.videosview.VideosActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    Adapter1 adapter;
    RecyclerView.LayoutManager layoutManager;
    List<AddListOfNotes> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        progressBar = findViewById(R.id.progressBarId);
        recyclerView = findViewById(R.id.notesActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        loadRecyclerView();

        ImageView back = findViewById(R.id.goback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadRecyclerView() {
        if(isNetworkConnected()){
            FirebaseDatabase.getInstance().getReference().child("Notes")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            progressBar.setVisibility(View.GONE);
                            list.clear();
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                String s = dataSnapshot.getKey();
                                list.add(new AddListOfNotes(s));
                            }
                            layoutManager = new GridLayoutManager(NotesActivity.this,2);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new Adapter1(NotesActivity.this,list);
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(NotesActivity.this,"Error "+error,Toast.LENGTH_LONG).show();

                        }
                    });


        }
        else{
            new AlertDialog.Builder(NotesActivity.this)
                    .setTitle("No Internet Connection")
                    .setMessage("Please Connect to the Internet")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onBackPressed();
                        }
                    }).show();
        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(NotesActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NotesActivity.this, MainFeatures.class));
        finish();
    }
}