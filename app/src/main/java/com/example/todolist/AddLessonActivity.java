package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddLessonActivity extends AppCompatActivity {

    private EditText editTextLessonName;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        editTextLessonName = findViewById(R.id.editTextLessonName);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            String lessonName = editTextLessonName.getText().toString();
            if (!lessonName.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("lessonName", lessonName);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(AddLessonActivity.this, "Введите название урока", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


