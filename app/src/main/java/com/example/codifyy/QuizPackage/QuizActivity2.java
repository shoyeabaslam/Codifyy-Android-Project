package com.example.codifyy.QuizPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codifyy.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class QuizActivity2 extends AppCompatActivity {


    CircularProgressBar progress;
    int r,w,t,s;
    float per;
    TextView total,right,wrong,skip,suggestion;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);


        progress = findViewById(R.id.circularProgressBar);
        total = findViewById(R.id.totalId);
        right = findViewById(R.id.rightId);
        wrong = findViewById(R.id.wrongId);
        skip = findViewById(R.id.skipId);
        suggestion = findViewById(R.id.suggestion);
        score = findViewById(R.id.score);


        r = getIntent().getIntExtra("right",0);
        w = getIntent().getIntExtra("wrong",0);
        t = getIntent().getIntExtra("total",0);
        s = getIntent().getIntExtra("skip",0);

        setTextViews();
        setUpProgressBar();
    }


    private void setUpProgressBar() {
        if(t!=0){
            per = (float)r/t*(100);
        }
        else{
            per = 0;
        }

        int s = (int)per;
        score.setText(s+"%");
        progress.setProgressWithAnimation(per,2000l);
        setUpInstructionMenu(per);
    }

    private void setUpInstructionMenu(float per) {
        TextView suggestion = findViewById(R.id.suggestion);
        int i = (int) per;
        if(i>90){
            suggestion.setText("Awesome!!!\nYou Look Well In Programming keep Going");
        }
        else if(i>75 && i<90){
            suggestion.setText("Not Bad Score!!!\nStill Can Be Better Keep Practising");
        }
        else if(i>60 && i<70){
            suggestion.setText("Practise Again!!!\nYou Can Score More");
        }
        else if(i>45 && i<60){
            suggestion.setText("Try Once More!!!\nPerfection is achieved not when there is nothing more to add, but rather when there is nothing more to take away");
        }
        else if(i>25 && i<45){
            suggestion.setText("You Need To Practise More!!!\nCome Back Again");
        }

        else if(i>15 && i<25){
            suggestion.setText("Don't Get Disappointed with score\nPractise And Makes AnyOne Perfect");
        }
        else{
            suggestion.setText("You Need to Practise More!!!\nGo To Learn Section And Choose Any Language And start Practising");
        }
    }

    private void setTextViews() {
        total.setText(""+t);
        right.setText(""+r);
        wrong.setText(""+w);
        skip.setText(""+s);
    }


    public void shareWith(View view) {
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss:aa", Locale.getDefault());


        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        String text = "Date::" +dateFormat.format(d)+"\nTime::"+timeFormat.format(d)+
                "\nYour score is\n"+score.getText()+"\nTotal Questions: "+t+"\nRight: "+r
                +"\nwrong:"+w+"\nSkip:"+s+"\n\n\nPlease share this application with your friends..\nThank You..";
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(QuizActivity2.this, "Whats app not installed", Toast.LENGTH_SHORT).show();
        }
    }
}