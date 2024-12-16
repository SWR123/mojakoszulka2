package com.example.mojakoszulka2.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mojakoszulka2.data.OrderData
import com.example.mojakoszulka2.data.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class OrderValidationState {
    object Valid : OrderValidationState()
    data class Invalid(val errors: List<String>) : OrderValidationState()
}

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {
    private val _orderData = MutableLiveData(OrderData())
    val orderData: LiveData<OrderData> = _orderData

    private val _validationState = MutableLiveData<OrderValidationState>()
    val validationState: LiveData<OrderValidationState> = _validationState

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun setShirtColor(color: String) { updateData { copy(shirtColor = color) } }
    fun setShirtSize(size: String) { updateData { copy(shirtSize = size) } }
    fun setShirtImage(uri: Uri?) { updateData { copy(shirtImageUri = uri) } }
    fun setShirtImageShape(shape: String) { updateData { copy(shirtImageShape = shape) } }
    fun setIncludeBackSide(include: Boolean) { updateData { copy(includeBackSide = include) } }
    fun setBackShirtImage(uri: Uri?) { updateData { copy(backShirtImageUri = uri) } }
    fun setBackShirtImageShape(shape: String) { updateData { copy(backShirtImageShape = shape) } }

    fun setPersonalData(
        firstName: String,
        lastName: String,
        companyName: String,
        street: String,
        postalCode: String,
        city: String,
        country: String,
        nip: String
    ) {
        updateData {
            copy(
                firstName = firstName,
                lastName = lastName,
                companyName = companyName,
                street = street,
                postalCode = postalCode,
                city = city,
                country = country,
                nip = nip
            )
        }
        validatePersonalData()
    }

    fun setShippingAddress(
        shippingStreet: String,
        shippingPostalCode: String,
        shippingCity: String,
        shippingCountry: String
    ) {
        updateData {
            copy(
                shippingStreet = shippingStreet,
                shippingPostalCode = shippingPostalCode,
                shippingCity = shippingCity,
                shippingCountry = shippingCountry
            )
        }
    }

    fun setShippingMethod(method: String) { updateData { copy(shippingMethod = method) } }
    fun setPaymentMethod(method: String) { updateData { copy(paymentMethod = method) } }
    fun setDiscountCode(code: String) { updateData { copy(discountCode = code) } }

    private fun validatePersonalData() {
        val errors = mutableListOf<String>()
        val data = _orderData.value!!

        if (data.firstName.isBlank()) errors.add("Imię jest wymagane")
        if (data.lastName.isBlank()) errors.add("Nazwisko jest wymagane")
        if (data.street.isBlank()) errors.add("Ulica jest wymagana")
        if (data.postalCode.isBlank()) errors.add("Kod pocztowy jest wymagany")
        if (data.city.isBlank()) errors.add("Miasto jest wymagane")
        
        _validationState.value = if (errors.isEmpty()) {
            OrderValidationState.Valid
        } else {
            OrderValidationState.Invalid(errors)
        }
    }

    private inline fun updateData(block: OrderData.() -> OrderData) {
        try {
            val current = _orderData.value ?: OrderData()
            _orderData.value = current.block()
        } catch (e: Exception) {
            _error.value = "Błąd podczas aktualizacji danych: ${e.message}"
        }
    }

    fun saveOrder() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _orderData.value?.let { order ->
                    repository.saveOrder(order)
                    _error.value = null
                }
            } catch (e: Exception) {
                _error.value = "Błąd podczas zapisywania zamówienia: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
} 