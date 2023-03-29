package com.example.codifyy.videosview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.codifyy.AddLanguages;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;

import java.net.Inet4Address;
import java.util.ArrayList;

public class VideosActivity extends AppCompatActivity {
    ImageView home;
    ArrayList<AddLanguages> list;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter adapter;
    String[] programingListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        home = findViewById(R.id.gobackToHome);
        recyclerView = findViewById(R.id.VideoActivityRecyclerView);
        //Extracting string array from string.xml for displaying the list of programming languages
        //use inside the oncreate method to avoid the app to crash
        programingListItems = getResources().getStringArray(R.array.ProgrammingListItem);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadRecyclerView();
    }

    private void loadRecyclerView() {
        list = new ArrayList<>();
        list.add(new AddLanguages(R.drawable.c,programingListItems[0]));
        list.add(new AddLanguages(R.drawable.c_plus,programingListItems[1]));
        list.add(new AddLanguages(R.drawable.python,programingListItems[2]));
        list.add(new AddLanguages(R.drawable.java,programingListItems[3]));
        list.add(new AddLanguages(R.drawable.php,programingListItems[4]));
        list.add(new AddLanguages(R.drawable.datastructure,programingListItems[5]));
        list.add(new AddLanguages(R.drawable.html,programingListItems[6]));
        list.add(new AddLanguages(R.drawable.javascript,programingListItems[7]));
        list.add(new AddLanguages(R.drawable.database,programingListItems[8]));
        list.add(new AddLanguages(R.drawable.cloudcomputing,programingListItems[9]));
        list.add(new AddLanguages(R.drawable.android,programingListItems[10]));



        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(VideosActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VideosActivity.this, MainFeatures.class));
        finish();
    }
}