package com.example.mojakoszulka2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mojakoszulka2.viewmodel.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WzorScreen(
    viewModel: OrderViewModel,
    onNavigateNext: () -> Unit
) {
    var expandedColor by remember { mutableStateOf(false) }
    var expandedSize by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Wybór koloru
        ExposedDropdownMenuBox(
            expanded = expandedColor,
            onExpandedChange = { expandedColor = !expandedColor }
        ) {
            TextField(
                value = viewModel.orderData.value?.shirtColor ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Kolor koszulki") },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedColor,
                onDismissRequest = { expandedColor = false }
            ) {
                listOf("biały", "czarny", "czerwony", "niebieski").forEach { color ->
                    DropdownMenuItem(
                        text = { Text(color) },
                        onClick = {
                            viewModel.setShirtColor(color)
                            expandedColor = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateNext,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dalej")
        }
    }
} 