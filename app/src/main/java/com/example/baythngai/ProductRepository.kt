package com.example.baythngai

class ProductRepository {
    private val api = RetrofitInstance.api

    suspend fun getProducts(searchQuery: String? = null, page: Int = 1, limit: Int = 10): List<Product> {
        println("Fetching API: search=$searchQuery, page=$page, limit=$limit") // Debug Log
        val response = api.getProducts(searchQuery, page, limit)
        return response.data
    }
}
