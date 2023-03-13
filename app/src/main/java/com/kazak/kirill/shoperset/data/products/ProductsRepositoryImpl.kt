package com.kazak.kirill.shoperset.data.products

import com.kazak.kirill.shoperset.data.api.ConfigApi
import com.kazak.kirill.shoperset.domain.products.ProductsRepository

class ProductsRepositoryImpl: ProductsRepository {

    override suspend fun getLatestSearchProducts() =
        ConfigApi().api.getLatestSearchProducts()

    override suspend fun getFlashSaleProducts() =
        ConfigApi().api.getFlashSaleProducts()
}