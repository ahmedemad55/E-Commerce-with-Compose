package com.example.plusshoping.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.plusshoping.data.interceptor.AuthInterceptor
import com.example.plusshoping.data.model.BannerResponse
import com.example.plusshoping.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val authInterceptor: AuthInterceptor,
): ViewModel() {

//    private val _cartItems = mutableStateOf<CartItem?>(null)
//    val cartItems: StateFlow<CartItem?> get() = _cartItems



}

