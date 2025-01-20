package com.example.baythngai

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.topAppBar))

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.products.observe(this) { products ->
            println("Updating RecyclerView with ${products.size} items") // Debug Log
            adapter = ProductAdapter(products) { product ->
                println("Added to cart: ${product.name}")
            }
            recyclerView.adapter = adapter
        }

        // Fetch all products initially
        productViewModel.fetchProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        println("SearchView initialized") // Debug Log

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("Query submitted: $query") // Debug Log
                productViewModel.fetchProducts(query) // Send query to API
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("Query changed: $newText") // Debug Log
                productViewModel.fetchProducts(newText) // Live search update
                return true
            }
        })

        return true
    }
}
