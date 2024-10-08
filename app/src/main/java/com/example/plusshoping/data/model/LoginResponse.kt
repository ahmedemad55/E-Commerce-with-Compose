package com.example.plusshoping.data.model

data class LoginResponse(
    val `data`: LoginData,
    val message: String,
    val status: Boolean
)