package com.marknjunge.foodrecipeapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marknjunge.foodrecipeapp.utils.loadAsset
import com.marknjunge.foodrecipeapp.R
import com.marknjunge.foodrecipeapp.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular_recipe.view.*
import java.util.*

class PopularRecipeAdapter(private val context: Context) : RecyclerView.Adapter<PopularRecipeAdapter.ViewHolder>() {
    private var data: List<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_popular_recipe, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], context)
    }

    fun setItems(data: List<Recipe>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Recipe, context: Context) {
            itemView.run {
                tvRecipeName.text = item.name
                tvRecipePrice.text = "${item.price} Dollars"
                tvRecipeTime.text = "${item.time} Min"
                tvFavourites.text = "${item.favs}K"
                Picasso.get().loadAsset(item.image).into(imgRecipe)

                sceneRoot.setOnClickListener {
                    // For the shared element transition to work properly, the View used needs to
                    // be the specific one for the item in the RecyclerView
                    RecipeDetailsActivity.start(item, context, imgRecipe)
                }
            }
        }
    }
}