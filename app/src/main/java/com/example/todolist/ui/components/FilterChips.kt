package com.example.todolist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.model.TodoFilter

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