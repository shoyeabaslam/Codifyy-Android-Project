package com.example.codifyy.QuizPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codifyy.LearnViewPackage.LearnActivity;
import com.example.codifyy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity1 extends AppCompatActivity {
    int right=0,wrong=0,total=0;
    int i=0;
    List<Items> list = new ArrayList<>();
    LinearLayout l1,l2,l3,l4;
    TextView t1,t2,t3,t4,ques;
    CardView next;
    int ttl_ques;
    TextView ques_numb;
    CountDownTimer c;
    ProgressBar progressBar;
    int skip=0;

    boolean right_ans = false;

    boolean notClicked = true;


    String json_name;
    int json_file_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        getIds();
        settingUpQuizChoice();
        getJson();
        setInfo(i);
        TImerRun();
        setTotalQues(i);
    }

    private void settingUpQuizChoice() {
        Intent intent = getIntent();
        json_name = intent.getStringExtra("quiz_intent_key");
        if(json_name.equals("c")){
            json_file_id = R.raw.c;
        }
        else if(json_name.equals("python")){
            json_file_id = R.raw.python;
        }
        else if(json_name.equals("java")){
            json_file_id = R.raw.java;
        }
        else if(json_name.equals("cpp")){
            json_file_id = R.raw.cpp;
        }

        else if(json_name.equals("html")){
            json_file_id = R.raw.html;
        }
        else if(json_name.equals("sql")){
            json_file_id = R.raw.sql;
            t1.setAllCaps(false);
            t2.setAllCaps(false);
            t3.setAllCaps(false);
            t4.setAllCaps(false);
        }
        else if(json_name.equals("php")){
            json_file_id = R.raw.php;
        }
        else if(json_name.equals("js")){
            json_file_id = R.raw.js;
        }
    }


    private void setTotalQues(int i) {
        ques_numb.setText((i+1)+"/"+ttl_ques);
    }

    private void TImerRun() {
        c = new CountDownTimer(30000,1000){


            @Override
            public void onTick(long l) {
                int i = (int) l/300;
                progressBar.setProgress(i);
            }

            @Override
            public void onFinish() {
                total++;
                if(notClicked){
                    skip++;
                }
                notClicked=true;
                if(i<ttl_ques-1){
                    t1.setClickable(true);
                    t2.setClickable(true);
                    t3.setClickable(true);
                    t4.setClickable(true);

                    l1.setBackgroundResource(R.drawable.gradient4);
                    l2.setBackgroundResource(R.drawable.gradient4);
                    l3.setBackgroundResource(R.drawable.gradient4);
                    l4.setBackgroundResource(R.drawable.gradient4);

                    i++;
                    setInfo(i);
                    setTotalQues(i);
                    TImerRun();
                }
                else if(i==ttl_ques-1){
                    c.cancel();
                    Toast.makeText(QuizActivity1.this, "over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity1.this,QuizActivity2.class);
                    intent.putExtra("right",right);
                    intent.putExtra("wrong",wrong);
                    intent.putExtra("total",ttl_ques);
                    intent.putExtra("skip",skip);
                    startActivity(intent);
                    finish();
                }



            }
        };
        c.start();
    }


    private void setInfo(int count) {
        Items items = list.get(count);
        ques.setText(items.getQuestions());
        t1.setText(items.getOp1());
        t2.setText(items.getOp2());
        t3.setText(items.getOp3());
        t4.setText(items.getOp4());

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notClicked = false;
                t2.setClickable(false);
                t3.setClickable(false);
                t4.setClickable(false);
                if(items.getOp1().equals(items.ans)){
                    l1.setBackgroundColor(Color.rgb(14,209,69));
                    t1.setTextColor(Color.rgb(0,77,0));
                    ++right;
                }
                else{
                    ++wrong;
                    l1.setBackgroundColor(Color.rgb(202,6,13));
                    t1.setTextColor(Color.rgb(135,0,0));
                    if(items.getOp2().equals(items.ans)){
                        l2.setBackgroundColor(Color.rgb(14,209,69));
                        t2.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp3().equals(items.ans)){
                        l3.setBackgroundColor(Color.rgb(14,209,69));
                        t3.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp4().equals(items.ans)){
                        l4.setBackgroundColor(Color.rgb(14,209,69));
                        t4.setTextColor(Color.rgb(0,77,0));
                    }

                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notClicked = false;
                t1.setClickable(false);
                t3.setClickable(false);
                t4.setClickable(false);
                if(items.getOp2().equals(items.ans)){
                    l2.setBackgroundColor(Color.rgb(14,209,69));
                    t2.setTextColor(Color.rgb(0,77,0));
                    ++right;
                }
                else{
                    ++wrong;
                    l2.setBackgroundColor(Color.rgb(202,6,13));
                    t2.setTextColor(Color.rgb(135,0,0));
                    if(items.getOp1().equals(items.ans)){
                        l1.setBackgroundColor(Color.rgb(14,209,69));
                        t1.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp3().equals(items.ans)){
                        l3.setBackgroundColor(Color.rgb(14,209,69));
                        t3.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp4().equals(items.ans)){
                        l4.setBackgroundColor(Color.rgb(14,209,69));
                        t4.setTextColor(Color.rgb(0,77,0));
                    }

                }
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notClicked = false;
                t2.setClickable(false);
                t1.setClickable(false);
                t4.setClickable(false);
                if(items.getOp3().equals(items.ans)){
                    l3.setBackgroundColor(Color.rgb(14,209,69));
                    t3.setTextColor(Color.rgb(0,77,0));
                    ++right;
                }
                else{
                    ++wrong;
                    l3.setBackgroundColor(Color.rgb(202,6,13));
                    t3.setTextColor(Color.rgb(135,0,0));
                    if(items.getOp2().equals(items.ans)){
                        l2.setBackgroundColor(Color.rgb(14,209,69));
                        t2.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp1().equals(items.ans)){
                        l1.setBackgroundColor(Color.rgb(14,209,69));
                        t1.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp4().equals(items.ans)){
                        l4.setBackgroundColor(Color.rgb(14,209,69));
                        t4.setTextColor(Color.rgb(0,77,0));
                    }

                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notClicked = false;
                t2.setClickable(false);
                t3.setClickable(false);
                t1.setClickable(false);
                if(items.getOp4().equals(items.ans)){
                    l4.setBackgroundColor(Color.rgb(14,209,69));
                    t4.setTextColor(Color.rgb(0,77,0));
                    ++right;
                }
                else{
                    l4.setBackgroundColor(Color.rgb(202,6,13));
                    t4.setTextColor(Color.rgb(135,0,0));
                    if(items.getOp2().equals(items.ans)){
                        l2.setBackgroundColor(Color.rgb(14,209,69));
                        t2.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp3().equals(items.ans)){
                        l3.setBackgroundColor(Color.rgb(14,209,69));
                        t3.setTextColor(Color.rgb(0,77,0));
                    }
                    else if(items.getOp1().equals(items.ans)){
                        l1.setBackgroundColor(Color.rgb(14,209,69));
                        t1.setTextColor(Color.rgb(0,77,0));
                    }
                    ++wrong;
                }
            }
        });

    }

    private void getJson() {
        String json;
        try {
            InputStream is = getResources().openRawResource(json_file_id);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONObject object = new JSONObject(json);
            JSONArray jsonArray = object.getJSONArray(json_name);
            ttl_ques = jsonArray.length();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                String ques = obj.getString("ques");
                String op1 = obj.getString("op1");
                String op2 = obj.getString("op2");
                String op3 = obj.getString("op3");
                String op4 = obj.getString("op4");
                String ans = obj.getString("ans");
                list.add(new Items(ques,op1,op2,op3,op4,ans));
            }


        }
        catch (Exception e){
            Toast.makeText(QuizActivity1.this, "Error"+e, Toast.LENGTH_LONG).show();
        }
    }

    private void getIds() {
        l1 = findViewById(R.id.op1);
        l2 = findViewById(R.id.op2);
        l3 = findViewById(R.id.op3);
        l4 = findViewById(R.id.op4);
        ques = findViewById(R.id.question);
        t1 = findViewById(R.id.op1text);
        t2 = findViewById(R.id.op2text);
        t3 = findViewById(R.id.op3text);
        t4 = findViewById(R.id.op4text);

        next = findViewById(R.id.next);
        progressBar = findViewById(R.id.simpleProgressBar);
        ques_numb = findViewById(R.id.question_number);
    }


    public void next(View view) {
        total++;
        if(notClicked){
            skip++;
        }
        notClicked=true;
        t1.setClickable(true);
        t2.setClickable(true);
        t3.setClickable(true);
        t4.setClickable(true);

        t1.setTextColor(Color.BLACK);
        t2.setTextColor(Color.BLACK);
        t3.setTextColor(Color.BLACK);
        t4.setTextColor(Color.BLACK);

        l1.setBackgroundResource(R.drawable.gradient4);
        l2.setBackgroundResource(R.drawable.gradient4);
        l3.setBackgroundResource(R.drawable.gradient4);
        l4.setBackgroundResource(R.drawable.gradient4);

        if(i<ttl_ques-1){
            i++;
            c.cancel();
            TImerRun();
            setInfo(i);
            setTotalQues(i);
        }
        else if(i==ttl_ques-1){
            c.cancel();
            Intent intent = new Intent(QuizActivity1.this,QuizActivity2.class);
            intent.putExtra("right",right);
            intent.putExtra("wrong",wrong);
            intent.putExtra("total",ttl_ques);
            intent.putExtra("skip",skip);
            startActivity(intent);
            finish();

        }
    }

    public void submit(View view) {
//        Toast.makeText(MainActivity.this, "hey", Toast.LENGTH_SHORT).show();
        if(!notClicked){
            total++;
        }
        c.cancel();
        Intent intent = new Intent(QuizActivity1.this,QuizActivity2.class);
        intent.putExtra("right",right);
        intent.putExtra("wrong",wrong);
        intent.putExtra("total",total);
        intent.putExtra("skip",skip);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(QuizActivity1.this)
                .setTitle("Are You Sure?")
                .setMessage("Do You Want TO Quit")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        QuizActivity1.super.onBackPressed();
                    }
                }).show();
    }
}