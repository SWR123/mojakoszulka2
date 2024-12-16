package fm.mrc.mojakoszulka2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fm.mrc.mojakoszulka2.viewmodel.OrderViewModel

@Composable
fun WzorScreen(
    viewModel: OrderViewModel = viewModel(),
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

        // Wybór rozmiaru
        ExposedDropdownMenuBox(
            expanded = expandedSize,
            onExpandedChange = { expandedSize = !expandedSize }
        ) {
            TextField(
                value = viewModel.orderData.value?.shirtSize ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Rozmiar") },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedSize,
                onDismissRequest = { expandedSize = false }
            ) {
                listOf("S", "M", "L", "XL", "XXL").forEach { size ->
                    DropdownMenuItem(
                        text = { Text(size) },
                        onClick = {
                            viewModel.setShirtSize(size)
                            expandedSize = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateNext,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dalej")
        }
    }
} 