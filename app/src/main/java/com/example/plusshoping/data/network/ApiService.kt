package com.example.plusshoping.data.network

import androidx.compose.ui.text.intl.Locale
import com.example.plusshoping.data.interceptor.AuthInterceptor
import com.example.plusshoping.data.model.BannerResponse
import com.example.plusshoping.data.model.CategoriesResponse
import com.example.plusshoping.data.model.LoginRequest
import com.example.plusshoping.data.model.LoginResponse
import com.example.plusshoping.data.model.RegisterRequest
import com.example.plusshoping.data.model.RegisterResponse
import com.example.plusshoping.data.model.home.HomeResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("banners")
    suspend fun getBanners(): Response<BannerResponse?>

    @GET("categories")
    suspend fun getCategories(): Response<CategoriesResponse?>

    @GET("home")
    suspend fun getProducts(): Response<HomeResponse?>





}

/*
object RetrofitInstance{
    // Create a logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Logs the request and response body
    }
    var language = getDeviceLanguage()
    var authInterceptor = AuthInterceptor(language = language)

    // Create an OkHttpClient and add the logging interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://student.valuxapps.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiClient = retrofit.create(ApiService::class.java)

    fun setAuthToken(token: String) {
        authInterceptor.setToken(token) }

    fun getDeviceLanguage(): String {
        val language = Locale.current.language
        return if (language == "ar") "ar" else "en"
    }

}

 */

