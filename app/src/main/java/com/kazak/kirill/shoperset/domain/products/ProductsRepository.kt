package com.kazak.kirill.shoperset.domain.products

import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSaleModel
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestSearchModel
import io.reactivex.Single

interface ProductsRepository {

    fun getLatestSearchProducts(): Single<LatestSearchModel>
    fun getFlashSaleProducts(): Single<FlashSaleModel>
}