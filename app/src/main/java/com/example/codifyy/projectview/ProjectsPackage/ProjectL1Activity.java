package com.example.codifyy.projectview.ProjectsPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codifyy.R;
import com.example.codifyy.projectview.ProjectActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProjectL1Activity extends AppCompatActivity {

    String child;
    List<AddProjectList> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    ProjectAdapter2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_l1);
        recyclerView  = findViewById(R.id.projectActivityRecyclerView);
        progressBar = findViewById(R.id.progressbar);
        child = getIntent().getStringExtra("child1");
        loadFirebase();
    }


    private void loadFirebase(){
        FirebaseDatabase.getInstance().getReference().child("Projects").child(child).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String getChild = dataSnapshot.getKey().toString();
                    list.add(new AddProjectList(child,getChild));
                }
                loadRecyclerView(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProjectL1Activity.this, "Error "+error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadRecyclerView(List<AddProjectList> list) {
        layoutManager = new LinearLayoutManager(ProjectL1Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProjectAdapter2(ProjectL1Activity.this,list);
        recyclerView.setAdapter(adapter);

    }

    public void goBackMethod(View view) {
        onBackPressed();
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(ProjectL1Activity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}