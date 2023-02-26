package com.kazak.kirill.shoperset.data.credentials.storage

import com.kazak.kirill.shoperset.data.credentials.storage.entity.UserCredentialsEntity

interface UserCredentialsStorage {

    fun getUserCredentialsList(): List<UserCredentialsEntity>
    fun saveUserCredentials(userCredentials: UserCredentialsEntity)
    fun deleteUserCredentials()
}