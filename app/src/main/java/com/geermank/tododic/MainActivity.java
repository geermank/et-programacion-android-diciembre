package com.geermank.tododic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.geermank.tododic.adapters.ListItemClickListener;
import com.geermank.tododic.adapters.TasksAdapter;
import com.geermank.tododic.database.LocalDatabase;
import com.geermank.tododic.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TasksAdapter.OnTaskClickListener, ListItemClickListener<Task> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        showWelcomeMessage();
        setUpTasksList();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.my_tasks);
    }

    private void showWelcomeMessage() {
        TextView tvWelcomeMessage = findViewById(R.id.tv_welcome_message);

        Intent intent = getIntent();
        String email = intent.getStringExtra(Constants.EMAIL_EXTRA);

        String welcomeMessage = getString(R.string.welcome_message);
        tvWelcomeMessage.setText(welcomeMessage);
    }

    private void setUpTasksList() {
        RecyclerView rvTasks = findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        List<Task> tasks = createTaskList();
        TasksAdapter adapter = new TasksAdapter(tasks);
        adapter.setOnTaskClickListener(this);
        adapter.setOnItemClickListener(this);

        rvTasks.setAdapter(adapter);
    }

    private List<Task> createTaskList() {
        return LocalDatabase.getInstance(this).getTaskDao().getAllTasks();
    }

    @Override
    public void onTaskClick(Task task) {
        //Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Task item) {
        Toast.makeText(this, item.getAssignedTo(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_create_new_task) {
            startNewTaskActivity();
        } else if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startNewTaskActivity() {
        startActivity(new Intent(this, CreateTaskActivity.class));
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.CREDENTIALS, MODE_PRIVATE);
        if (sharedPreferences.contains(Constants.EMAIL_EXTRA) ||
                sharedPreferences.contains(Constants.PASSWORD_EXTRA)) {
            sharedPreferences.edit()
                    .clear()
                    .apply();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}