package com.kazak.kirill.shoperset.domain.product

import com.kazak.kirill.shoperset.domain.products.model.product.ProductModel
import io.reactivex.Single

interface ProductInformationRepository {

    fun getProductInformation(): Single<ProductModel>
}