package com.example.codifyy.MainFeaturesPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codifyy.LearnViewPackage.LearbActivity2;
import com.example.codifyy.LearnViewPackage.LearnActivity;
import com.example.codifyy.MainActivity;
import com.example.codifyy.QuizPackage.QuizActivity1;
import com.example.codifyy.QuizPackage.QuizInterface;
import com.example.codifyy.R;
import com.example.codifyy.booksview.BookActivity;
import com.example.codifyy.notesview.NotesActivity;
import com.example.codifyy.projectview.ProjectActivity;
import com.example.codifyy.recomendedcourses.AddRecommendation;
import com.example.codifyy.recomendedcourses.RecommendationAdapter;
import com.example.codifyy.technicalquesview.QuestionsActivity;
import com.example.codifyy.videosview.VideosActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class MainFeatures extends AppCompatActivity {
    SliderView sliderView;
    SliderAdapter sliderAdapter;
    ArrayList<AddData> list = new ArrayList<>();
    ProgressBar loadingProgressBar;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
//    ---------------------------------Navigation view-------------------------------------
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView navTextView;
//    --------------------------------Recycler view ----------------------------------------
    List<AddFeaturesInfo> addFeaturesInfoList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

//    --------------------------------Navigation menu listener---------------------------
    public String hack_url;
    public String join_url;
//  --------------------------------------------Adding Recommending courses--------------------------------------------
    RecyclerView recyclerView_reccomendation;
    RecommendationAdapter recommendationAdapter;
    List<AddRecommendation> recommendationsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_features);
//        ...............Find view By Id...............................................
        findVIewsByThereId();
