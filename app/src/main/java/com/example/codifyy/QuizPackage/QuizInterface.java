package com.example.codifyy.QuizPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.codifyy.R;

public class QuizInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_interface);
    }

    public void goBackMethod(View view) {
        onBackPressed();
    }

    public void c_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","c");
        startActivity(intent);
    }

    public void python_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","python");
        startActivity(intent);
    }

    public void java_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","java");
        startActivity(intent);
    }

    public void cpp_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","cpp");
        startActivity(intent);
    }

    public void html_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","html");
        startActivity(intent);
    }

    public void sql_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","sql");
        startActivity(intent);
    }

    public void php_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","php");
        startActivity(intent);
    }

    public void js_clicked(View view) {
        Intent intent = new Intent(QuizInterface.this,QuizActivity1.class);
        intent.putExtra("quiz_intent_key","js");
        startActivity(intent);
    }
}