package com.example.codifyy.projectview;

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

import com.example.codifyy.MainActivity;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;
import com.example.codifyy.notesview.AddingNotesPackage.ListOfNotesActivity;
import com.example.codifyy.projectview.ProjectsPackage.AddProjectList;
import com.example.codifyy.projectview.ProjectsPackage.ProjectAdapter1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {
    ImageView home;
    List<AddProjectList> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    ProjectAdapter1 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        recyclerView = findViewById(R.id.projectActivityRecyclerView);
        progressBar = findViewById(R.id.progressbar);


        if(!isNetworkConnected()){
            new AlertDialog.Builder(ProjectActivity.this)
                    .setTitle("No Internet Connection")
                    .setMessage("Please Connect to the Internet")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onBackPressed();
                        }
                    }).show();
        }
        else {
            loadFirebase();
        }
    }
    private void loadFirebase(){
        FirebaseDatabase.getInstance().getReference().child("Projects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String getChild = dataSnapshot.getKey().toString();
                    list.add(new AddProjectList(getChild));
                }
                loadRecyclerView(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProjectActivity.this, "Error "+error, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadRecyclerView(List<AddProjectList> list) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ProjectActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProjectAdapter1(ProjectActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(ProjectActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void goBackMethod(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProjectActivity.this, MainFeatures.class));
        finish();
    }
}