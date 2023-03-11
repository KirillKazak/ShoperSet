package com.kazak.kirill.shoperset.domain.product

import com.kazak.kirill.shoperset.domain.product.model.ProductModel
import io.reactivex.Single

interface ProductInformationRepository {

    fun getProductInformation(): Single<ProductModel>
}