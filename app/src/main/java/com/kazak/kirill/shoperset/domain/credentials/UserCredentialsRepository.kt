package com.kazak.kirill.shoperset.domain.credentials

import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

interface UserCredentialsRepository {

    suspend fun getUserCredentialsById(userId: Int) : UserCredentialsModel
    suspend fun saveUserCredentials(userCredentials: UserCredentialsModel)
    suspend fun deleteUserCredentials()
    suspend fun checkUserCredentialsOnSignIn(
        firstName: String,
        lastName: String,
        email: String,
        errorMessage: (String) -> Unit
    )
    suspend fun checkUserCredentialsOnLogIn(
        firstName: String,
        password: String,
        errorMessage: (String) -> Unit
    )

    suspend fun getUserPhoto(): String
}