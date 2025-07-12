package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.TodoDatabase
import com.example.todolist.data.TodoRepository
import com.example.todolist.mvi.TodoViewModelFactory
import com.example.todolist.ui.TodoScreen
import com.example.todolist.ui.theme.TodoListAndroidComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize database and repository
        val database = TodoDatabase.getDatabase(this)
        val repository = TodoRepository(database.todoDao())
        val viewModelFactory = TodoViewModelFactory(repository)
        
        setContent {
            TodoListAndroidComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoScreen(
                        viewModel = viewModel(factory = viewModelFactory)
                    )
                }
            }
        }
    }
} 