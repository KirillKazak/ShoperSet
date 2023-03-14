package com.kazak.kirill.shoperset.domain.credentials.useCase

import android.text.TextUtils
import android.util.Patterns
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository

class CheckUserCredentialsOnSignInUseCase(
    private val userCredentialsRepository: UserCredentialsRepository
) {

    suspend fun checkUserCredentialsOnSignIn(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    ) {
        when {
            TextUtils.isEmpty(firstName) -> { errorMessage.invoke(ENTER_FIRST_NAME_MESSAGE) }
            TextUtils.isEmpty(lastName) -> { errorMessage.invoke(ENTER_LAST_NAME_MESSAGE) }
            TextUtils.isEmpty(email) -> { errorMessage.invoke(ENTER_EMAIL_MESSAGE) }
            !email.isEmailValid() -> { errorMessage.invoke(INCORRECT_EMAIL_MESSAGE) }
            else -> {

                if (!checkDoesUserWithThisCredentialsExist(firstName, lastName, email)
                    {errorMessage.invoke(it)} ){

                    userCredentialsRepository.saveUserCredentials(
                        userFirstName = firstName,
                        userLastName = lastName,
                        userEmail = email
                    )
                }
            }
        }
    }

    private suspend fun checkDoesUserWithThisCredentialsExist(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    ): Boolean {
        val userCredentialsList = userCredentialsRepository.getUserCredentialsList()
        val firstAndLastUserName = firstName + lastName
        var isExist = false
        var message = SUCCESS_SIGN_IN_MESSAGE

        for (i in userCredentialsList) {
            if (i.userFirstName + i.userLastName == firstAndLastUserName) {
                message = USER_WITH_THIS_NAME_EXISTS_MESSAGE
                isExist = true
                break
            } else if (i.userEmail == email) {
                message = USER_WITH_THIS_EMAIL_EXISTS_MESSAGE
                isExist = true
                break
            } else {
                message = SUCCESS_SIGN_IN_MESSAGE
                isExist = false
            }
        }
        errorMessage.invoke(message)
        return isExist
    }

    private fun String.isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private companion object {
        const val ENTER_FIRST_NAME_MESSAGE = "Please, enter your first name"
        const val ENTER_LAST_NAME_MESSAGE = "Please, enter your last name"
        const val ENTER_EMAIL_MESSAGE = "Please, enter your email"
        const val INCORRECT_EMAIL_MESSAGE = "You have entered email incorrect"
        const val USER_WITH_THIS_NAME_EXISTS_MESSAGE = "A user with the same name already exists"
        const val USER_WITH_THIS_EMAIL_EXISTS_MESSAGE = "A user with the same email already exists"
        const val SUCCESS_SIGN_IN_MESSAGE = "Sign in is success"
    }
}