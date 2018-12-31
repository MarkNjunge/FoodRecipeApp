package com.marknjunge.foodrecipeapp.utils

import androidx.palette.graphics.Palette

val Palette.Swatch.rgbString: String
    get() = "#${Integer.toHexString(this.rgb)}"
