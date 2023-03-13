package com.kazak.kirill.shoperset.domain.productInformation.useCase

import com.kazak.kirill.shoperset.domain.productInformation.ProductInformationRepository

class GetProductInformationUseCase(private val productInformationRepository: ProductInformationRepository) {

    suspend fun getProductInformation() =
        productInformationRepository.getProductInformation()
}