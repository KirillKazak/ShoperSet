package com.kazak.kirill.shoperset.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.DeleteUserCredentialsUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserCredentialsByIdUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.SaveUserCredentialsUseCase
import com.kazak.kirill.shoperset.util.UserId.Companion.currentUserId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val deleteUserCredentialsUseCase: DeleteUserCredentialsUseCase,
    private val getUserCredentialsByIdUseCase: GetUserCredentialsByIdUseCase,
    private val saveUserCredentialsUseCase: SaveUserCredentialsUseCase
): ViewModel() {
    val currentUserCredentialsLiveData = MutableLiveData<UserCredentialsModel>()

    init {
        getCurrentUserCredentials()
    }

    fun deleteUserCredentials() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserCredentialsUseCase.deleteUserCredentials()
        }
    }

    private fun getCurrentUserCredentials() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext getUserCredentialsByIdUseCase.getUserCredentialsById(currentUserId)
            }
            currentUserCredentialsLiveData.value = result
        }
    }

    fun saveUserCredentials(userCredentialsModel: UserCredentialsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserCredentialsUseCase.saveUserCredentials(userCredentialsModel)
            getCurrentUserCredentials()
        }
    }
}