package com.example.mojakoszulka2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mojakoszulka2.ui.theme.Mojakoszulka2Theme
import com.example.mojakoszulka2.viewmodel.OrderViewModel
import com.example.mojakoszulka2.ui.screens.*
import com.example.mojakoszulka2.util.PdfGenerator
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        viewModel = ViewModelProvider(this)[OrderViewModel::class.java]
        
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            
            Mojakoszulka2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentBackStackEntry?.destination?.route == "wzor",
                                onClick = { navController.navigate("wzor") },
                                icon = { Icon(Icons.Default.Home, contentDescription = "Wzór") },
                                label = { Text("Wzór") }
                            )
                            NavigationBarItem(
                                selected = currentBackStackEntry?.destination?.route == "dane",
                                onClick = { navController.navigate("dane") },
                                icon = { Icon(Icons.Default.Person, contentDescription = "Dane") },
                                label = { Text("Dane") }
                            )
                            NavigationBarItem(
                                selected = currentBackStackEntry?.destination?.route == "wysylka",
                                onClick = { navController.navigate("wysylka") },
                                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Wysyłka") },
                                label = { Text("Wysyłka") }
                            )
                            NavigationBarItem(
                                selected = currentBackStackEntry?.destination?.route == "platnosc",
                                onClick = { navController.navigate("platnosc") },
                                icon = { Icon(Icons.Default.CreditCard, contentDescription = "Płatność") },
                                label = { Text("Płatność") }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "wzor",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("wzor") { 
                            WzorScreen(
                                viewModel = viewModel,
                                onNavigateNext = { navController.navigate("dane") }
                            )
                        }
                        composable("dane") {
                            DaneScreen(
                                viewModel = viewModel,
                                onNavigateNext = { navController.navigate("wysylka") }
                            )
                        }
                        composable("wysylka") {
                            WysylkaScreen(
                                viewModel = viewModel,
                                onNavigateNext = { navController.navigate("platnosc") }
                            )
                        }
                        composable("platnosc") {
                            PlatnoscScreen(
                                viewModel = viewModel,
                                onSubmitOrder = { submitOrder() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun submitOrder() {
        val currentOrder = viewModel.orderData.value ?: return
        try {
            val pdfFile = PdfGenerator().generatePdf(this, currentOrder)
            sendEmailWithPdf(pdfFile)
        } catch (e: Exception) {
            // TODO: Dodaj obsługę błędów
        }
    }

    private fun sendEmailWithPdf(pdfFile: File) {
        try {
            val uri = FileProvider.getUriForFile(
                this,
                "${packageName}.fileprovider",
                pdfFile
            )
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("brzezinscy@yahoo.pl"))
                putExtra(Intent.EXTRA_SUBJECT, "Zamówienie z mojakoszulka.com")
                putExtra(Intent.EXTRA_TEXT, "W załączniku szczegóły zamówienia.")
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(emailIntent, "Wyślij email..."))
        } catch (e: Exception) {
            // TODO: Dodaj obsługę błędów
        }
    }
} 