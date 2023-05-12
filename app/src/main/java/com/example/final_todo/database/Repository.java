package com.example.final_todo.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.final_todo.model.Category;
import com.example.final_todo.model.Todo;

import java.util.List;

public class Repository {
    private CategoryDao categoryRepostiroy;

    private TodoDao todoRepository;

    public Repository(AppDatabase appDatabase) {
        this.categoryRepostiroy = appDatabase.categoryDao();
    }

    public void insertCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.insertCategory(category);
        });
    }

    public void updateCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.updateCategory(category);
        });
    }

    public void deleteCategory(Category category){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.deleteCategory(category);
        });
    }

    public LiveData<List<Category>> loadAllCategory(){
        return categoryRepostiroy.loadAllCategory();
    }

    public LiveData<List<Category>> loadCategoryById(int categoryId) {
        return categoryRepostiroy.loadCategoryById(categoryId);
    }

    public void insertTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.insert(todo);
        });
    }

    public LiveData<List<Todo>> loadAllTodo(int categoryId){
        return todoRepository.loadTodoByCategoryId(categoryId);
    }
}
