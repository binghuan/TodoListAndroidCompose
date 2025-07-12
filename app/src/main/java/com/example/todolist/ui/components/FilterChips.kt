package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.model.TodoFilter
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListAndroidComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChips(
    currentFilter: TodoFilter,
    onFilterChange: (TodoFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = currentFilter == TodoFilter.ALL,
            onClick = { onFilterChange(TodoFilter.ALL) },
            label = { Text("All") }
        )
        
        FilterChip(
            selected = currentFilter == TodoFilter.ACTIVE,
            onClick = { onFilterChange(TodoFilter.ACTIVE) },
            label = { Text("Active") }
        )
        
        FilterChip(
            selected = currentFilter == TodoFilter.COMPLETED,
            onClick = { onFilterChange(TodoFilter.COMPLETED) },
            label = { Text("Completed") }
        )
    }
}

@Preview(showBackground = true, name = "Filter Chips - All Selected")
@Composable
fun FilterChipsAllSelectedPreview() {
    TodoListAndroidComposeTheme {
        FilterChips(
            currentFilter = TodoFilter.ALL,
            onFilterChange = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Filter Chips - Active Selected")
@Composable
fun FilterChipsActiveSelectedPreview() {
    TodoListAndroidComposeTheme {
        FilterChips(
            currentFilter = TodoFilter.ACTIVE,
            onFilterChange = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Filter Chips - Completed Selected")
@Composable
fun FilterChipsCompletedSelectedPreview() {
    TodoListAndroidComposeTheme {
        FilterChips(
            currentFilter = TodoFilter.COMPLETED,
            onFilterChange = { /* Preview - no action needed */ }
        )
    }
}

@Preview(showBackground = true, name = "Filter Chips - All States")
@Composable
fun FilterChipsAllStatesPreview() {
    TodoListAndroidComposeTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "All Selected",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            FilterChips(
                currentFilter = TodoFilter.ALL,
                onFilterChange = { /* Preview - no action needed */ }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Active Selected",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            FilterChips(
                currentFilter = TodoFilter.ACTIVE,
                onFilterChange = { /* Preview - no action needed */ }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Completed Selected",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            FilterChips(
                currentFilter = TodoFilter.COMPLETED,
                onFilterChange = { /* Preview - no action needed */ }
            )
        }
    }
} 