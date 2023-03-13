package com.kazak.kirill.shoperset.domain.productInformation

import com.kazak.kirill.shoperset.domain.productInformation.model.product.ProductModel

interface ProductInformationRepository {

    suspend fun getProductInformation(): ProductModel
}