package com.kazak.kirill.domain.credentials.useCase

import com.kazak.kirill.domain.credentials.UserCredentialsRepository

class SaveUserPhotoUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun saveUserPhoto(userPhoto: String) {
        userCredentialsRepository.saveUserPhoto(userPhoto)
    }
}