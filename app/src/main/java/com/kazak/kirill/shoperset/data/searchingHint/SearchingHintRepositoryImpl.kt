package com.kazak.kirill.shoperset.data.searchingHint

import com.kazak.kirill.shoperset.data.api.ConfigApi
import com.kazak.kirill.shoperset.domain.searchingHint.SearchingHintRepository

class SearchingHintRepositoryImpl: SearchingHintRepository {

    override fun getSearchingHints() =
        ConfigApi().api.getSearchingHints()
}