package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListAndroidComposeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTodoInput(
    onAddTodo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var todoText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = todoText,
                onValueChange = { todoText = it },
                placeholder = { Text("Enter your todo here...") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (todoText.isNotBlank()) {
                            onAddTodo(todoText)
                            todoText = ""
                        }
                        keyboardController?.hide()
                    }
                ),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            FloatingActionButton(
                onClick = {
                    if (todoText.isNotBlank()) {
                        onAddTodo(todoText)
                        todoText = ""
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add todo"
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Add Todo Input Default")
@Composable
fun AddTodoInputPreview() {
    TodoListAndroidComposeTheme {
        AddTodoInput(
            onAddTodo = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Add Todo Input with Custom Padding")
@Composable
fun AddTodoInputPreviewWithPadding() {
    TodoListAndroidComposeTheme {
        AddTodoInput(
            onAddTodo = { /* Preview - no action needed */ },
            modifier = Modifier.padding(8.dp)
        )
    }
} 