package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    TextView tvGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvGithub = findViewById(R.id.tvGithub);

        tvGithub.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/yourusername/zakatgoldcalculator"));

            startActivity(intent);

        });
    }
}