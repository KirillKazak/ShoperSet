package com.kazak.kirill.util.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.kazak.kirill.R
import com.kazak.kirill.databinding.CustomViewBtnSignInWithBinding

class CustomViewBtnSignInWith(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private val vbBtnSignInWith = CustomViewBtnSignInWithBinding.inflate(
        LayoutInflater.from(context), this, false
    )

    init {
        addView(vbBtnSignInWith.root)
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomViewBtnSignInWith, 0, 0
        )

        vbBtnSignInWith.apply {
            val iconLogo = typedArray.getDrawable(R.styleable.CustomViewBtnSignInWith_icon_logo)
            val signInWith = typedArray.getString(R.styleable.CustomViewBtnSignInWith_sign_in_with)

            ivLogoSignInWith.background = iconLogo
            tvSignInWith.text = signInWith

            btnSignInWith.setOnClickListener {  }
        }
    }
}