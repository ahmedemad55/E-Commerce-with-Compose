package com.example.plusshoping.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plusshoping.data.interceptor.AuthInterceptor
import com.example.plusshoping.data.model.BannerResponse
import com.example.plusshoping.data.model.CategoriesResponse
import com.example.plusshoping.data.model.home.HomeResponse
import com.example.plusshoping.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val authInterceptor: AuthInterceptor,
) : ViewModel() {

    private val _banners = MutableStateFlow<BannerResponse?>(null)
    val banners: StateFlow<BannerResponse?> get() = _banners

    private val _allCategories = MutableStateFlow<CategoriesResponse?>(null)
    val allCategories: StateFlow<CategoriesResponse?> get() = _allCategories

    private val _products = MutableStateFlow<HomeResponse?>(null)
    val products: StateFlow<HomeResponse?> get() = _products

    // Error handling
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    init {
        getBanners()
        getHomeProduct()
        getCategories()
    }


    // Function to load the data for banners, brands, and products
    fun getBanners( /*onSuccess: (String) -> Unit, onError: (String) -> Unit */) {
        viewModelScope.launch {
            try {
                // Fetch banners
                val bannerResponse = apiRepository.getBanners()
                if (bannerResponse.isSuccessful) {
                    _banners.value = bannerResponse.body()
                    _errorMessage.value = null
//                    onSuccess("Home data loaded successfully")

                } else {
                    _errorMessage.value = "Error loading banners: ${bannerResponse.code()}"
//                    onError(_errorMessage.value ?: "Unknown error")
                    return@launch
                }



            } catch (e: Exception) {
                _errorMessage.value = "Network error: ${e.localizedMessage}"
//                onError(_errorMessage.value ?: "Unknown error")
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val categoriesResponse = apiRepository.getCategories()
                if (categoriesResponse.isSuccessful) {
                    _allCategories.value = categoriesResponse.body()
                    _errorMessage.value = null
                    Log.d("Ahmed", "getCategories: ${allCategories.value}")
                } else {
                    Log.d("Ahmed", "getCategories: ${categoriesResponse.message()}")
                    _errorMessage.value = "Error loading categories: ${categoriesResponse.code()}"
                }
            } catch (e: Exception) {
                Log.d("Ahmed", "getCategories: ${e.message}")
                _errorMessage.value = "Network error: ${e.localizedMessage}"
            }

        }
    }

    fun getHomeProduct() {
        viewModelScope.launch {
            try {
                val productResponse = apiRepository.getProducts()
                if (productResponse.isSuccessful) {
                    _products.value = productResponse.body()
                    _errorMessage.value = null
                    Log.d("Ahmed", "getHome: ${products.value}")
                } else {
                    Log.d("Ahmed", "getHome: ${productResponse.message()}")
                    _errorMessage.value = "Error loading products: ${productResponse.code()}"
                }
            } catch (e: Exception) {
                Log.d("Ahmed", "getHome: ${e.message}")
                _errorMessage.value = "Network error: ${e.localizedMessage}"
            }

        }
    }


}

