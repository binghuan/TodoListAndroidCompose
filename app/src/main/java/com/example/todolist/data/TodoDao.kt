package com.example.todolist.data

import androidx.room.*
import com.example.todolist.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    
    @Query("SELECT * FROM todo_items ORDER BY timestamp DESC")
    fun getAllTodos(): Flow<List<TodoItem>>
    
    @Query("SELECT * FROM todo_items WHERE id = :id")
    suspend fun getTodoById(id: String): TodoItem?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoItem)
    
    @Update
    suspend fun updateTodo(todo: TodoItem)
    
    @Delete
    suspend fun deleteTodo(todo: TodoItem)
    
    @Query("DELETE FROM todo_items WHERE id = :id")
    suspend fun deleteTodoById(id: String)
    
    @Query("DELETE FROM todo_items")
    suspend fun deleteAllTodos()
    
    @Query("SELECT * FROM todo_items WHERE isCompleted = 0 ORDER BY timestamp DESC")
    fun getActiveTodos(): Flow<List<TodoItem>>
    
    @Query("SELECT * FROM todo_items WHERE isCompleted = 1 ORDER BY timestamp DESC")
    fun getCompletedTodos(): Flow<List<TodoItem>>
} 