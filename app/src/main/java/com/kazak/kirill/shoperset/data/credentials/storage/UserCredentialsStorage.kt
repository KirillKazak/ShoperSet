package com.kazak.kirill.shoperset.data.credentials.storage

import com.kazak.kirill.shoperset.data.db.entity.UserCredentialsEntity

interface UserCredentialsStorage {

    suspend fun getUserCredentialsList(): List<UserCredentialsEntity>
    suspend fun getUserCredentialsById(userId: Int): UserCredentialsEntity
    suspend fun saveUserCredentials(userCredentials: UserCredentialsEntity)
    suspend fun deleteUserCredentials()
}