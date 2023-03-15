package com.kazak.kirill.domain.productInformation.useCase

import com.kazak.kirill.domain.productInformation.ProductInformationRepository

class GetProductInformationUseCase(private val productInformationRepository: ProductInformationRepository) {

    suspend fun getProductInformation() =
        productInformationRepository.getProductInformation()
}