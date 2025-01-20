package com.example.baythngai

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("search") searchQuery: String? = null,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 10
    ): ApiResponse
}
