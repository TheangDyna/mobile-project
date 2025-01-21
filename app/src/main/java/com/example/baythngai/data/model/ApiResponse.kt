package com.example.baythngai.data.model

data class ApiResponse(
    val status: String,
    val total: Int,
    val results: Int,
    val data: List<Product>
)

