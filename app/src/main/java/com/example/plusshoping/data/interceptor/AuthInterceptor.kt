package com.example.plusshoping.data.interceptor

import androidx.compose.ui.text.intl.Locale
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private var authToken:String?=null, private var language:String?=null) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        authToken?.let {
            requestBuilder.addHeader("Authorization", it)
        }
        language?.let {
            requestBuilder.addHeader("lang", it)
        }
            requestBuilder.addHeader("Content-Type", "application/json")

        return chain.proceed(requestBuilder.build())
    }

    fun setToken(token:String){
        authToken = token
    }
    fun setLanguage(lang:String){
        language = lang
    }
}
