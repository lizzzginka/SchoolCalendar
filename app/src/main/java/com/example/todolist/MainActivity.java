package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLessons;
    private Button buttonAddLesson;
    private LessonAdapter lessonAdapter;
    private List<Lesson> lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewLessons = findViewById(R.id.recyclerView_lessons);
        buttonAddLesson = findViewById(R.id.button_add_lesson);

        lessons = new ArrayList<>();
        lessonAdapter = new LessonAdapter(lessons, this);
        recyclerViewLessons.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLessons.setAdapter(lessonAdapter);

        buttonAddLesson.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLessonActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Handle adding new lesson
            String lessonName = data.getStringExtra("lessonName");
            lessons.add(new Lesson(lessonName, new ArrayList<>()));
            lessonAdapter.notifyDataSetChanged();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            // Handle adding new task
            int lessonIndex = data.getIntExtra("lessonIndex", -1);
            if (lessonIndex != -1) {
                String taskName = data.getStringExtra("taskName");
                String taskGrade = data.getStringExtra("taskGrade");
                if (lessonIndex < lessons.size()) {
                    Lesson lesson = lessons.get(lessonIndex);
                    lesson.getTasks().add(new Task(taskName, Integer.parseInt(taskGrade)));
                    lessonAdapter.notifyItemChanged(lessonIndex);
                }
            }
        }
    }
}

