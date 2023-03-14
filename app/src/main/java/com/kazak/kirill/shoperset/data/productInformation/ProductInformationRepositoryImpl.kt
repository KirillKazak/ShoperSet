package com.kazak.kirill.shoperset.data.productInformation

import com.kazak.kirill.shoperset.data.api.ConfigApi
import com.kazak.kirill.shoperset.domain.productInformation.ProductInformationRepository

class ProductInformationRepositoryImpl: ProductInformationRepository {

    override suspend fun getProductInformation() =
        ConfigApi().api.getProductInformation()
}