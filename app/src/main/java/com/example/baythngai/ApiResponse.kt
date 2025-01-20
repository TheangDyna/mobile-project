package com.example.baythngai

data class ApiResponse(
    val status: String,
    val total: Int,
    val results: Int,
    val data: List<Product>
)

data class Product(
    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val cuisines: List<Cuisine>,
    val dietaries: List<String>,
    val inStock: Boolean,
    val calories: Double?,
    val protein: Double?,
    val carbs: Double?,
    val fats: Double?,
    val ingredients: List<String>,
    val ratingsAverage: Double,
    val ratingsQuantity: Int,
    val thumbnail: String,
)

data class Cuisine(
    val name: String
)
