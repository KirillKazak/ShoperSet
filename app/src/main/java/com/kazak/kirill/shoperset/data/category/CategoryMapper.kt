package com.kazak.kirill.shoperset.data.category

import com.kazak.kirill.shoperset.data.category.entity.CategoryEntity
import com.kazak.kirill.shoperset.domain.category.model.CategoryModel

class CategoryMapper {

    private fun mapCategoryEntityToModel(
        categoryEntity: CategoryEntity
    ): CategoryModel =
        CategoryModel(
            categoryName = categoryEntity.categoryName,
            categoryImage = categoryEntity.categoryImage,
            categoryBackground = categoryEntity.categoryBackground,
            categoryIsClick = categoryEntity.categoryIsClick
        )

    fun mapCategoryEntityListToModelList(
        categoryEntityList: List<CategoryEntity>
    ): List<CategoryModel> =
        categoryEntityList.map {
            mapCategoryEntityToModel(it)
        }
}