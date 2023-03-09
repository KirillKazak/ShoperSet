package com.kazak.kirill.shoperset.domain.latestSearch

import com.kazak.kirill.shoperset.domain.latestSearch.model.flashSale.FlashSaleModel
import com.kazak.kirill.shoperset.domain.latestSearch.model.latestSearch.LatestSearchModel
import io.reactivex.Single

interface ProductsRepository {

    fun getLatestSearchProducts(): Single<LatestSearchModel>
    fun getFlashSaleProducts(): Single<FlashSaleModel>
}