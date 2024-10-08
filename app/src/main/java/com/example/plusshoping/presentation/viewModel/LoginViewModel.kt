package com.example.plusshoping.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plusshoping.data.interceptor.AuthInterceptor
import com.example.plusshoping.data.model.LoginRequest
import com.example.plusshoping.data.model.LoginResponse
import com.example.plusshoping.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(val apiRepository: ApiRepository, val authInterceptor: AuthInterceptor): ViewModel () {

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse : StateFlow<LoginResponse?> get() = _loginResponse

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value


    fun login( email: String, password: String, onSuccess : (String) -> Unit, onError : (String) -> Unit){

        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email = email, password = password)
                val response = apiRepository.login(loginRequest)

                if (response.isSuccessful){
                    if (response.body()?.status == true){

                        _loginResponse.value = response.body()
                        authInterceptor.setToken(response.body()?.data?.token?:"")
                        _errorMessage.value = null
                        onSuccess(response.body()?.message?:"")

                    }else{
                        _errorMessage.value = response.body()?.message
                        onError(response.body()?.message?:"")
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