package com.kazak.kirill.data.searchingHint

import com.kazak.kirill.data.api.ConfigApi
import com.kazak.kirill.domain.searchingHint.SearchingHintRepository

class SearchingHintRepositoryImpl(private val configApi: ConfigApi): SearchingHintRepository {

    override suspend fun getSearchingHints() =
        configApi.api.getSearchingHints()
}