package com.kazak.kirill.shoperset.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.product.model.ProductModel

class ProductViewModel(): ViewModel() {

    val informationAboutProductLD = MutableLiveData<ProductModel>()

}