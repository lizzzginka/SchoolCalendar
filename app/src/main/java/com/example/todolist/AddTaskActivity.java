package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskName;
    private EditText editTextTaskGrade;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        editTextTaskGrade = findViewById(R.id.editTextTaskGrade);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            String taskName = editTextTaskName.getText().toString();
            String taskGrade = editTextTaskGrade.getText().toString();
            if (!taskName.isEmpty() && !taskGrade.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("taskName", taskName);
                resultIntent.putExtra("taskGrade", taskGrade);
                resultIntent.putExtra("lessonIndex", getIntent().getIntExtra("lessonIndex", -1));
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(AddTaskActivity.this, "Введите данные задания", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

