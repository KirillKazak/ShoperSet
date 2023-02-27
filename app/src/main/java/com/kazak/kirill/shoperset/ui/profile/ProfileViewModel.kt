package com.kazak.kirill.shoperset.ui.profile

import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.DeleteUserCredentialsUseCase

class ProfileViewModel(
    private val deleteUserCredentialsUseCase: DeleteUserCredentialsUseCase
): ViewModel() {

    fun deleteUserCredentials() {
        deleteUserCredentialsUseCase.deleteUserCredentials()
    }
}