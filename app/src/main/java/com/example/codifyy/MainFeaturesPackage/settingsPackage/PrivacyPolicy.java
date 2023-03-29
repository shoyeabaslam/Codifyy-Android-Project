package com.example.codifyy.MainFeaturesPackage.settingsPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.codifyy.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PrivacyPolicy extends AppCompatActivity {


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        textView = findViewById(R.id.TEXT_STATUS_ID);
        try {
            loadText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadText() throws IOException {
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = this.getResources().openRawResource(R.raw.privacypolicy);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            string.trim();
            stringBuilder.append(string).append("\n");
            textView.setText(stringBuilder);

        }
        is.close();

    }

    public void goBackMethod(View view) {
        onBackPressed();
    }
}