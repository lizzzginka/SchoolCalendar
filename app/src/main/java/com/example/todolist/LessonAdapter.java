package com.example.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private final List<Lesson> lessons;
    private final Context context;

    public LessonAdapter(List<Lesson> lessons, Context context) {
        this.lessons = lessons;
        this.context = context;
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_item, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.textViewLessonName.setText(lesson.getName());

        TaskAdapter taskAdapter = new TaskAdapter(lesson.getTasks(), context);
        holder.recyclerViewTasks.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerViewTasks.setAdapter(taskAdapter);
        // Обработчик нажатий для раскрытия урока
        holder.textViewLessonName.setOnClickListener(view -> {
            if (holder.recyclerViewTasks.getVisibility() == View.GONE) {
                holder.recyclerViewTasks.setVisibility(View.VISIBLE);
                holder.buttonCollapse.setVisibility(View.VISIBLE);
                holder.buttonAddTask.setVisibility(View.VISIBLE);
            } else {
                holder.recyclerViewTasks.setVisibility(View.GONE);
                holder.buttonCollapse.setVisibility(View.GONE);
                holder.buttonAddTask.setVisibility(View.GONE);
            }
        });

        // Обработчик нажатия кнопки "Свернуть"
        holder.buttonCollapse.setOnClickListener(view -> {
            holder.recyclerViewTasks.setVisibility(View.GONE);
            holder.buttonCollapse.setVisibility(View.GONE);
            holder.buttonAddTask.setVisibility(View.GONE);
        });

        holder.buttonAddTask.setOnClickListener(view -> {
            // Создаем диалог для ввода задания
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Добавить задание");

            // Настраиваем разметку для диалога (используем уже существующую разметку)
            View dialogView = LayoutInflater.from(context).inflate(R.layout.activity_add_task, null);
            builder.setView(dialogView);

            // Получаем ссылки на элементы ввода из разметки
            EditText editTextTaskName = dialogView.findViewById(R.id.editTextTaskName);
            EditText editTextTaskGrade = dialogView.findViewById(R.id.editTextTaskGrade);

            // Настраиваем кнопку Сохранить
            builder.setPositiveButton("Сохранить", (dialog, which) -> {
                String taskName = editTextTaskName.getText().toString();
                String taskGradeStr = editTextTaskGrade.getText().toString();

                if (!taskName.isEmpty() && !taskGradeStr.isEmpty()) {
                    try {
                        int taskGrade = Integer.parseInt(taskGradeStr);
                        Task newTask = new Task(taskName, taskGrade);
                        lesson.getTasks().add(newTask);

                        taskAdapter.notifyDataSetChanged();
                    } catch (NumberFormatException e) {
                        Toast.makeText(context, "Введите корректную оценку", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }



    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLessonName;
        RecyclerView recyclerViewTasks;
        Button buttonAddTask;
        Button buttonCollapse;

        public LessonViewHolder(View itemView) {
            super(itemView);
            textViewLessonName = itemView.findViewById(R.id.textViewLessonName);
            recyclerViewTasks = itemView.findViewById(R.id.recyclerView_tasks);
            buttonAddTask = itemView.findViewById(R.id.button_add_task);
            buttonCollapse = itemView.findViewById(R.id.button_collapse);
        }
    }
}



