package com.example.plusshoping.data.model

data class RegisterResponse(
    val `data`: RegisterData,
    val message: String,
    val status: Boolean
)