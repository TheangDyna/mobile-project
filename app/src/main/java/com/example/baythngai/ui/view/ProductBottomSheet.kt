package com.example.baythngai.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.baythngai.R
import com.example.baythngai.data.model.Product
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductBottomSheet(private val product: Product) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.bottom_sheet_product, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productImage: ImageView = view.findViewById(R.id.bottomSheetProductImage)
        val productName: TextView = view.findViewById(R.id.bottomSheetProductName)
        val productDescription: TextView = view.findViewById(R.id.bottomSheetProductDescription)
        val productPrice: TextView = view.findViewById(R.id.bottomSheetProductPrice)
        val productStock: TextView = view.findViewById(R.id.bottomSheetProductStock)
        val productCuisines: TextView = view.findViewById(R.id.bottomSheetProductCuisines)
        val cuisinesLabel: TextView = view.findViewById(R.id.bottomSheetCuisinesLabel)
        val productIngredients: TextView = view.findViewById(R.id.bottomSheetProductIngredients)
        val ingredientsLabel: TextView = view.findViewById(R.id.bottomSheetIngredientsLabel)
        val closeButton: Button = view.findViewById(R.id.closeBottomSheetButton)

        // Load product details
        productName.text = product.name
        productDescription.text = product.description
        productPrice.text = "Price: $${product.price}"

        // Show stock availability
        productStock.text = if (product.inStock) "In Stock ✅" else "Out of Stock ❌"

        // Load product image using Glide
        Glide.with(requireContext())
            .load(product.thumbnail)
            .into(productImage)

        // Display cuisines
        if (product.cuisines.isNotEmpty()) {
            productCuisines.text = product.cuisines.joinToString(", ") { it.name }
        } else {
            cuisinesLabel.visibility = View.GONE
            productCuisines.visibility = View.GONE
        }

        // Display ingredients
        if (product.ingredients.isNotEmpty()) {
            productIngredients.text = product.ingredients.joinToString(", ")
            ingredientsLabel.visibility = View.VISIBLE
            productIngredients.visibility = View.VISIBLE
        } else {
            ingredientsLabel.visibility = View.GONE
            productIngredients.visibility = View.GONE
        }

        // Close button action
        closeButton.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}
