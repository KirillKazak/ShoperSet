package com.kazak.kirill.data.searchingHint

import com.kazak.kirill.data.api.ConfigApi
import com.kazak.kirill.domain.searchingHint.SearchingHintRepository

class SearchingHintRepositoryImpl: SearchingHintRepository {

    override suspend fun getSearchingHints() =
        ConfigApi().api.getSearchingHints()
}