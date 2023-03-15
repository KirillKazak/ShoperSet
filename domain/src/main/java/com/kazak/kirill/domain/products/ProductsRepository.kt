package com.kazak.kirill.domain.products

import com.kazak.kirill.domain.products.model.flashSale.FlashSaleModel
import com.kazak.kirill.domain.products.model.latestSearch.LatestSearchModel

interface ProductsRepository {

    suspend fun getLatestSearchProducts(): LatestSearchModel
    suspend fun getFlashSaleProducts(): FlashSaleModel
}