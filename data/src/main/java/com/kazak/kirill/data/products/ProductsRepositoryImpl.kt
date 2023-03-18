package com.kazak.kirill.data.products

import com.kazak.kirill.data.api.ConfigApi
import com.kazak.kirill.domain.products.ProductsRepository

class ProductsRepositoryImpl(private val configApi: ConfigApi): ProductsRepository {

    override suspend fun getLatestSearchProducts() =
        configApi.api.getLatestSearchProducts()

    override suspend fun getFlashSaleProducts() =
        configApi.api.getFlashSaleProducts()
}