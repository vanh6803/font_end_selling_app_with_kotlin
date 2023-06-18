package com.example.sellingappkotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.sellingappkotlin.R
import com.example.sellingappkotlin.databinding.LayoutItemProductBinding
import com.example.sellingappkotlin.models.Product
import com.example.sellingappkotlin.utils.Config
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(var context: Context) : Adapter<ProductAdapter.ProductViewHolder>() {

    private var list = mutableListOf<Product>()

    fun setData(list: MutableList<Product>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val obj: Product = list[position]
        holder.bindView(obj)
    }

    class ProductViewHolder(val binding: LayoutItemProductBinding) : ViewHolder(binding.root) {
        fun bindView(product: Product) {
            Glide.with(binding.root).load(product.image.replace("localhost",Config.LOCALHOST )).error(R.drawable.baseline_image_24)
                .into(binding.imgProduct)
            binding.tvNameProduct.text = product.name
            val numberFormat = NumberFormat.getNumberInstance(Locale("vi", "VN"))
            val formattedPrice = numberFormat.format(product.price)
            binding.tvPriceProduct.text = "$formattedPrice đ"
        }
    }

}