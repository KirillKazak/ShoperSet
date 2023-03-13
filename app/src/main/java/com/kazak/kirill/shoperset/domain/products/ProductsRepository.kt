package com.kazak.kirill.shoperset.domain.products

import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSaleModel
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestSearchModel

interface ProductsRepository {

    suspend fun getLatestSearchProducts(): LatestSearchModel
    suspend fun getFlashSaleProducts(): FlashSaleModel
}