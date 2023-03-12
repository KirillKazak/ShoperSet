package com.kazak.kirill.shoperset.data.latestSearch

import com.kazak.kirill.shoperset.data.api.ConfigApi
import com.kazak.kirill.shoperset.domain.products.ProductsRepository

class LatestSearchRepositoryImpl: ProductsRepository {

    override fun getLatestSearchProducts() =
        ConfigApi().api.getLatestSearchProducts()

    override fun getFlashSaleProducts() =
        ConfigApi().api.getFlashSaleProducts()
}