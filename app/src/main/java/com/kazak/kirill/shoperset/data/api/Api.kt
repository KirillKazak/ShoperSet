package com.kazak.kirill.shoperset.data.api

import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSaleModel
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestSearchModel
import com.kazak.kirill.shoperset.domain.productInformation.model.product.ProductModel
import com.kazak.kirill.shoperset.domain.searchingHint.model.SearchingHintModel
import retrofit2.http.GET

interface Api {

    @GET(LATEST_SEARCH_API_URL)
    suspend fun getLatestSearchProducts(): LatestSearchModel

    @GET(FLASH_SALE_API_URL)
    suspend fun getFlashSaleProducts(): FlashSaleModel

    @GET(PRODUCT_INFORMATION_API_URL)
    suspend fun getProductInformation(): ProductModel

    @GET(SEARCHING_HINTS_API_URL)
    suspend fun getSearchingHints(): SearchingHintModel

    private companion object {
        const val LATEST_SEARCH_API_URL = "/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7"
        const val FLASH_SALE_API_URL = "/v3/a9ceeb6e-416d-4352-bde6-2203416576ac"
        const val PRODUCT_INFORMATION_API_URL = "/v3/f7f99d04-4971-45d5-92e0-70333383c239"
        const val SEARCHING_HINTS_API_URL = "/v3/4c9cd822-9479-4509-803d-63197e5a9e19"
    }
}