package com.kazak.kirill.shoperset.data.credentials

import android.text.TextUtils
import android.util.Patterns
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel
import com.kazak.kirill.shoperset.util.Constants.ENTER_EMAIL_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.ENTER_FIRST_NAME_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.ENTER_LAST_NAME_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.FIRST_USER_CREDENTIALS_ID
import com.kazak.kirill.shoperset.util.Constants.INCORRECT_EMAIL_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.SUCCESS_SIGN_IN_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.USER_WITH_THIS_EMAIL_EXISTS_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.USER_WITH_THIS_NAME_EXISTS_MESSAGE

class UserCredentialsRepositoryImpl(
    private val userCredentialsStorage: UserCredentialsStorage
): UserCredentialsRepository {

    private val mapper = UserCredentialsMapper()

    override fun getUserCredentialsList(): List<UserCredentialsModel> =
        mapper.mapUserCredentialsEntityListToModelList(
            userCredentialsStorage.getUserCredentialsList()
        )


    override fun saveUserCredentials(userCredentials: UserCredentialsModel) {
        userCredentialsStorage.saveUserCredentials(
            mapper.mapUserCredentialsModelToEntity(userCredentials)
        )
    }

    override fun deleteUserCredentials() {
        userCredentialsStorage.deleteUserCredentials()
    }

    override fun checkUserCredentialsOnSignIn(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    ) {
        when {
            TextUtils.isEmpty(firstName) -> {
                errorMessage.invoke(ENTER_FIRST_NAME_MESSAGE)
            }
            TextUtils.isEmpty(lastName) -> {
                errorMessage.invoke(ENTER_LAST_NAME_MESSAGE)
            }
            TextUtils.isEmpty(email) -> {
                errorMessage.invoke(ENTER_EMAIL_MESSAGE)
            }
            !email.isEmailValid() ->
                errorMessage.invoke(INCORRECT_EMAIL_MESSAGE)
            else -> {

                if (!checkDoesUserWithThisCredentialsExist(firstName, lastName, email)
                    {errorMessage.invoke(it)}) {

                    saveUserCredentials(
                        UserCredentialsModel(
                            userId = generateCredentialsId(),
                            userFirstName = firstName,
                            userLastName = lastName,
                            userEmail = email
                        )
                    )
                }
            }
        }
    }

    private fun String.isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun generateCredentialsId() : Int =
        if (getUserCredentialsList().isEmpty()) {
            FIRST_USER_CREDENTIALS_ID
        } else {
            getUserCredentialsList().size + FIRST_USER_CREDENTIALS_ID
        }

    private fun checkDoesUserWithThisCredentialsExist(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    ): Boolean {
        val userCredentialsList = getUserCredentialsList()
        val firstAndLastUserName = firstName + lastName
        var isExist = false
        var message = SUCCESS_SIGN_IN_MESSAGE

        for (i in userCredentialsList) {
            if (i.userFirstName + i.userLastName == firstAndLastUserName) {
                message = USER_WITH_THIS_NAME_EXISTS_MESSAGE
                isExist = true
            } else if (i.userEmail == email) {
                message = USER_WITH_THIS_EMAIL_EXISTS_MESSAGE
                isExist = true
            } else {
                message = SUCCESS_SIGN_IN_MESSAGE
                isExist = true
            }
        }
        errorMessage.invoke(message)
        return isExist
    }
}