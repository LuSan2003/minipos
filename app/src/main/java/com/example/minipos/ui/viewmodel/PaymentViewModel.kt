package com.example.minipos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minipos.data.Transaction
import com.example.minipos.data.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class PaymentViewModel(private val repository: TransactionRepository) : ViewModel() {

    private val _paymentState = MutableStateFlow<PaymentState>(PaymentState.Idle)
    val paymentState: StateFlow<PaymentState> = _paymentState.asStateFlow()

    fun processPayment(amount: Double) {
        if (amount <= 0) {
            _paymentState.value = PaymentState.Error("Monto inválido")
            return
        }

        viewModelScope.launch {
            _paymentState.value = PaymentState.Loading
            try {

                // delay(1000) 

                val cardNumber = generateRandomCardNumber()
                val transaction = Transaction(
                    amount = amount,
                    cardNumber = cardNumber,
                    date = System.currentTimeMillis()
                )
                repository.insert(transaction)
                _paymentState.value = PaymentState.Success("Pago exitoso! Tarjeta: **** ${cardNumber.takeLast(4)}")
            } catch (e: Exception) {
                _paymentState.value = PaymentState.Error("Error al procesar el pago: ${e.message}")
            }
        }
    }

    fun resetState() {
        _paymentState.value = PaymentState.Idle
    }

    private fun generateRandomCardNumber(): String {
        return (1..16).map { Random.nextInt(0, 10) }.joinToString("")
    }
}

sealed class PaymentState {
    data object Idle : PaymentState()
    data object Loading : PaymentState()
    data class Success(val message: String) : PaymentState()
    data class Error(val message: String) : PaymentState()
}
