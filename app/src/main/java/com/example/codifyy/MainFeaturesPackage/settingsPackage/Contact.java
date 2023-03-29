package com.example.codifyy.MainFeaturesPackage.settingsPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.codifyy.R;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

    }

    public void goBackMethod(View view) {
        onBackPressed();
    }

    public void sendGmail(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?to=" + "codifyyofficial@gmail.com");
        intent.setData(data);
        startActivity(Intent.createChooser(intent, "Send mail..."));

    }

    public void sendWhatsapp(View view) {
        final String url = "http://wa.me/+918309644110";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}