package com.example.engaz.features.auth.data.entities.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Error? = null
)

data class Error(

	@field:SerializedName("message")
	val message: String? = null
)
