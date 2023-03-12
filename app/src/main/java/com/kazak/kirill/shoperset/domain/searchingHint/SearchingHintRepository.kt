package com.kazak.kirill.shoperset.domain.searchingHint

import com.kazak.kirill.shoperset.domain.searchingHint.model.SearchingHintModel
import io.reactivex.Single

interface SearchingHintRepository {

    fun getSearchingHints(): Single<SearchingHintModel>
}