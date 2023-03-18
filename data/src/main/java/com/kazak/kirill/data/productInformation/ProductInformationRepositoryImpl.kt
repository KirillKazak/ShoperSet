package com.kazak.kirill.data.productInformation

import com.kazak.kirill.data.api.ConfigApi
import com.kazak.kirill.domain.productInformation.ProductInformationRepository

class ProductInformationRepositoryImpl(private val configApi: ConfigApi): ProductInformationRepository {

    override suspend fun getProductInformation() =
        configApi.api.getProductInformation()
}