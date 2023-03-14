package com.kazak.kirill.shoperset.domain.products.useCase

import com.kazak.kirill.shoperset.domain.products.ProductsRepository

class GetFlashSaleProductsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getFlashSaleProducts() =
        productsRepository.getFlashSaleProducts()
}