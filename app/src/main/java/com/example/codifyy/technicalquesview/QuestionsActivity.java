package com.example.codifyy.technicalquesview;

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
import com.example.codifyy.LearnViewPackage.LearnActivity;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;
import com.example.codifyy.notesview.NotesActivity;
import com.example.codifyy.videosview.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    List<AddQuesInfo> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    QuesAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        recyclerView = findViewById(R.id.notesActivityRecyclerView);
        progressBar = findViewById(R.id.progressbar);

       if(!isNetworkConnected()){
            new AlertDialog.Builder(QuestionsActivity.this)
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
        FirebaseDatabase.getInstance().getReference().child("Technical Questions")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        progressBar.setVisibility(View.GONE);
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            String title = dataSnapshot.child("title").getValue().toString();
                            String img_url = dataSnapshot.child("img_url").getValue().toString();
                            String web_url = dataSnapshot.child("web_url").getValue().toString();
                            list.add(new AddQuesInfo(title,img_url,web_url));
                        }
                        loadRecyclerView(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(QuestionsActivity.this,"error "+error,Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void loadRecyclerView(List<AddQuesInfo> list) {
//        list = new ArrayList<>();
//        list.add(new AddLanguages(R.drawable.c,"c programing"));
//        list.add(new AddLanguages(R.drawable.c_plus,"c++ programing"));
//        list.add(new AddLanguages(R.drawable.python,"python programing"));
//        list.add(new AddLanguages(R.drawable.java,"java programing"));
//        list.add(new AddLanguages(R.drawable.php,"php programing"));
//        list.add(new AddLanguages(R.drawable.datastructure,"data structure"));
//        list.add(new AddLanguages(R.drawable.javascript,"javascript"));
//        list.add(new AddLanguages(R.drawable.database,"database"));
//        list.add(new AddLanguages(R.drawable.cloudcomputing,"cloud computing"));
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuesAdapter(QuestionsActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    public void goBackMethod(View view) {
        onBackPressed();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(QuestionsActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuestionsActivity.this, MainFeatures.class));
        finish();
    }
}