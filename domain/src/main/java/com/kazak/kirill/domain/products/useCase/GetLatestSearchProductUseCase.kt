package com.kazak.kirill.domain.products.useCase

import com.kazak.kirill.domain.products.ProductsRepository
import com.kazak.kirill.domain.products.model.latestSearch.LatestModel
import com.kazak.kirill.domain.products.model.latestSearch.LatestSearchModel

class GetLatestSearchProductUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getLatestSearchProduct(activeCategories: List<String>) =
        filterLatestSearchProducts(productsRepository.getLatestSearchProducts(), activeCategories)

    private fun filterLatestSearchProducts(
        response: LatestSearchModel,
        activeCategories: List<String>
    ): List<LatestModel> {

        var list = listOf<String>()

        if (activeCategories.isNotEmpty()) {
            list = activeCategories
        }

        return response.latest.filter {
            list.contains(it.category)
        }
    }
}