package com.marknjunge.foodrecipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marknjunge.foodrecipeapp.R
import com.marknjunge.foodrecipeapp.model.Category
import com.marknjunge.foodrecipeapp.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPopularImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val recipeAdapter = PopularRecipeAdapter(this)
        rvPopularImages.adapter = recipeAdapter

        val recipes = listOf(
                Recipe("Salad Noodle", 15, 4, "salad_noodle.webp", 2.1),
                Recipe("Berry Pancake", 12, 6, "berry_pancake.webp", 1.2),
                Recipe("Fancy Toast", 15, 6, "fancy_toast.webp", 1.5)
        )
        recipeAdapter.setItems(recipes)

        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val categoriesAdapter = CategoriesAdapter()
        rvCategories.adapter = categoriesAdapter

        val categories = listOf(
                Category("Cake", "cake.webp"),
                Category("Pasta", "pasta.webp"),
                Category("Chicken", "chicken.webp"),
                Category("Vegan", "vegan.webp")
        )
        categoriesAdapter.setItems(categories)
    }
}
