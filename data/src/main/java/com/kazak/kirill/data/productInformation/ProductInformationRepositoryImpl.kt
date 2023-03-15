package com.kazak.kirill.data.productInformation

import com.kazak.kirill.data.api.ConfigApi
import com.kazak.kirill.domain.productInformation.ProductInformationRepository

class ProductInformationRepositoryImpl: ProductInformationRepository {

    override suspend fun getProductInformation() =
        ConfigApi().api.getProductInformation()
}