package com.kazak.kirill.shoperset.domain.latestSearch.useCase

import com.kazak.kirill.shoperset.domain.latestSearch.ProductsRepository

class GetFlashSaleProductsUseCase(private val productsRepository: ProductsRepository) {

    fun getFlashSaleProducts() =
        productsRepository.getFlashSaleProducts()
}