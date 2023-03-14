package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class DeleteUserCredentialsUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun deleteUserCredentials() {
        userCredentialsRepository.deleteUserCredentials()
    }
}