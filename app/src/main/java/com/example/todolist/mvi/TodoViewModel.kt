package com.example.todolist.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoRepository
import com.example.todolist.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    
    private val _state = MutableStateFlow(TodoState())
    val state: StateFlow<TodoState> = _state.asStateFlow()
    
    init {
        observeTodos()
    }
    
    private fun observeTodos() {
        viewModelScope.launch {
            combine(
                repository.getAllTodos(),
                _state
            ) { todos, currentState ->
                currentState.copy(
                    todos = todos,
                    isLoading = false
                )
            }.collect { newState ->
                _state.value = newState
            }
        }
    }
    
    fun handleIntent(intent: TodoIntent) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                when (intent) {
                    is TodoIntent.AddTodo -> addTodo(intent.title)
                    is TodoIntent.ToggleTodo -> toggleTodo(intent.id)
                    is TodoIntent.DeleteTodo -> deleteTodo(intent.id)
                    is TodoIntent.SetFilter -> setFilter(intent.filter)
                    is TodoIntent.UpdateTodoTitle -> updateTodoTitle(intent.id, intent.title)
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
    
    private suspend fun addTodo(title: String) {
        if (title.isBlank()) return
        
        val newTodo = TodoItem(title = title.trim())
        repository.insertTodo(newTodo)
    }
    
    private suspend fun toggleTodo(id: String) {
        val todo = repository.getTodoById(id)
        if (todo != null) {
            val updatedTodo = todo.copy(isCompleted = !todo.isCompleted)
            repository.updateTodo(updatedTodo)
        }
    }
    
    private suspend fun deleteTodo(id: String) {
        repository.deleteTodoById(id)
    }
    
    private fun setFilter(filter: com.example.todolist.model.TodoFilter) {
        _state.value = _state.value.copy(filter = filter)
    }
    
    private suspend fun updateTodoTitle(id: String, title: String) {
        if (title.isBlank()) return
        
        val todo = repository.getTodoById(id)
        if (todo != null) {
            val updatedTodo = todo.copy(title = title.trim())
            repository.updateTodo(updatedTodo)
        }
    }
} 