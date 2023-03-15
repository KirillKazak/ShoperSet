package com.kazak.kirill.shoperset.ui.extenshions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImageWithoutPlaceholder(url: String) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadImageWithPlaceholder(url: String, placeholder:Int) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(placeholder)
        .into(this)
}