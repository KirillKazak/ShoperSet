package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class GetUserPhotoUseCase(private val userCredentialsRepository: UserCredentialsRepository) {

    suspend fun getUserPhoto() =
        userCredentialsRepository.getUserPhoto()
}