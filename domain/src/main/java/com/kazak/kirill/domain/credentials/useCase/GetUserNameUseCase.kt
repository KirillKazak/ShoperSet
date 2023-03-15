package com.kazak.kirill.domain.credentials.useCase

import com.kazak.kirill.domain.credentials.UserCredentialsRepository

class GetUserNameUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun getUserName() =
        userCredentialsRepository.getUserName()



}