package com.geermank.tododic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geermank.tododic.database.LocalDatabase;
import com.geermank.tododic.database.TasksDao;
import com.geermank.tododic.models.Task;

public class CreateTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle, etResponsible;
    private Button btnCreateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setUpToolbar();

        etTitle = findViewById(R.id.et_task_title);
        etResponsible = findViewById(R.id.et_task_assigned_to);

        btnCreateTask = findViewById(R.id.btn_create_task);
        btnCreateTask.setOnClickListener(this);
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Crear tarea nueva");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCreateTask) {
            createTask();
        }
    }

    private void createTask() {
        String title = etTitle.getText().toString();
        String responsible = etResponsible.getText().toString();

        Task task = new Task(title, responsible, false);

        LocalDatabase database = LocalDatabase.getInstance(this);
        TasksDao tasksDao = database.getTaskDao();
        tasksDao.insert(task);

        Toast.makeText(this, "Tarea creada!", Toast.LENGTH_SHORT).show();
    }
}