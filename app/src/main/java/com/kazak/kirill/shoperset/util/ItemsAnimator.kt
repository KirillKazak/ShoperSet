package com.kazak.kirill.shoperset.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.cardview.widget.CardView

fun increaseItem(item: CardView) {
    ObjectAnimator.ofPropertyValuesHolder(
        item,
        PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1.2f),
        PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1.2f)
    ).apply {
        start()
    }
}

fun decreaseItem(view: View) {
    ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 0.8f),
        PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 0.8f)
    ).apply {
        start()
    }
}