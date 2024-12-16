package com.example.mojakoszulka2.data.mapper

import android.net.Uri
import com.example.mojakoszulka2.data.OrderData
import com.example.mojakoszulka2.data.local.OrderEntity

fun OrderEntity.toOrderData() = OrderData(
    shirtColor = shirtColor,
    shirtSize = shirtSize,
    shirtImageUri = shirtImageUriString?.let { Uri.parse(it) },
    shirtImageShape = shirtImageShape,
    includeBackSide = includeBackSide,
    backShirtImageUri = backShirtImageUriString?.let { Uri.parse(it) },
    backShirtImageShape = backShirtImageShape,
    firstName = firstName,
    lastName = lastName,
    companyName = companyName,
    street = street,
    postalCode = postalCode,
    city = city,
    country = country,
    nip = nip,
    shippingStreet = shippingStreet,
    shippingPostalCode = shippingPostalCode,
    shippingCity = shippingCity,
    shippingCountry = shippingCountry,
    shippingMethod = shippingMethod,
    paymentMethod = paymentMethod,
    discountCode = discountCode
)

fun OrderData.toEntity() = OrderEntity(
    shirtColor = shirtColor,
    shirtSize = shirtSize,
    shirtImageUriString = shirtImageUri?.toString(),
    shirtImageShape = shirtImageShape,
    includeBackSide = includeBackSide,
    backShirtImageUriString = backShirtImageUri?.toString(),
    backShirtImageShape = backShirtImageShape,
    firstName = firstName,
    lastName = lastName,
    companyName = companyName,
    street = street,
    postalCode = postalCode,
    city = city,
    country = country,
    nip = nip,
    shippingStreet = shippingStreet,
    shippingPostalCode = shippingPostalCode,
    shippingCity = shippingCity,
    shippingCountry = shippingCountry,
    shippingMethod = shippingMethod,
    paymentMethod = paymentMethod,
    discountCode = discountCode
) 