package com.kazak.kirill.shoperset.domain.products.useCase

import com.kazak.kirill.shoperset.domain.products.ProductsRepository

class GetFlashSaleProductsUseCase(private val productsRepository: ProductsRepository) {

    fun getFlashSaleProducts() =
        productsRepository.getFlashSaleProducts()
}