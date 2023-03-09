package com.kazak.kirill.shoperset.domain.category

import com.kazak.kirill.shoperset.domain.category.model.CategoryModel

interface CategoryRepository {

    fun getCategoryList(): List<CategoryModel>
}