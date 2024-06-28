package com.example.engaz.features.auth.data.entities.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UserLogin(

	@field:SerializedName("phone")
	val phone: Any? = null,

	@field:SerializedName("isVerified")
	val isVerified: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: Any? = null,

	@field:SerializedName("otpCode")
	val otpCode: Any? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("isAdmin")
	val isAdmin: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class Data(

	@field:SerializedName("user")
	val user: UserLogin? = null
)
