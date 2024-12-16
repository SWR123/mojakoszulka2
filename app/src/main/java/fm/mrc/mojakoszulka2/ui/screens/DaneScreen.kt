package fm.mrc.mojakoszulka2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fm.mrc.mojakoszulka2.viewmodel.OrderViewModel

@Composable
fun DaneScreen(
    viewModel: OrderViewModel = viewModel(),
    onNavigateNext: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Imię") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Nazwisko") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = street,
            onValueChange = { street = it },
            label = { Text("Ulica") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = postalCode,
                onValueChange = { postalCode = it },
                label = { Text("Kod pocztowy") },
                modifier = Modifier.weight(0.4f)
            )
            
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Miasto") },
                modifier = Modifier.weight(0.6f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.setPersonalData(
                    firstName = firstName,
                    lastName = lastName,
                    companyName = "",
                    street = street,
                    postalCode = postalCode,
                    city = city,
                    country = "Polska",
                    nip = ""
                )
                onNavigateNext()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dalej")
        }
    }
} 