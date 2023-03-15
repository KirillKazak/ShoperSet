package com.kazak.kirill.domain.credentials.useCase

import com.kazak.kirill.domain.credentials.UserCredentialsRepository

class GetUserPhotoUseCase(private val userCredentialsRepository: UserCredentialsRepository) {

    suspend fun getUserPhoto() =
        userCredentialsRepository.getUserPhoto()
}