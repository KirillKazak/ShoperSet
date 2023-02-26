package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

class SaveUserCredentialsUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    fun saveUserCredentials(userCredentials: UserCredentialsModel) {
        userCredentialsRepository.saveUserCredentials(userCredentials)
    }
}