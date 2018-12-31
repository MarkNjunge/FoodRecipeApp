package com.marknjunge.foodrecipeapp.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityOptionsCompat
import com.marknjunge.foodrecipeapp.utils.generatePalette
import com.marknjunge.foodrecipeapp.utils.isDark
import com.marknjunge.foodrecipeapp.utils.loadAsset
import com.marknjunge.foodrecipeapp.utils.rgbString
import com.marknjunge.foodrecipeapp.R
import com.marknjunge.foodrecipeapp.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.include_recipe_details.*
import java.io.IOException
import java.io.InputStream

class RecipeDetailsActivity : AppCompatActivity() {

    companion object {
        const val RECIPE_KEY = "recipe"

        fun start(recipe: Recipe, context: Context, sharedElementView: View) {
            val intent = Intent(context, RecipeDetailsActivity::class.java)
            intent.putExtra(RecipeDetailsActivity.RECIPE_KEY, recipe)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, sharedElementView, "recipeImage")

            context.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val recipe = intent.extras?.get(RECIPE_KEY) as Recipe

        imgBack.setOnClickListener {
            supportFinishAfterTransition()
        }

        recipe.apply {
            Picasso.get().loadAsset(image).into(imgRecipeImage)
            tvRecipeName.text = name
            tvRecipeFavs.text = "${favs}K"
            tvRecipeTime.text = time.toString()
            tvRecipePrice.text = price.toString()

            // Change the color of the arrow based on the colors in the image and whether it's dark or not
            val bitmap = getBitmapFromAsset(this@RecipeDetailsActivity, image)
            bitmap?.let {
                bitmap.generatePalette { palette ->
                    // If the bitmap is dark, use the light color
                    val swatch = if (bitmap.isDark()) palette?.lightVibrantSwatch else palette?.darkVibrantSwatch
                    swatch?.let {
                        val parseColor = Color.parseColor(swatch.rgbString)
                        imgBack.setColorFilter(parseColor, PorterDuff.Mode.SRC_ATOP)
                    }
                }
            }
        }

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_recipe_details_final)

        val transition = ChangeBounds().apply {
            interpolator = AnticipateOvershootInterpolator(1.0f)
            duration = 500
        }

        // Wait for the activity to be fully visible and the shared element transition to be complete
        Handler().postDelayed({
            TransitionManager.beginDelayedTransition(root, transition)
            constraintSet.applyTo(root)
        }, 600)
    }


    private fun getBitmapFromAsset(context: Context, filePath: String): Bitmap? {
        val assetManager = context.assets

        val inputStream: InputStream
        return try {
            inputStream = assetManager.open(filePath)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            Log.e("ERROR:", e.message)
            null
        }
    }
}
