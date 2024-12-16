package fm.mrc.mojakoszulka2.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class OrderViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when personal data is empty validation should fail`() {
        val viewModel = OrderViewModel()
        
        viewModel.setPersonalData("", "", "", "", "", "", "", "")
        
        val validationState = viewModel.validationState.value
        assertTrue(validationState is OrderValidationState.Invalid)
        validationState as OrderValidationState.Invalid
        assertTrue(validationState.errors.isNotEmpty())
    }

    @Test
    fun `when personal data is valid validation should pass`() {
        val viewModel = OrderViewModel()
        
        viewModel.setPersonalData(
            "Jan",
            "Kowalski",
            "Firma",
            "Ulica 1",
            "00-000",
            "Warszawa",
            "Polska",
            "1234567890"
        )
        
        val validationState = viewModel.validationState.value
        assertTrue(validationState is OrderValidationState.Valid)
    }
} 