package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

class GetUserCredentialsListUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    fun getUserCredentialsList(): List<UserCredentialsModel> =
        userCredentialsRepository.getUserCredentialsList()
}