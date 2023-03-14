package com.kazak.kirill.shoperset.domain.productInformation.model.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductModel(
    val colors: ArrayList<String>?,
    val description: String?,
    val image_urls: ArrayList<String>?,
    val name: String?,
    val number_of_reviews: Int,
    val price: Int,
    val rating: Double
): Parcelable