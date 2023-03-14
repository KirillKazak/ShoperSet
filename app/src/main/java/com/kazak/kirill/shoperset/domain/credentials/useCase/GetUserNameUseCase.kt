package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class GetUserNameUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun getUserName() =
        userCredentialsRepository.getUserName()



}