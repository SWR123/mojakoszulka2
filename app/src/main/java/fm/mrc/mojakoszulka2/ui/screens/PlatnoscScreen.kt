package fm.mrc.mojakoszulka2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fm.mrc.mojakoszulka2.viewmodel.OrderViewModel

@Composable
fun PlatnoscScreen(
    viewModel: OrderViewModel = viewModel(),
    onSubmitOrder: () -> Unit
) {
    var selectedPaymentMethod by remember { mutableStateOf("karta") }
    var discountCode by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Metoda płatności",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RadioButton(
                selected = selectedPaymentMethod == "karta",
                onClick = { selectedPaymentMethod = "karta" }
            )
            Text("Karta płatnicza")
            
            RadioButton(
                selected = selectedPaymentMethod == "przelew",
                onClick = { selectedPaymentMethod = "przelew" }
            )
            Text("Przelew")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = discountCode,
            onValueChange = { discountCode = it },
            label = { Text("Kod rabatowy") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.setPaymentMethod(selectedPaymentMethod)
                viewModel.setDiscountCode(discountCode)
                onSubmitOrder()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Złóż zamówienie")
        }
    }
} 