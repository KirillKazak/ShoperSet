package com.kazak.kirill.shoperset.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kazak.kirill.shoperset.R

class ProductFragment : Fragment(R.layout.fragment_product) {

    fun newInstance(param1: String, param2: String) =
        ProductFragment().apply {
            arguments = Bundle().apply {
//                putString(ARG_PARAM1, param1)
//                putString(ARG_PARAM2, param2)
            }
        }

}