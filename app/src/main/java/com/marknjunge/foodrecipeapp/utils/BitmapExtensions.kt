package com.marknjunge.foodrecipeapp.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.palette.graphics.Palette

/**
 * Determine if an image is dark by calculating the luminance of each pixel.
 *
 * [Relative luminance](https://en.wikipedia.org/wiki/Relative_luminance)
 *
 * @param threshold The percentage of pixels that should be dark for the image to be considered dark.
 * Represented as a float with a max of 1f.
 */
fun Bitmap.isDark(threshold: Float = 0.45f): Boolean {
    val darkThreshold = this.width.toFloat() * this.height.toFloat() * threshold
    var darkPixels = 0

    val pixels = IntArray(this.width * this.height)
    this.getPixels(pixels, 0, this.width, 0, 0, this.width, this.height)

    for (pixel in pixels) {
        val r = Color.red(pixel)
        val g = Color.green(pixel)
        val b = Color.blue(pixel)
//        val luminance = 0.299 * r + 0.0 + 0.587 * g + 0.0 + 0.114 * b + 0.0 // Original from source
        val luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b
        if (luminance < 150) {
            darkPixels++
        }
    }

    val dark = darkPixels >= darkThreshold

    val totalPixels = this.width.toFloat() * this.height.toFloat()
    val darkPercentage = darkPixels / totalPixels * 100
    Log.d("isDark", "Dark pixels: $darkPercentage%, Threshold: ${threshold * 100}%, Conclusion: ${if (dark) "dark" else "not dark"}")
    return dark
}

/**
 * Generate a palette from a bitmap.
 * Runs in an AsyncTask.
 */
fun Bitmap.generatePalette(onComplete: (palette: Palette?) -> Unit) {
    Palette.from(this).generate { palette ->
        onComplete(palette)
    }
}

