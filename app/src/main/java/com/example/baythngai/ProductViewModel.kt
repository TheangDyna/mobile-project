package com.example.baythngai

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = repository.getProducts()
                _products.value = products
                println("Fetched products: $products") // Debug log
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error fetching products: ${e.message}")
            }
        }
    }
}
