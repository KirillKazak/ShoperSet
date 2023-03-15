package com.kazak.kirill.domain.productInformation

import com.kazak.kirill.domain.productInformation.model.product.ProductModel

interface ProductInformationRepository {

    suspend fun getProductInformation(): ProductModel
}