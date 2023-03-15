package com.kazak.kirill.domain.credentials.useCase

import com.kazak.kirill.domain.credentials.UserCredentialsRepository

class DeleteUserCredentialsUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun deleteUserCredentials() {
        userCredentialsRepository.deleteUserCredentials()
    }
}