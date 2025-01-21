package com.example.baythngai

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private var products: List<Product>, private val onAddToCart: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() { // Fix: Change Adapter Type

    private val VIEW_TYPE_PRODUCT = 1
    private val VIEW_TYPE_LOADING = 2

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productName: TextView = view.findViewById(R.id.productName)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val viewDetailsButton: Button = view.findViewById(R.id.viewDetailsButton)
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int {
        return if (products[position].name == "loading") VIEW_TYPE_LOADING else VIEW_TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_PRODUCT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
            ProductViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.loading_skeleton, parent, false)
            LoadingViewHolder(view)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            val product = products[position]
            holder.productName.text = product.name
            holder.productPrice.text = "Price: $${product.price}"

            Glide.with(holder.productImage.context)
                .load(product.thumbnail)
                .into(holder.productImage)

            holder.viewDetailsButton.setOnClickListener {
                val context = holder.itemView.context
                if (context is FragmentActivity) {
                    val bottomSheet = ProductBottomSheet(product)
                    bottomSheet.show(context.supportFragmentManager, "ProductBottomSheet")
                }
            }
        }
    }

    override fun getItemCount(): Int = products.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}
