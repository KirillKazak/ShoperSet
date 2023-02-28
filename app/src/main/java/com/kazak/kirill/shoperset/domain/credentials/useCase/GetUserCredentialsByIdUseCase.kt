package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class GetUserCredentialsByIdUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    fun getUserCredentialsById(userId: Int) =
        userCredentialsRepository.getUserCredentialsById(userId)
}