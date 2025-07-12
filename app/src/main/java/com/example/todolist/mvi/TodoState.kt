package com.example.todolist.mvi

import com.example.todolist.model.TodoFilter
import com.example.todolist.model.TodoItem

data class TodoState(
    val todos: List<TodoItem> = emptyList(),
    val filter: TodoFilter = TodoFilter.ALL,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val filteredTodos: List<TodoItem>
        get() = when (filter) {
            TodoFilter.ALL -> todos
            TodoFilter.ACTIVE -> todos.filter { !it.isCompleted }
            TodoFilter.COMPLETED -> todos.filter { it.isCompleted }
        }
    
    val activeCount: Int
        get() = todos.count { !it.isCompleted }
    
    val completedCount: Int
        get() = todos.count { it.isCompleted }
} 