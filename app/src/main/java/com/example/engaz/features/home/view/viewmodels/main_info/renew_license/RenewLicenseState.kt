package com.example.engaz.features.home.view.viewmodels.main_info.renew_license

data class RenewLicenseState(

    var licenseNumber : String = "",
    var licenseNumberError: String? =null,

    var driverName : String= "",
    var driverNameError: String? =null,

    var expiryDate : String= "",
    var expiryDateError: String? =null,

    var carDescription : String = "",
    var carDescriptionError : String? = null,

    var currentAddress : String = "",
    var currentAddressError : String? = null,

    var email : String = "",
    var emailError : String? = null,

    var phone : String = "",
    var phoneError : String? = null,

    var requestNumber : String = "",
    var requestNumberError : String? = null,

    )
