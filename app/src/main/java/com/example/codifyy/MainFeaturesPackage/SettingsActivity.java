package com.example.codifyy.MainFeaturesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.codifyy.MainActivity;
import com.example.codifyy.MainFeaturesPackage.settingsPackage.AboutActivity;
import com.example.codifyy.MainFeaturesPackage.settingsPackage.Contact;
import com.example.codifyy.MainFeaturesPackage.settingsPackage.PrivacyPolicy;
import com.example.codifyy.R;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Switch mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAuth = FirebaseAuth.getInstance();
        mode = findViewById(R.id.switch_modeId);


        loadNightMode();
        if(mode.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else if(mode.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


    }

    private void loadNightMode() {
        SharedPreferences shrd = getSharedPreferences("mode",MODE_PRIVATE);
        mode.setChecked(shrd.getBoolean("value",false));

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    mode.setChecked(true);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    mode.setChecked(false);
                }
            }
        });

    }

    public void goBackMethod(View view) {
        onBackPressed();
    }


//    contact page opening

    public void contact(View view) {
        startActivity(new Intent(SettingsActivity.this, Contact.class));
    }

    public void privacyPolicy(View view) {
        startActivity(new Intent(SettingsActivity.this, PrivacyPolicy.class));
    }

    public void aboutClick(View view) {
        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
    }
}