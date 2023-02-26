package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class DeleteUserCredentialsUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    fun deleteUserCredentials() {
        userCredentialsRepository.deleteUserCredentials()
    }
}