//................... Loading the features used in app......................................................

        loadNavigationBar();//navigation
        loadImages();//adding images.
        AddFeatures();
        addRecommendation();

    }

    private void findVIewsByThereId() {
        sliderView = findViewById(R.id.sliderViewId);
        toolbar = findViewById(R.id.toolbarid);
        nav = findViewById(R.id.navId);
        mAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        View  view = nav.getHeaderView(0); // to change the navigation header text.....future reference
        navTextView = view.findViewById(R.id.profile_name);//finding the navigation header text....
        recyclerView = findViewById(R.id.features_recyclerView);
        loadingProgressBar = findViewById(R.id.sliderLoadingProgressBar);
        recyclerView_reccomendation = findViewById(R.id.recycler_recommendation);
    }

    private void addRecommendation() {
        recommendationsList = new ArrayList<>();
        recommendationsList.add(new AddRecommendation(R.drawable.java1,"java programming","https://www.w3schools.com/java/default.asp"));
        recommendationsList.add(new AddRecommendation(R.drawable.data_structures,"DataStructures and Algorithms",
                "https://practice.geeksforgeeks.org/explore?page=1&category[]=Arrays&sortBy=submissions&utm_source=gfg&utm_medium=gfg_header&utm_campaign=gfgpractice_header"));
        recommendationsList.add(new AddRecommendation(R.drawable.python1,"python programming","https://www.w3schools.com/python/default.asp"));
        recommendationsList.add(new AddRecommendation(R.drawable.cpp1,"c++ programming","https://www.w3schools.com/cpp/default.asp"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainFeatures.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_reccomendation.setHasFixedSize(true);
        recyclerView_reccomendation.setLayoutManager(layoutManager);
        recyclerView_reccomendation.setNestedScrollingEnabled(false);
        recommendationAdapter = new RecommendationAdapter(MainFeatures.this,recommendationsList);
        recyclerView_reccomendation.setAdapter(recommendationAdapter);




    }

    private void AddFeatures() {
        addFeaturesInfoList.add(new AddFeaturesInfo(R.drawable.video,"videos"));
        addFeaturesInfoList.add(new AddFeaturesInfo(R.drawable.learning,"Learn"));
        addFeaturesInfoList.add(new AddFeaturesInfo(R.drawable.notepad,"Notes"));
        addFeaturesInfoList.add(new AddFeaturesInfo(R.drawable.book,"Books"));
        addFeaturesInfoList.add(new AddFeaturesInfo(R.drawable.projects,"Projects"));
        addFeaturesInfoList.add(new AddFeaturesInfo(R.drawable.meeting,"Technical Questions"));


        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(MainFeatures.this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(MainFeatures.this,addFeaturesInfoList);
        recyclerView.setAdapter(adapter);
    }

    public String getFirebaseUser() {
        firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            return firebaseUser.getDisplayName();
        }
        return "Anonymous";
    }

    private void loadNavigationBar() {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        navTextView.setText(getFirebaseUser());//to add gmail name to navigation bar.....

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.setting){
                    drawerLayout.close();
                    Intent intent = new Intent(MainFeatures.this,SettingsActivity.class);
                    startActivity(intent);
                }

                else if(item.getItemId()==R.id.compiler){
                    //Since LearnActivity2 contains webView so passing the url from here itself
                    //url will be "my compiler"
                    drawerLayout.close();
                    final String url = "https://www.mycompiler.io/new/c";
                    Intent intent = new Intent(MainFeatures.this, LearbActivity2.class);
                    intent.putExtra("web_url",url);
                    intent.putExtra("title","Compiler");
                    startActivity(intent);
                }

//---------------------if Quiz clicked-------------------------------------------------

                else if(item.getItemId()==R.id.Quiz){
                    drawerLayout.close();
                    Intent intent = new Intent(MainFeatures.this, QuizInterface.class);
                    startActivity(intent);
                }

//---------------------if logout clickeds------------------------------------------------
                else if(item.getItemId()==R.id.logout){
                    finish();
                    mAuth.signOut();
                    Toast.makeText(MainFeatures.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainFeatures.this, MainActivity.class);
                    startActivity(intent);
                }
     //----------------if Hackathon clicked-----------------------------------
                else if(item.getItemId()==R.id.hackathon){
                    FirebaseDatabase.getInstance()
                            .getReference().child("Hackathon")
                                .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                 hack_url = dataSnapshot.getValue().toString();
                            }
                            if(!hack_url.equals("")){
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(hack_url));
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainFeatures.this,"No Hackathon Right Now!!!",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainFeatures.this,"Error "+error,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
    //-----------------if join clicked----------------------------------------
                else if(item.getItemId()==R.id.join){
                    FirebaseDatabase.getInstance()
                            .getReference().child("Join")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                        join_url = dataSnapshot.getValue().toString();
                                    }
                                    if(!join_url.equals("")){
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(join_url));
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(MainFeatures.this,"Available Soon",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(MainFeatures.this,"Error "+error,Toast.LENGTH_SHORT).show();
                                }
                            });
                }
    //--------------- if feedback clicked-----------------------------------
                else if(item.getItemId()==R.id.feedback){
                    Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:?to=" + "codifyyofficial@gmail.com");
                    mailIntent.setData(data);
                    startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.close();
        }
        else {
            new AlertDialog.Builder(MainFeatures.this)
                    .setTitle("Are You Sure?")
                    .setMessage("Do You Want To Quit")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No",null).show();
        }
    }

    private void loadImages() {
        if(!isNetworkConnected()){
            loadingProgressBar.setVisibility(View.GONE);
            list.add(new AddData(R.drawable.gotoquizimg,"quiz"));
            list.add(new AddData(R.drawable.banner1,"banner"));
            list.add(new AddData(R.drawable.offlineimg,"offline"));

        loadSlider(list);
        }
        else{
            FirebaseDatabase.getInstance().getReference().child("Slider_Images")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            loadingProgressBar.setVisibility(View.GONE);
                                list.clear();
                                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                    String img_url = dataSnapshot.child("img_url").getValue().toString();
                                    String if_click_url = dataSnapshot.child("if_click_url").getValue().toString();
                                    list.add(new AddData(img_url,if_click_url));
                                }
                                loadSlider(list);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainFeatures.this,"error "+error,Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    private void loadSlider(ArrayList<AddData> list) {
        sliderAdapter = new SliderAdapter(this,list);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.RED);
        sliderView.setIndicatorUnselectedColor(Color.WHITE);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }


    //------------------------Funtions to call the intent when buttons gets clicked-------------------------------
    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainFeatures.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}