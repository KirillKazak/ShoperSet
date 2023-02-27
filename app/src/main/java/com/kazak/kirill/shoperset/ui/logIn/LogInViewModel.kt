package com.kazak.kirill.shoperset.ui.logIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.CheckUserCredentialsOnLogInUseCase

class LogInViewModel(
    private val checkUserCredentialsOnLogInUseCase: CheckUserCredentialsOnLogInUseCase
): ViewModel() {
    val logInMessageLiveData = MutableLiveData<String>()

    fun getLogInMessage(firstName: String, password: String
    ) {
        checkUserCredentialsOnLogInUseCase.checkUserCredentialsOnLogIn(
            firstName, password
        ) { message ->
            logInMessageLiveData.value = message
        }
    }
}