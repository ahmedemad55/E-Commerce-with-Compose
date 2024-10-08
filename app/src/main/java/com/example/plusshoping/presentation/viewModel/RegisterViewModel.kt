package com.example.plusshoping.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plusshoping.data.interceptor.AuthInterceptor
import com.example.plusshoping.data.model.RegisterRequest
import com.example.plusshoping.data.model.RegisterResponse
import com.example.plusshoping.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (val apiRepository: ApiRepository, val authInterceptor: AuthInterceptor): ViewModel() {

    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse : StateFlow<RegisterResponse?> get() = _registerResponse


    // To handle errors, you can use a separate mutableStateOf
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    fun register(email: String, password: String, name: String, phone: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {

            try {
                val registerRequest = RegisterRequest(email = email, password = password, name = name, phone = phone)
                val response = apiRepository.register(registerRequest)

                if (response.isSuccessful) {
                    if (response.body()?.status == true){

                        _registerResponse.value = response.body()
                        authInterceptor.setToken(response.body()?.data?.token?:"")
                        _errorMessage.value = null
                        onSuccess(response.body()?.message.toString())

                    }else {
                        _errorMessage.value = response.body()?.message
                        onError(response.body()?.message.toString())
                    }

                } else {
                    _errorMessage.value = "Server error: ${response.code()}"
                }
            }catch (e:Exception){
                _errorMessage.value = "Network error: ${e.localizedMessage}"
            }
        }
    }

}