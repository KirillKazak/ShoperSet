package com.kazak.kirill.shoperset.domain.credentials

import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

interface UserCredentialsRepository {

    fun getUserCredentialsList(): List<UserCredentialsModel>
    fun saveUserCredentials(userCredentials: UserCredentialsModel)
    fun deleteUserCredentials()
    fun checkUserCredentialsOnSignIn(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    )
    fun checkUserCredentialsOnLogIn(
        firstName: String,
        password: String,
        errorMessage: (String) -> Unit
    )
}