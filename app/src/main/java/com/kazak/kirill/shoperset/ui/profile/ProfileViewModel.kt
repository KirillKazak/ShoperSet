package com.kazak.kirill.shoperset.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.DeleteUserCredentialsUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserCredentialsByIdUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.SaveUserCredentialsUseCase
import com.kazak.kirill.shoperset.util.UserId.Companion.currentUserId

class ProfileViewModel(
    private val deleteUserCredentialsUseCase: DeleteUserCredentialsUseCase,
    private val getUserCredentialsByIdUseCase: GetUserCredentialsByIdUseCase,
    private val saveUserCredentialsUseCase: SaveUserCredentialsUseCase
): ViewModel() {
    val currentUserCredentialsLiveData = MutableLiveData<UserCredentialsModel>()

    fun deleteUserCredentials() {
        deleteUserCredentialsUseCase.deleteUserCredentials()
    }

    fun getCurrentUserCredentials() {
        currentUserCredentialsLiveData.value =
            getUserCredentialsByIdUseCase.getUserCredentialsById(currentUserId)
    }

    fun saveUserCredentials(userCredentialsModel: UserCredentialsModel) {
        saveUserCredentialsUseCase.saveUserCredentials(userCredentialsModel)
    }
}