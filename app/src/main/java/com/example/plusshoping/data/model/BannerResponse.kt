package com.example.plusshoping.data.model

data class BannerResponse(
    val data: List<BannerData>,
    val message: Any,
    val status: Boolean
)