package com.kazak.kirill.shoperset.domain.latestSearch.useCase

import com.kazak.kirill.shoperset.domain.latestSearch.ProductsRepository

class GetLatestSearchProductUseCase(private val productsRepository: ProductsRepository) {

    fun getLatestSearchProduct() =
        productsRepository.getLatestSearchProducts()
}