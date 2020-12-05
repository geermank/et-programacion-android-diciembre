package com.geermank.tododic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvWelcomeMessage = findViewById(R.id.tv_welcome_message);

        Intent intent = getIntent();
        String email = intent.getStringExtra(Constants.EMAIL_EXTRA);

        String welcomeMessage = getString(R.string.welcome_message);
        tvWelcomeMessage.setText(welcomeMessage);
    }
}