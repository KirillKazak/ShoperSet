package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class CheckUserCredentialsOnSignInUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    fun checkUserCredentialsOnSignIn(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    ) {
        userCredentialsRepository.checkUserCredentialsOnSignIn(
            firstName, lastName, email, errorMessage
        )
    }
}