package com.kazak.kirill.shoperset.data.api

import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSaleModel
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestSearchModel
import com.kazak.kirill.shoperset.domain.products.model.product.ProductModel
import com.kazak.kirill.shoperset.domain.searchingHint.model.SearchingHintModel
import com.kazak.kirill.shoperset.util.Constants.FLASH_SALE_API_URL
import com.kazak.kirill.shoperset.util.Constants.LATEST_SEARCH_API_URL
import com.kazak.kirill.shoperset.util.Constants.PRODUCT_INFORMATION_API_URL
import com.kazak.kirill.shoperset.util.Constants.SEARCHING_HINTS_API_URL
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET(LATEST_SEARCH_API_URL)
    fun getLatestSearchProducts(): Single<LatestSearchModel>

    @GET(FLASH_SALE_API_URL)
    fun getFlashSaleProducts(): Single<FlashSaleModel>

    @GET(PRODUCT_INFORMATION_API_URL)
    fun getProductInformation(): Single<ProductModel>

    @GET(SEARCHING_HINTS_API_URL)
    fun getSearchingHints(): Single<SearchingHintModel>
}