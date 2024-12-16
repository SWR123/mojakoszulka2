package fm.mrc.mojakoszulka2.data

import android.net.Uri

data class OrderData(
    var shirtColor: String = "biały",
    var shirtSize: String = "M",
    var shirtImageUri: Uri? = null,
    var shirtImageShape: String = "kwadrat",
    var includeBackSide: Boolean = false,
    var backShirtImageUri: Uri? = null,
    var backShirtImageShape: String = "kwadrat",

    var firstName: String = "",
    var lastName: String = "",
    var companyName: String = "",
    var street: String = "",
    var postalCode: String = "",
    var city: String = "",
    var country: String = "",
    var nip: String = "",
    var shippingStreet: String = "",
    var shippingPostalCode: String = "",
    var shippingCity: String = "",
    var shippingCountry: String = "",

    var shippingMethod: String = "paczkomat",
    var paymentMethod: String = "karta",
    var discountCode: String = ""
) 