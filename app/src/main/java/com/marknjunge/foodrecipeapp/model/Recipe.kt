package com.marknjunge.foodrecipeapp.model

import android.os.Parcel
import android.os.Parcelable

data class Recipe(val name: String, val time: Int, val price: Int, val image: String, val favs: Double) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(time)
        writeInt(price)
        writeString(image)
        writeDouble(favs)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Recipe> = object : Parcelable.Creator<Recipe> {
            override fun createFromParcel(source: Parcel): Recipe = Recipe(source)
            override fun newArray(size: Int): Array<Recipe?> = arrayOfNulls(size)
        }
    }
}