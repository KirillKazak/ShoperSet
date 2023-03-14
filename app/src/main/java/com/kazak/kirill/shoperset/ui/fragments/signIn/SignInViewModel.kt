package com.kazak.kirill.shoperset.ui.fragments.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazak.kirill.shoperset.domain.credentials.useCase.CheckUserCredentialsOnSignInUseCase
import com.kazak.kirill.shoperset.ui.fragments.Constants.ERROR
import kotlinx.coroutines.*

class SignInViewModel(
    private val checkUserCredentialsOnSignInUseCase: CheckUserCredentialsOnSignInUseCase
): ViewModel() {
    val signInMessageLiveData = MutableLiveData<String>()

    fun getSignInMessage(
        firstName: String,
        lastName: String,
        email: String
    ) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                var toSend = ERROR
                checkUserCredentialsOnSignInUseCase.checkUserCredentialsOnSignIn(
                    firstName, lastName, email) { message ->
                    toSend = message
                }
                return@withContext toSend
            }

            signInMessageLiveData.value = result
        }
    }
}