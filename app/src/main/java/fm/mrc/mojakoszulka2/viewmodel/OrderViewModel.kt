package fm.mrc.mojakoszulka2.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fm.mrc.mojakoszulka2.data.OrderData

sealed class OrderValidationState {
    object Valid : OrderValidationState()
    data class Invalid(val errors: List<String>) : OrderValidationState()
}

class OrderViewModel : ViewModel() {
    private val _orderData = MutableLiveData(OrderData())
    val orderData: LiveData<OrderData> = _orderData

    private val _validationState = MutableLiveData<OrderValidationState>()
    val validationState: LiveData<OrderValidationState> = _validationState

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

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
} 