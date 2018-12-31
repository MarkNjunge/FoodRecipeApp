package com.marknjunge.foodrecipeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marknjunge.foodrecipeapp.utils.loadAsset
import com.marknjunge.foodrecipeapp.R
import com.marknjunge.foodrecipeapp.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import java.util.*

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var data: List<Category> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setItems(data: List<Category>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Category) {
            itemView.run {
                tvCategoryName.text = item.title
                Picasso.get().loadAsset(item.image).into(imgCategoryImage)
            }
        }
    }
}