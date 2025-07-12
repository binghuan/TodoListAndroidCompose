package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolist.model.TodoItem
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListAndroidComposeTheme

@Composable
fun TodoItemCard(
    todoItem: TodoItem,
    onToggle: (String) -> Unit,
    onDelete: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todoItem.isCompleted,
                onCheckedChange = { onToggle(todoItem.id) }
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Text(
                text = todoItem.title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (todoItem.isCompleted) {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                textDecoration = if (todoItem.isCompleted) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                },
                modifier = Modifier.weight(1f)
            )
            
            IconButton(
                onClick = { onDelete(todoItem.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete todo",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Todo Item - Active")
@Composable
fun TodoItemCardActivePreview() {
    TodoListAndroidComposeTheme {
        TodoItemCard(
            todoItem = TodoItem(
                id = "1",
                title = "Buy groceries for the week",
                isCompleted = false,
                timestamp = System.currentTimeMillis()
            ),
            onToggle = { /* Preview - no action needed */ },
            onDelete = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Todo Item - Completed")
@Composable
fun TodoItemCardCompletedPreview() {
    TodoListAndroidComposeTheme {
        TodoItemCard(
            todoItem = TodoItem(
                id = "2",
                title = "Finish Android project documentation",
                isCompleted = true,
                timestamp = System.currentTimeMillis() - 1000
            ),
            onToggle = { /* Preview - no action needed */ },
            onDelete = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Todo Item - Long Title")
@Composable
fun TodoItemCardLongTitlePreview() {
    TodoListAndroidComposeTheme {
        TodoItemCard(
            todoItem = TodoItem(
                id = "3",
                title = "This is a very long todo item title that should wrap properly and demonstrate how the card handles longer text content",
                isCompleted = false,
                timestamp = System.currentTimeMillis() - 2000
            ),
            onToggle = { /* Preview - no action needed */ },
            onDelete = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Todo Items - Multiple")
@Composable
fun TodoItemCardMultiplePreview() {
    TodoListAndroidComposeTheme {
        Column {
            TodoItemCard(
                todoItem = TodoItem(
                    id = "1",
                    title = "Active todo item",
                    isCompleted = false,
                    timestamp = System.currentTimeMillis()
                ),
                onToggle = { /* Preview - no action needed */ },
                onDelete = { /* Preview - no action needed */ }
            )
            
            TodoItemCard(
                todoItem = TodoItem(
                    id = "2",
                    title = "Completed todo item",
                    isCompleted = true,
                    timestamp = System.currentTimeMillis() - 1000
                ),
                onToggle = { /* Preview - no action needed */ },
                onDelete = { /* Preview - no action needed */ }
            )
            
            TodoItemCard(
                todoItem = TodoItem(
                    id = "3",
                    title = "Another active item",
                    isCompleted = false,
                    timestamp = System.currentTimeMillis() - 2000
                ),
                onToggle = { /* Preview - no action needed */ },
                onDelete = { /* Preview - no action needed */ }
            )
        }
    }
} 