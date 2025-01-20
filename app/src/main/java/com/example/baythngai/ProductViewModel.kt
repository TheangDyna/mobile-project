package com.example.baythngai

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        if (isLoading || lastPageReached) return

        isLoading = true
        viewModelScope.launch {
            try {
                if (reset) {
                    currentPage = 1
                    lastPageReached = false
                    _products.value = mutableListOf() // Clear list for new search
                }

                println("Fetching products with query: $searchQuery, page: $currentPage") // Debug Log
                val newProducts = repository.getProducts(searchQuery, currentPage, pageSize)

                if (newProducts.isEmpty()) {
                    lastPageReached = true
                } else {
                    val updatedList = (_products.value ?: mutableListOf()) + newProducts
                    _products.value = updatedList.toMutableList()
                    currentPage++ // Move to next page
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
