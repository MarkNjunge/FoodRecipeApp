package com.marknjunge.foodrecipeapp.utils

import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun Picasso.loadAsset(filename: String): RequestCreator = this.load("file:///android_asset/$filename")
