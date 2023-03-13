package com.kazak.kirill.shoperset.domain.searchingHint

import com.kazak.kirill.shoperset.domain.searchingHint.model.SearchingHintModel

interface SearchingHintRepository {

    suspend fun getSearchingHints(): SearchingHintModel
}