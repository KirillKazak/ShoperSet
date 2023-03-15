package com.kazak.kirill.domain.searchingHint.useCase

import com.kazak.kirill.domain.searchingHint.SearchingHintRepository

class GetSearchingHintsUseCase(private val searchingHintRepository: SearchingHintRepository) {

    suspend fun getSearchingHints() =
        searchingHintRepository.getSearchingHints()
}