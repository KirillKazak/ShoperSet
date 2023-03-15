package com.kazak.kirill.domain.searchingHint

import com.kazak.kirill.domain.searchingHint.model.SearchingHintModel

interface SearchingHintRepository {

    suspend fun getSearchingHints(): SearchingHintModel
}