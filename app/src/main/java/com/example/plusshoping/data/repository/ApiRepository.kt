package com.example.plusshoping.data.repository

import com.example.plusshoping.data.model.LoginRequest
import com.example.plusshoping.data.model.RegisterRequest
import com.example.plusshoping.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository@Inject constructor(private val apiService: ApiService) {
    suspend fun login(requestLogin: LoginRequest) = apiService.login(requestLogin)
    suspend fun register(requestRegister: RegisterRequest) = apiService.register(requestRegister)
    suspend fun getBanners() = apiService.getBanners()
    suspend fun getCategories() = apiService.getCategories()
    suspend fun getProducts() = apiService.getProducts()
}
//
/*
    suspend fun getHome() = apiService.getHomeData()
    suspend fun getCategories() = apiService.getCategories()
    suspend fun getProductsByCategory(id: String) = apiService.getCategoryById(id)
    suspend fun getFavorites() = apiService.getFavorites()
    suspend fun addOrDeleteFavorite(addOrDeleteRequest:AddOrDeleteRequest) = apiService.addOrDeleteFavorite(addOrDeleteRequest)

 */