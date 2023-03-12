package com.kazak.kirill.shoperset.data.credentials

import android.text.TextUtils
import android.util.Patterns
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel
import com.kazak.kirill.shoperset.util.Constants.ENTER_EMAIL_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.ENTER_FIRST_NAME_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.ENTER_LAST_NAME_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.ENTER_PASSWORD_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.FIRST_USER_CREDENTIALS_ID
import com.kazak.kirill.shoperset.util.Constants.INCORRECT_EMAIL_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.SUCCESS_LOG_IN_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.SUCCESS_SIGN_IN_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.USER_NOT_EXIST_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.USER_WITH_THIS_EMAIL_EXISTS_MESSAGE
import com.kazak.kirill.shoperset.util.Constants.USER_WITH_THIS_NAME_EXISTS_MESSAGE
import com.kazak.kirill.shoperset.util.UserId.Companion.currentUserId

class UserCredentialsRepositoryImpl(
    private val userCredentialsStorage: UserCredentialsStorage
): UserCredentialsRepository {

    private val mapper = UserCredentialsMapper()

    override fun getUserCredentialsList(): List<UserCredentialsModel> =
        mapper.mapUserCredentialsEntityListToModelList(
            userCredentialsStorage.getUserCredentialsList()
        )

    override fun getUserCredentialsById(userId: Int): UserCredentialsModel =
        mapper.mapUserCredentialsEntityToModel(userCredentialsStorage.getUserCredentialsById(userId))

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
            !email.isEmailValid() -> {
                errorMessage.invoke(INCORRECT_EMAIL_MESSAGE)
            }
            else -> {
                if (!checkDoesUserWithThisCredentialsExist(firstName, lastName, email)
                    {errorMessage.invoke(it)} ){
                    val generatedUserId = generateCredentialsId()

                    saveUserCredentials(
                        UserCredentialsModel(
                            userId = generatedUserId,
                            userFirstName = firstName,
                            userLastName = lastName,
                            userEmail = email,
                            userPhoto = ""
                        )
                    )

                    currentUserId = generatedUserId
                }
            }
        }
    }

    override fun checkUserCredentialsOnLogIn(
        firstName: String,
        password: String,
        errorMessage: (String) -> Unit
    ) {
        when {
            TextUtils.isEmpty(firstName) -> {
                errorMessage.invoke(ENTER_FIRST_NAME_MESSAGE)
            }
            TextUtils.isEmpty(password) -> {
                errorMessage.invoke(ENTER_PASSWORD_MESSAGE)
            }
            else -> {
                checkLoginWithFirstName(firstName) {errorMessage.invoke(it)}
            }
        }
    }

    override fun getUserPhoto() =
        getUserCredentialsById(currentUserId).userPhoto

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

    private fun checkLoginWithFirstName(firstName: String, errorMessage: (String) -> Unit) {
        val userCredentialsList = getUserCredentialsList()
        var isSuccess = false

        for (i in userCredentialsList) {
            if (i.userFirstName == firstName) {
                errorMessage.invoke(SUCCESS_LOG_IN_MESSAGE)
                isSuccess = true
                currentUserId = i.userId
                break
            } else {
                isSuccess = false
            }
        }

        if (!isSuccess) {
            errorMessage.invoke(USER_NOT_EXIST_MESSAGE)
        }
    }
}