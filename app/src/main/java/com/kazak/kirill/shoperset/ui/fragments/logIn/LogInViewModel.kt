package com.kazak.kirill.shoperset.ui.fragments.logIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazak.kirill.shoperset.domain.credentials.useCase.CheckUserCredentialsOnLogInUseCase
import com.kazak.kirill.shoperset.ui.fragments.Constants.ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogInViewModel(
    private val checkUserCredentialsOnLogInUseCase: CheckUserCredentialsOnLogInUseCase
): ViewModel() {
    val logInMessageLiveData = MutableLiveData<String>()

    fun getLogInMessage(firstName: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                var toSend = ERROR
                checkUserCredentialsOnLogInUseCase.checkUserCredentialsOnLogIn(
                    firstName, password
                ) { message -> toSend = message }
                return@withContext toSend
            }

            logInMessageLiveData.value = result
        }

    }
}