package com.example.mojakoszulka2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mojakoszulka2.viewmodel.OrderViewModel

@Composable
fun WysylkaScreen(
    viewModel: OrderViewModel,
    onNavigateNext: () -> Unit
) {
    var selectedShippingMethod by remember { mutableStateOf("paczkomat") }
    var shippingStreet by remember { mutableStateOf("") }
    var shippingPostalCode by remember { mutableStateOf("") }
    var shippingCity by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Metoda wysyłki",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RadioButton(
                selected = selectedShippingMethod == "paczkomat",
                onClick = { selectedShippingMethod = "paczkomat" }
            )
            Text("Paczkomat")
            
            RadioButton(
                selected = selectedShippingMethod == "kurier",
                onClick = { selectedShippingMethod = "kurier" }
            )
            Text("Kurier")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Adres wysyłki",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        OutlinedTextField(
            value = shippingStreet,
            onValueChange = { shippingStreet = it },
            label = { Text("Ulica") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = shippingPostalCode,
                onValueChange = { shippingPostalCode = it },
                label = { Text("Kod pocztowy") },
                modifier = Modifier.weight(0.4f)
            )
            
            OutlinedTextField(
                value = shippingCity,
                onValueChange = { shippingCity = it },
                label = { Text("Miasto") },
                modifier = Modifier.weight(0.6f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.setShippingMethod(selectedShippingMethod)
                viewModel.setShippingAddress(
                    shippingStreet = shippingStreet,
                    shippingPostalCode = shippingPostalCode,
                    shippingCity = shippingCity,
                    shippingCountry = "Polska"
                )
                onNavigateNext()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dalej")
        }
    }
} 