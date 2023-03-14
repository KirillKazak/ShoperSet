package com.kazak.kirill.shoperset.domain.credentials

import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel

interface UserCredentialsRepository {
    suspend fun getUserCredentialsList(): List<UserCredentialsModel>
    suspend fun saveUserPhoto(userPhoto: String)
    suspend fun saveUserCredentials(userFirstName: String, userLastName: String, userEmail: String)
    suspend fun deleteUserCredentials()
    suspend fun getUserName() : String
    suspend fun getUserPhoto() : String
    suspend fun compareUserNames(nameFromLogin: String) : Boolean
}