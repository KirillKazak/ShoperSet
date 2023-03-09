package com.kazak.kirill.shoperset.domain.category.useCase

import com.kazak.kirill.shoperset.domain.category.CategoryRepository
import com.kazak.kirill.shoperset.domain.category.model.CategoryModel

class GetCategoryListUseCase(private val categoryRepository: CategoryRepository) {

    fun getCategoryList(): List<CategoryModel> =
        categoryRepository.getCategoryList()
}