package com.example.engaz.features.home.view.viewmodels.home

data class HomeState(

    var profileImage : String? = null,
    var username : String? = null,
    var wallet : Double? = null,
    var currency : String? = null,

    var assistantImage : String? = null,
    var assistantName : String? = null,

    var isHomePageLoading : Boolean = false,
    var error : String? = null,

    )
