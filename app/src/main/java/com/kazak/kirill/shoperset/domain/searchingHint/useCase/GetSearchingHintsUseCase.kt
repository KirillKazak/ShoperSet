package com.kazak.kirill.shoperset.domain.searchingHint.useCase

import com.kazak.kirill.shoperset.domain.searchingHint.SearchingHintRepository

class GetSearchingHintsUseCase(private val searchingHintRepository: SearchingHintRepository) {

    fun getSearchingHints() =
        searchingHintRepository.getSearchingHints()
}