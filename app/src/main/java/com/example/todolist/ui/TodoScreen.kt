package com.example.todolist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.model.TodoFilter
import com.example.todolist.model.TodoItem
import com.example.todolist.mvi.TodoIntent
import com.example.todolist.mvi.TodoState
import com.example.todolist.mvi.TodoViewModel
import com.example.todolist.ui.components.AddTodoInput
import com.example.todolist.ui.components.FilterChips
import com.example.todolist.ui.components.TodoItemCard
import com.example.todolist.ui.theme.TodoListAndroidComposeTheme

@Composable
fun TodoScreen(
    viewModel: TodoViewModel
) {
    val state by viewModel.state.collectAsState()
    
    TodoScreenContent(
        state = state,
        onAddTodo = { title ->
            viewModel.handleIntent(TodoIntent.AddTodo(title))
        },
        onToggleTodo = { id ->
            viewModel.handleIntent(TodoIntent.ToggleTodo(id))
        },
        onDeleteTodo = { id ->
            viewModel.handleIntent(TodoIntent.DeleteTodo(id))
        },
        onSetFilter = { filter ->
            viewModel.handleIntent(TodoIntent.SetFilter(filter))
        }
    )
} 

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoListAndroidComposeTheme {
        TodoScreenContent(
            state = TodoState(
                todos = listOf(
                    TodoItem(
                        id = "1",
                        title = "Buy groceries",
                        isCompleted = false,
                        timestamp = System.currentTimeMillis()
                    ),
                    TodoItem(
                        id = "2", 
                        title = "Finish Android project",
                        isCompleted = true,
                        timestamp = System.currentTimeMillis() - 1000
                    ),
                    TodoItem(
                        id = "3",
                        title = "Call dentist",
                        isCompleted = false,
                        timestamp = System.currentTimeMillis() - 2000
                    )
                ),
                filter = TodoFilter.ALL,
                isLoading = false,
                error = null
            ),
            onAddTodo = { },
            onToggleTodo = { },
            onDeleteTodo = { },
            onSetFilter = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenEmptyPreview() {
    TodoListAndroidComposeTheme {
        TodoScreenContent(
            state = TodoState(
                todos = emptyList(),
                filter = TodoFilter.ALL,
                isLoading = false,
                error = null
            ),
            onAddTodo = { },
            onToggleTodo = { },
            onDeleteTodo = { },
            onSetFilter = { }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreenContent(
    state: TodoState,
    onAddTodo: (String) -> Unit,
    onToggleTodo: (String) -> Unit,
    onDeleteTodo: (String) -> Unit,
    onSetFilter: (TodoFilter) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.todo_list_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Add Todo Input
            AddTodoInput(
                onAddTodo = onAddTodo
            )
            
            // Filter Chips
            FilterChips(
                currentFilter = state.filter,
                onFilterChange = onSetFilter
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Stats
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${state.todos.size}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = stringResource(R.string.stats_total),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${state.activeCount}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = stringResource(R.string.stats_active),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${state.completedCount}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = stringResource(R.string.stats_completed),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Todo List
            if (state.filteredTodos.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (state.filter) {
                            TodoFilter.ALL -> stringResource(R.string.no_todos_all)
                            TodoFilter.ACTIVE -> stringResource(R.string.no_todos_active)
                            TodoFilter.COMPLETED -> stringResource(R.string.no_todos_completed)
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(
                        items = state.filteredTodos,
                        key = { it.id }
                    ) { todo ->
                        TodoItemCard(
                            todoItem = todo,
                            onToggle = onToggleTodo,
                            onDelete = onDeleteTodo
                        )
                    }
                }
            }
        }
    }
} 