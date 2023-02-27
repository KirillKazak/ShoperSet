package com.kazak.kirill.shoperset.ui.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.CheckUserCredentialsOnSignInUseCase

class SignInViewModel(
    private val checkUserCredentialsOnSignInUseCase: CheckUserCredentialsOnSignInUseCase
): ViewModel() {
    val signInMessageLiveData = MutableLiveData<String>()

    fun getSignInMessage(
        firstName: String,
        lastName: String,
        email: String
    ) {
        checkUserCredentialsOnSignInUseCase.checkUserCredentialsOnSignIn(
            firstName, lastName, email) { message ->
            signInMessageLiveData.value = message
        }
    }
}