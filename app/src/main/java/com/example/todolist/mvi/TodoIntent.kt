package com.example.todolist.mvi

import com.example.todolist.model.TodoFilter

sealed class TodoIntent {
    data class AddTodo(val title: String) : TodoIntent()
    data class ToggleTodo(val id: String) : TodoIntent()
    data class DeleteTodo(val id: String) : TodoIntent()
    data class SetFilter(val filter: TodoFilter) : TodoIntent()
    data class UpdateTodoTitle(val id: String, val title: String) : TodoIntent()
} 