package com.geermank.tododic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnRegister;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Iniciar sesión");

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        // View
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            login();
        } else {
            Toast.makeText(this, "Registrando cuenta..", Toast.LENGTH_SHORT).show();
        }
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Faltan datos por completar", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(Constants.EMAIL_EXTRA, email);
        mainIntent.putExtra(Constants.PASSWORD_EXTRA, password);

        startActivity(mainIntent);
        finish();
    }
}