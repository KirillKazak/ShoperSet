package com.kazak.kirill.shoperset.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.CustomViewItemProfileBinding

class CustomViewItemProfile(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    var onItemClickListener: OnItemClickListener? = null

    private val vbItemProfile = CustomViewItemProfileBinding.inflate(
        LayoutInflater.from(context), this, false
    )

    init {
        addView(vbItemProfile.root)
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomViewItemProfile, 0, 0
        )

        vbItemProfile.apply {
            val iconItem = typedArray.getDrawable(R.styleable.CustomViewItemProfile_icon_item)
            val nameItem = typedArray.getString(R.styleable.CustomViewItemProfile_name_item)
            val visibilityBalance = typedArray.getString(R.styleable.CustomViewItemProfile_visibility_balance)

            ivItemImage.background = iconItem
            tvItemName.text = nameItem

            customViewItemProfile.setOnClickListener {
                onItemClickListener?.onItemClick()
            }

            when (visibilityBalance) {
                "visible" -> {
                    ivArrow.visibility = View.INVISIBLE
                    tvBalanceScore.visibility = View.VISIBLE
                }
                "invisibleArrow" -> {
                    ivArrow.visibility = View.INVISIBLE
                    tvBalanceScore.visibility = View.INVISIBLE
                }
                else -> {
                    ivArrow.visibility = View.VISIBLE
                    tvBalanceScore.visibility = View.INVISIBLE
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick ()
    }
}