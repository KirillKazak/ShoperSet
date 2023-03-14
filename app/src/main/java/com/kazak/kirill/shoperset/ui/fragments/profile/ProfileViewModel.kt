package com.kazak.kirill.shoperset.ui.fragments.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazak.kirill.shoperset.ui.objects.UserNameAndPhotoModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.DeleteUserCredentialsUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserNameUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserPhotoUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.SaveUserPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val deleteUserCredentialsUseCase: DeleteUserCredentialsUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getUserPhotoUseCase: GetUserPhotoUseCase,
    private val saveUserPhotoUseCase: SaveUserPhotoUseCase
): ViewModel() {
    val userNameAndPhotoLiveData = MutableLiveData<UserNameAndPhotoModel>()

    init {
        getUserNameAndPhoto()
    }

    fun deleteUserCredentials() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserCredentialsUseCase.deleteUserCredentials()
        }
    }

    private fun getUserNameAndPhoto() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {

                val name = async {
                    return@async getUserNameUseCase.getUserName()
                }

                val photo = async {
                    return@async getUserPhotoUseCase.getUserPhoto()
                }

                return@withContext UserNameAndPhotoModel(
                    name.await(),
                    photo.await()
                )
            }
            userNameAndPhotoLiveData.postValue(result)
        }
    }

    fun saveUserPhoto(userPhoto: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserPhotoUseCase.saveUserPhoto(userPhoto)
            getUserNameAndPhoto()
        }
    }
}