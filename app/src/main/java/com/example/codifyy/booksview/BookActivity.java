package com.example.codifyy.booksview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codifyy.AddLanguages;
import com.example.codifyy.LearnViewPackage.AddInfo;
import com.example.codifyy.LearnViewPackage.LearnActivity;
import com.example.codifyy.LearnViewPackage.LearnAdaper;
import com.example.codifyy.MainActivity;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.R;
import com.example.codifyy.notesview.NotesActivity;
import com.example.codifyy.videosview.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    ImageView home;
    ArrayList<AddBooksInfo> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BookAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        recyclerView = findViewById(R.id.booksActivityRecyclerView);
        progressBar = findViewById(R.id.progressbar);

        ImageView img = findViewById(R.id.gobackToHome);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

      if(!isNetworkConnected()){
          Toast.makeText(BookActivity.this, "no internet Connected", Toast.LENGTH_SHORT).show();
      }
      else{
          loadFirebase();
      }
    }

    private void loadFirebase() {
        FirebaseDatabase.getInstance().getReference().child("Books")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        progressBar.setVisibility(View.GONE);
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String name = dataSnapshot.child("name").getValue().toString();
                            String title = dataSnapshot.child("title").getValue().toString();
                            String book_cover = dataSnapshot.child("book_cover").getValue().toString();
                            String buy_url = dataSnapshot.child("buy_url").getValue().toString();
                            String pdf_url = dataSnapshot.child("pdf_url").getValue().toString();
                            list.add(new AddBooksInfo(name,book_cover,pdf_url,buy_url,title));
                        }
                        loadRecyclerView(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(BookActivity.this, "error "+error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadRecyclerView(List<AddBooksInfo> list) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter(BookActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    public void goBackMethod(View view){
        onBackPressed();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(BookActivity.this.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BookActivity.this, MainFeatures.class));
        finish();
    }
}