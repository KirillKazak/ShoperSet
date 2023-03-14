package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class CheckUserCredentialsOnLogInUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun checkUserCredentialsOnLogIn(
        firstName: String,
        password: String,
        errorMessage: (String) -> Unit
    ) {
        userCredentialsRepository.checkUserCredentialsOnLogIn(firstName, password, errorMessage)
    }
}