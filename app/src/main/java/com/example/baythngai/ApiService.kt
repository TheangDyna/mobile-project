package com.example.baythngai

import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/products")
    suspend fun getProducts(): ApiResponse
}
