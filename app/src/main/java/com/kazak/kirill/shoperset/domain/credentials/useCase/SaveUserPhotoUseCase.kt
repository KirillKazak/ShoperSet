package com.kazak.kirill.shoperset.domain.credentials.useCase

import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class SaveUserPhotoUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun saveUserPhoto(userPhoto: String) {
        userCredentialsRepository.saveUserPhoto(userPhoto)
    }
}