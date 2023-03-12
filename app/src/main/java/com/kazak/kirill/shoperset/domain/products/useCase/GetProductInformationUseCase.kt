package com.kazak.kirill.shoperset.domain.products.useCase

import com.kazak.kirill.shoperset.domain.product.ProductInformationRepository

class GetProductInformationUseCase(private val productInformationRepository: ProductInformationRepository) {

    fun getProductInformation() =
        productInformationRepository.getProductInformation()
}