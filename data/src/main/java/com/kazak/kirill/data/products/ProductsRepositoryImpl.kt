package com.kazak.kirill.data.products

import com.kazak.kirill.data.api.ConfigApi
import com.kazak.kirill.domain.products.ProductsRepository

class ProductsRepositoryImpl: ProductsRepository {

    override suspend fun getLatestSearchProducts() =
        ConfigApi().api.getLatestSearchProducts()

    override suspend fun getFlashSaleProducts() =
        ConfigApi().api.getFlashSaleProducts()
}