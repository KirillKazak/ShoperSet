package com.kazak.kirill.shoperset.data.product

import com.kazak.kirill.shoperset.data.api.ConfigApi
import com.kazak.kirill.shoperset.domain.product.ProductInformationRepository

class ProductInformationRepositoryImpl: ProductInformationRepository {

    override fun getProductInformation() =
        ConfigApi().api.getProductInformation()
}