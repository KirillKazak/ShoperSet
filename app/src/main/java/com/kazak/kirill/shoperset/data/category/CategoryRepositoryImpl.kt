package com.kazak.kirill.shoperset.data.category

import android.content.Context
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.data.category.entity.CategoryEntity
import com.kazak.kirill.shoperset.domain.category.CategoryRepository
import com.kazak.kirill.shoperset.domain.category.model.CategoryModel

class CategoryRepositoryImpl(context: Context): CategoryRepository {

    private val phoneCategory = CategoryEntity(
        categoryName = context.getString(R.string.phones),
        categoryImage = context.getDrawable(R.drawable.icon_phone),
        categoryBackground = context.getDrawable(R.drawable.grey_circle),
        categoryIsClick = false
    )
    private val phoneHeadphones = CategoryEntity(
        categoryName = context.getString(R.string.headphones),
        categoryImage = context.getDrawable(R.drawable.icon_headphones),
        categoryBackground = context.getDrawable(R.drawable.grey_circle),
        categoryIsClick = false
    )
    private val phoneGames = CategoryEntity(
        categoryName = context.getString(R.string.games),
        categoryImage = context.getDrawable(R.drawable.icon_games),
        categoryBackground = context.getDrawable(R.drawable.grey_circle),
        categoryIsClick = false
    )
    private val phoneCars = CategoryEntity(
        categoryName = context.getString(R.string.cars),
        categoryImage = context.getDrawable(R.drawable.icon_cars),
        categoryBackground = context.getDrawable(R.drawable.grey_circle),
        categoryIsClick = false
    )
    private val phoneFurniture = CategoryEntity(
        categoryName = context.getString(R.string.furniture),
        categoryImage = context.getDrawable(R.drawable.icon_furniture),
        categoryBackground = context.getDrawable(R.drawable.grey_circle),
        categoryIsClick = false
    )
    private val phoneKids = CategoryEntity(
        categoryName = context.getString(R.string.kids),
        categoryImage = context.getDrawable(R.drawable.icon_kids),
        categoryBackground = context.getDrawable(R.drawable.grey_circle),
        categoryIsClick = false
    )

    private val categoryMapper = CategoryMapper()

    private val categoryListEntity =
        arrayListOf(phoneCategory, phoneHeadphones, phoneGames, phoneCars, phoneFurniture, phoneKids)

    override fun getCategoryList(): List<CategoryModel> =
        categoryMapper.mapCategoryEntityListToModelList(categoryListEntity)
}