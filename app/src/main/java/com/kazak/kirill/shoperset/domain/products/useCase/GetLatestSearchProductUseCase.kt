package com.kazak.kirill.shoperset.domain.products.useCase

import com.kazak.kirill.shoperset.domain.products.ProductsRepository

class GetLatestSearchProductUseCase(private val productsRepository: ProductsRepository) {

    fun getLatestSearchProduct() =
        productsRepository.getLatestSearchProducts()
}