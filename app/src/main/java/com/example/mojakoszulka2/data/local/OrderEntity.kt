package com.example.mojakoszulka2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.net.Uri

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shirtColor: String,
    val shirtSize: String,
    val shirtImageUriString: String?,
    val shirtImageShape: String,
    val includeBackSide: Boolean,
    val backShirtImageUriString: String?,
    val backShirtImageShape: String,
    val firstName: String,
    val lastName: String,
    val companyName: String,
    val street: String,
    val postalCode: String,
    val city: String,
    val country: String,
    val nip: String,
    val shippingStreet: String,
    val shippingPostalCode: String,
    val shippingCity: String,
    val shippingCountry: String,
    val shippingMethod: String,
    val paymentMethod: String,
    val discountCode: String,
    val timestamp: Long = System.currentTimeMillis()
) 