package com.example.codifyy.LearnViewPackage;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codifyy.MainActivity;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;
import com.example.codifyy.projectview.ProjectActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LearnAdaper adaper;
    List<AddInfo> list = new ArrayList<>();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        recyclerView = findViewById(R.id.learn_Recyclerview);
        progressBar = findViewById(R.id.progressbar);
        if(!isNetworkConnected()){
            new AlertDialog.Builder(LearnActivity.this)
                    .setTitle("No Internet Connection")
                    .setMessage("Please Check Your internet Connection")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

    private void loadFirebase() {
        FirebaseDatabase.getInstance().getReference().child("Learn")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        progressBar.setVisibility(View.GONE);
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String title = dataSnapshot.child("title").getValue().toString();
                            String img_url = dataSnapshot.child("img_url").getValue().toString();
                            String web_url = dataSnapshot.child("web_url").getValue().toString();
                            list.add(new AddInfo(title,img_url,web_url));
                        }
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new GridLayoutManager(LearnActivity.this,2);
                        recyclerView.setLayoutManager(layoutManager);
                        adaper = new LearnAdaper(LearnActivity.this,list);
                        recyclerView.setAdapter(adaper);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LearnActivity.this, "error "+error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void goBackMethod(View view) {
        onBackPressed();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(LearnActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LearnActivity.this, MainFeatures.class));
        finish();
    }
}