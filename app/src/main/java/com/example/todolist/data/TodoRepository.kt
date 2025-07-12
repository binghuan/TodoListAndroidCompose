package com.example.todolist.data

import com.example.todolist.model.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    
    fun getAllTodos(): Flow<List<TodoItem>> = todoDao.getAllTodos()
    
    fun getActiveTodos(): Flow<List<TodoItem>> = todoDao.getActiveTodos()
    
    fun getCompletedTodos(): Flow<List<TodoItem>> = todoDao.getCompletedTodos()
    
    suspend fun getTodoById(id: String): TodoItem? = todoDao.getTodoById(id)
    
    suspend fun insertTodo(todo: TodoItem) = todoDao.insertTodo(todo)
    
    suspend fun updateTodo(todo: TodoItem) = todoDao.updateTodo(todo)
    
    suspend fun deleteTodo(todo: TodoItem) = todoDao.deleteTodo(todo)
    
    suspend fun deleteTodoById(id: String) = todoDao.deleteTodoById(id)
    
    suspend fun deleteAllTodos() = todoDao.deleteAllTodos()
} 