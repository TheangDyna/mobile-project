package com.example.baythngai

class ProductRepository {
    private val api = RetrofitInstance.api

    suspend fun getProducts(): List<Product> {
        val response = api.getProducts()
        return response.data
    }
}
