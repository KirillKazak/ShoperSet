package com.kazak.kirill.shoperset.domain.products.useCase

import com.kazak.kirill.shoperset.domain.products.ProductsRepository
import com.kazak.kirill.shoperset.domain.products.model.CategoriesNamesModel
import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSaleModel

class GetFlashSaleProductsUseCase(private val productsRepository: ProductsRepository) {

    suspend fun getFlashSaleProducts(activeCategories: List<String>) =
        filterFlashSaleProducts(productsRepository.getFlashSaleProducts(), activeCategories)

    private fun filterFlashSaleProducts(
        response: FlashSaleModel,
        activeCategories: List<String>
    ): List<FlashSale> {

        var list = CategoriesNamesModel().categoriesNameList

        if (activeCategories.isNotEmpty()) {
            list = activeCategories
        }

        return response.flash_sale.filter {
            list.contains(it.category)
        }
    }

}