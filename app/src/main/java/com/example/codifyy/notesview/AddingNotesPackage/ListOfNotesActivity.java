package com.example.codifyy.notesview.AddingNotesPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codifyy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListOfNotesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<AddListOfNotes> list = new ArrayList<>();
    Adapter2 adapter;
    ProgressBar progressBar;
    String child1;

    TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notes);
        progressBar = findViewById(R.id.progressBarId);
        recyclerView = findViewById(R.id.notesListRecyclerView);
        toolbarText = findViewById(R.id.setTitleBar);

        child1 = getIntent().getStringExtra("child1");
        toolbarText.setText(child1);


       setUpRecylerVIew();


    }

    private void setUpRecylerVIew() {

        if(isNetworkConnected()){
            FirebaseDatabase.getInstance().getReference().child("Notes").child(child1)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            progressBar.setVisibility(View.GONE);
                            list.clear();
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                String s = dataSnapshot.getKey();
                                String title = dataSnapshot.child("title").getValue().toString();
                                String url = dataSnapshot.child("url").getValue().toString();
                                list.add(new AddListOfNotes(s,title,url));
                            }
                            layoutManager = new LinearLayoutManager(ListOfNotesActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new Adapter2(ListOfNotesActivity.this,list);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ListOfNotesActivity.this,"Error "+error,Toast.LENGTH_LONG).show();

                        }
                    });


        }
        else{
            Toast.makeText(ListOfNotesActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }




    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(ListOfNotesActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void goBackMethod(View view) {
        onBackPressed();
    }
}