package com.kazak.kirill.shoperset.domain

import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestModel

data class ProductsModel(
    val latestModelList: List<LatestModel>,
    val flashSaleList: List<FlashSale>
)
