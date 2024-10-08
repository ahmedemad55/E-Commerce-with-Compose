package com.example.plusshoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.plusshoping.data.interceptor.AuthInterceptor
import com.example.plusshoping.presentation.view.HomeScreen
import com.example.plusshoping.presentation.view.OnboardingScreen
import com.example.plusshoping.presentation.view.RegisterScreen
import com.example.plusshoping.presentation.viewModel.LoginViewModel
import com.example.plusshoping.presentation.viewModel.RegisterViewModel
import com.example.plusshoping.ui.theme.PlusShopingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val registerViewModel: RegisterViewModel = hiltViewModel()
//            val showOnboarding by remember { mutableStateOf(true) }
//
//            if (showOnboarding) {
//                Navigator(screen = OnboardingScreen())
//
//            } else {
//                Navigator(screen = RegisterScreen())
//            }
//            val authInterceptor = AuthInterceptor()
//            authInterceptor.setLanguage(RetrofitInstance.getDeviceLanguage())

          Navigator(screen = HomeScreen())


        }
    }
}
