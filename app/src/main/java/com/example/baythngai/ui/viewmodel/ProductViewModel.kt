package com.example.baythngai.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baythngai.data.model.Product
import com.example.baythngai.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _products = MutableLiveData<MutableList<Product>>()
    val products: LiveData<MutableList<Product>> get() = _products

    private var currentPage = 1
    private val pageSize = 10
    private var isLoading = false
    private var lastPageReached = false

    fun fetchProducts(searchQuery: String? = null, reset: Boolean = false) {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                if (reset) {
                    currentPage = 1
                    lastPageReached = false
                    _products.value = mutableListOf() // Ensure list is cleared before fetching new data
                }

                println("Fetching products with query: $searchQuery, page: $currentPage") // Debug Log
                val newProducts = repository.getProducts(searchQuery, currentPage, pageSize)

                if (newProducts.isEmpty()) {
                    lastPageReached = true
                } else {
                    val updatedList = if (reset) newProducts else (_products.value ?: mutableListOf()) + newProducts
                    _products.postValue(updatedList.toMutableList()) // Ensure LiveData updates correctly
                    currentPage++
                }
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error fetching products: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

}
