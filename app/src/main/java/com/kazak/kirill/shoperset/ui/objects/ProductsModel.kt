package com.kazak.kirill.shoperset.ui.objects

import com.kazak.kirill.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.domain.products.model.latestSearch.LatestModel

data class ProductsModel(
    val latestModelList: List<LatestModel>,
    val flashSaleList: List<FlashSale>
)
