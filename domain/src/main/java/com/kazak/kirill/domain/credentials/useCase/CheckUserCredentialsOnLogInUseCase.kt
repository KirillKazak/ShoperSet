package com.kazak.kirill.domain.credentials.useCase

import com.kazak.kirill.domain.credentials.UserCredentialsRepository

class CheckUserCredentialsOnLogInUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun checkUserCredentialsOnLogIn(
        firstName: String,
        password: String,
        errorMessage: (String) -> Unit
    ) {
        when {
            firstName.isEmpty() -> { errorMessage.invoke(ENTER_FIRST_NAME_MESSAGE) }
            password.isEmpty() -> { errorMessage.invoke(ENTER_PASSWORD_MESSAGE) }
            else -> { checkLoginWithFirstName(firstName) {errorMessage.invoke(it)} }
        }
    }

    private suspend fun checkLoginWithFirstName(firstName: String, errorMessage: (String) -> Unit) {
        if (!userCredentialsRepository.compareUserNames(firstName)) {
            errorMessage.invoke(USER_NOT_EXIST_MESSAGE)
        } else {
            errorMessage.invoke(SUCCESS_LOG_IN_MESSAGE)
        }
    }

    private companion object {
        const val ENTER_FIRST_NAME_MESSAGE = "Please, enter your first name"
        const val ENTER_PASSWORD_MESSAGE = "Please, enter your password"
        const val SUCCESS_LOG_IN_MESSAGE = "Log in is success"
        const val USER_NOT_EXIST_MESSAGE = "User with this name does not exist"
    }
}