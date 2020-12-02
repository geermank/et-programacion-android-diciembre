package com.geermank.tododic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // View
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        //Button btnRegister = find...
        //btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Iniciando sesión", Toast.LENGTH_SHORT).show();
    }
}