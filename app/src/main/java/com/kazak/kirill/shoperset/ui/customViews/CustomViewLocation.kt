package com.kazak.kirill.shoperset.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.kazak.kirill.shoperset.databinding.CustomViewLocationBinding

class CustomViewLocation (context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    var onItemClickListener: OnItemClickListener? = null

    private val vbItemLocation = CustomViewLocationBinding.inflate(
        LayoutInflater.from(context), this, false
    )

    init {
        addView(vbItemLocation.root)

        vbItemLocation.apply {

            customViewLocation.setOnClickListener {
                onItemClickListener?.onItemClick()
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick ()
    }
}